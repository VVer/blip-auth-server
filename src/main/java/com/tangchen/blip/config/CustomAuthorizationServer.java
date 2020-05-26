package com.tangchen.blip.config;

import com.security.foxtc.translator.BaseWebResponseExceptionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author tangchen
 */
@Configuration
@EnableAuthorizationServer
public class CustomAuthorizationServer extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private BaseWebResponseExceptionTranslator baseWebResponseExceptionTranslator;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    private RedisTokenStore redisTokenStore;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 处理令牌的一些操作安全性操作
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //配置检查检查token断点需要认证后才能访问，配置获取token key端点全部放开，运行client进行表单登录
        security.checkTokenAccess("permitAll()")
                .tokenKeyAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }


    /**
     * 处理资源服务器client的配置
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("juexiao")
                .scopes("all")
                .secret(passwordEncoder.encode("juexiao-secret"))
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(3600)
                .authorizedGrantTypes("password", "client_details", "refresh_token")
                .and()
                .withClient("study-consumer")
                .scopes("all")
                .secret(passwordEncoder.encode("study-consumer-secret"))
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(3600)
                .authorizedGrantTypes("client_details", "refresh_token");
    }

    /**
     * 处理令牌的一些配置
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenStore(redisTokenStore).exceptionTranslator(baseWebResponseExceptionTranslator);
    }


}
