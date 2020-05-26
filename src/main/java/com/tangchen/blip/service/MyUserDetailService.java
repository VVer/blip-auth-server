package com.tangchen.blip.service;

import com.tangchen.blip.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangchen
 */
@Primary
@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserModel loadUserByUsername(String s) throws UsernameNotFoundException {
        UserModel userModel = new UserModel();
        userModel.setEmail("1219754780@qq.com");
        userModel.setEnable(true);
        userModel.setPassword(passwordEncoder.encode("123456"));
        userModel.setUserId("123");
        userModel.setAuthorities(new ArrayList<>());
        userModel.setUserName("18180462253");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("listTopic"));
        userModel.setAuthorities(authorities);
        return userModel;
    }
}
