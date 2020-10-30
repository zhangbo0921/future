package com.qinghe.future.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qinghe.future.user.entity.Permission;
import com.qinghe.future.user.mapper.PermissionMapper;
import com.qinghe.future.user.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张波
 * @since 2020-10-28
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionListByUserId(String userId) {
        return permissionMapper.getPermissionListByUserId(userId);
    }
}
