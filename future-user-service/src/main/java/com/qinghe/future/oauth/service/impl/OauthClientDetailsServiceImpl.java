package com.qinghe.future.oauth.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinghe.future.oauth.entity.OauthClientDetails;
import com.qinghe.future.oauth.mapper.OauthClientDetailsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张波
 * @since 2020-10-30
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IService<OauthClientDetails> {

}
