package com.qinghe.future.config;

import com.qinghe.future.user.vo.UserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserTokenConverter extends DefaultUserAuthenticationConverter {

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        UserDetail userDetail = (UserDetail)authentication.getPrincipal();
        Map<String,Object> userinfo = new LinkedHashMap<String, Object>();
        userinfo.put("userId", userDetail.getUserId());
        userinfo.put("username", userDetail.getUsername());
        userinfo.put("nickName", userDetail.getNickName());
        userinfo.put("sex", userDetail.getGender());
        userinfo.put("headImgUrl", userDetail.getHeadImgUrl());
        response.put("userinfo",userinfo);
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }
}
