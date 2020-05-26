package com.tangchen.blip.service;

import com.security.foxtc.component.social.model.SocialUserDetails;
import com.security.foxtc.component.social.service.SocialUserDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author tangchen
 */
@Service
public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 获取社交用户信息
     *
     * @param socialToken
     * @return
     */
    @Override
    public SocialUserDetails loadUserBySocialToken(String socialToken) {
        if (StringUtils.isEmpty(socialToken)) {
            return null;
        }
        if (!"123456".equals(socialToken)) {
            return null;
        }
        SocialUserDetails socialUserDetails = new SocialUserDetails();
        socialUserDetails.setSocialId("test123");
        socialUserDetails.setSocialAccessToken(socialToken);
        return socialUserDetails;
    }

    /**
     * 获取社交关联的用户信息
     *
     * @param socialId
     * @return
     */
    @Override
    public UserDetails loadUserBySocialId(String socialId) {
        if ("test123".equals(socialId)) {
            return userDetailsService.loadUserByUsername(socialId);
        }
        return null;
    }
}
