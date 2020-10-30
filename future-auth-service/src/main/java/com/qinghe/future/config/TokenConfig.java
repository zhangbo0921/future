package com.qinghe.future.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class TokenConfig {

    @Value("${security.oauth2.jwt.secret}")
    private String JWT_SECRET;

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * JWTToken转换器
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        //设置自定义用户访问TokenConverter，扩展用户信息
        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
        UserTokenConverter userTokenConverter = new UserTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(userTokenConverter);
        converter.setAccessTokenConverter(defaultAccessTokenConverter);

        converter.setSigningKey(JWT_SECRET);
        return converter;
    }
}
