package com.qinghe.future.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Resource
    private TokenStore tokenStore;
    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    @Resource(name = "jdbcClientDetailsService")
    private ClientDetailsService clientDetailsService;
    @Resource
    private AuthorizationCodeServices authorizationCodeServices;

    //客户端详情服务配置
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
//        clients.inMemory()
//                .withClient("test")
//                .secret("123")
//                .resourceIds("future-order-service")  //配置可以访问的服务ID，如：future-order-service
//                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token") //访问的5种方式
//                .scopes("all") //授权范围，客户端的权限 如：future-order-service
//                .autoApprove(false)  //自动批准，false会跳转到授权页面，true会自动发令牌
//                .redirectUris("http://www.baidu.com");
    }

    //授权服务端点配置
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager) //认证管理器  密码模式需要
                .authorizationCodeServices(authorizationCodeServices)  //授权码服务  授权码模式需要
                .tokenServices(tokenServices()) //临牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST); //端点允许POST请求
    }

    //授权服务端点安全策略
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")    //oauth/token_key 公开
                .checkTokenAccess("permitAll()")  // oauth/check_token 公开
                .allowFormAuthenticationForClients(); //允许表单认证
    }

    //token服务
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices(); //token服务
        services.setClientDetailsService(clientDetailsService);//设置客户端详情服务
        services.setSupportRefreshToken(true);//是否产生刷新令牌
        services.setTokenStore(tokenStore);//令牌存储策略
        //设置JWT令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        services.setTokenEnhancer(tokenEnhancerChain);
        services.setAccessTokenValiditySeconds(7200);//令牌默认有效2小时
        services.setRefreshTokenValiditySeconds(259200); //刷新令牌默认3天有效
        return services;
    }

    //授权码服务
    @Bean
    public AuthorizationCodeServices jdbcAuthorizationCodeServices(DataSource dataSource){
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    //客户端Bean服务配置
    @Bean("jdbcClientDetailsService")
    public ClientDetailsService clientDetailsService(DataSource dataSource){
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
        return jdbcClientDetailsService;
    }
}
