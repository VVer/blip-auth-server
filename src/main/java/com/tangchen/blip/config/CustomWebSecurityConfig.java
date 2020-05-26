package com.tangchen.blip.config;


import com.security.foxtc.config.EmailSecurityConfigurerAdapter;
import com.security.foxtc.config.MobileSecurityConfigurerAdapter;
import com.security.foxtc.config.SocialSecurityConfigurerAdapter;
import com.security.foxtc.handler.BaseLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring security的配置
 *
 * @author tangchen
 */
@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EmailSecurityConfigurerAdapter emailSecurityConfigurerAdapter;
    @Autowired
    private MobileSecurityConfigurerAdapter mobileSecurityConfigurerAdapter;
    @Autowired
    private SocialSecurityConfigurerAdapter socialSecurityConfigurerAdapter;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 定义成功登出的处理器
     *
     * @return
     */
    @Bean
    public BaseLogoutSuccessHandler customLogoutSuccessHandler() {
        return new BaseLogoutSuccessHandler();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(customLogoutSuccessHandler())
                .permitAll()
                .and().authorizeRequests()
                .antMatchers("/email/token", "/mobile/token", "/social/token").permitAll().and().authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .disable()
                .csrf().disable()
                .apply(emailSecurityConfigurerAdapter)
                .and().apply(mobileSecurityConfigurerAdapter)
                .and().apply(socialSecurityConfigurerAdapter);

    }
}
