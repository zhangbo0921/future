package com.qinghe.future.user.service.impl;

import com.qinghe.future.user.entity.Permission;
import com.qinghe.future.user.entity.Userinfo;
import com.qinghe.future.user.service.PermissionService;
import com.qinghe.future.user.service.UserinfoService;
import com.qinghe.future.user.vo.UserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserinfoService userinfoService;
    @Resource
    private PermissionService permissionService;
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        //查询账号
        Userinfo userinfo = userinfoService.loadUserByUsername(account);
        if(userinfo==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //查询权限
        List<Permission> permissionList = permissionService.getPermissionListByUserId(userinfo.getUserId().toString());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if(permissionList!=null && permissionList.size()>0){
            for (Permission permission : permissionList) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getResourcesCode()));
            }
        }

        UserDetail userDetail = new UserDetail(userinfo.getName(),userinfo.getPassword(),userinfo.getEnable()==1?true:false,true,true,userinfo.getIsLock()==0?true:false,grantedAuthorities);
        userDetail.setUserId(userinfo.getUserId().toString());
        userDetail.setNickName(userinfo.getNickName());
        userDetail.setGender(userinfo.getGender());
        userDetail.setHeadImgUrl(userinfo.getHeadImgUrl());

        return userDetail;
    }
}
