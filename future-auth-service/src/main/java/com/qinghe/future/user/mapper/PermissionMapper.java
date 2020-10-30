package com.qinghe.future.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinghe.future.user.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张波
 * @since 2020-10-28
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> getPermissionListByUserId(@Param("userId") String userId);
}
