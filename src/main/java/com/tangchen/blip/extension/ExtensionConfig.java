package com.tangchen.blip.extension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author tangchen
 */
@Configuration
public class ExtensionConfig {

    @Autowired
    private LettuceConnectionFactory connectionFactory;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        /**
         * 对称加密token
         */
        JwtAccessTokenConverter jwtAccessTokenConverter = new CustomJwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("aasscc");
        return jwtAccessTokenConverter;
    }


    @Bean
    public RedisTokenStore redisTokenStore() {
        return new RedisTokenStore(connectionFactory);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
