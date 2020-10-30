package com.qinghe.future.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 张波
 * @since 2020-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String resourcesName;

    private String resourcesCode;

    private Integer pid;

    private String uri;

    /**
     * 1:内部打开 2:外部打开
     */
    private Integer openType;

    private Integer sort;

    /**
     * 1:系统 2:模块 3:菜单 4.操作
     */
    private Integer type;

    private String desc;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
