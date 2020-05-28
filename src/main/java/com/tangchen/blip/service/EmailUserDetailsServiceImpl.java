package com.tangchen.blip.service;


import com.security.foxtc.component.email.service.EmailUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * 根据邮箱获取验证码
 *
 * @author tangchen
 */
@Service
public class EmailUserDetailsServiceImpl implements EmailUserDetailsService {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String loadCodeByEmail(String email) {
        return "1234";
    }

    @Override
    public UserDetails loadUserByEmail(String email) {
        return userDetailsService.loadUserByUsername(email);
    }
}
