package com.qinghe.future.oauth.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Blob;

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
public class OauthCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private Blob authentication;


}
