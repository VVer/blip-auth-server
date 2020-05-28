package com.tangchen.blip.service;

import com.security.foxtc.component.mobile.service.MobileUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author tangchen
 */
@Service
public class MobileUserDetailsServiceImpl implements MobileUserDetailsService {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String loadCodeByMobile(String mobile) {
        return "3454";
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) {
        return userDetailsService.loadUserByUsername(mobile);
    }
}
