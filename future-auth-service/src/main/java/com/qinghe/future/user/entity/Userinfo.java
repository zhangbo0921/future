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
 * @since 2020-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_userinfo")
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String name;

    private String nickName;

    private String headImgUrl;

    private String account;

    private String gender;

    private String weixin;

    private String phone;

    private String password;

    private Integer isLock;

    private Integer enable;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
