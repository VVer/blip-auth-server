package com.tangchen.blip.extension;

import com.tangchen.blip.model.UserModel;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置扩展JWT token返回参数
 *
 * @author tangchen
 */
public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Map<String, Object> additionalInformationMap = new HashMap<>();
        UserModel userDetails = (UserModel) authentication.getPrincipal();
        DefaultOAuth2AccessToken oAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;

        additionalInformationMap.put("email1", userDetails.getEmail());
        additionalInformationMap.put("userId1", userDetails.getUserId());
        oAuth2AccessToken.setAdditionalInformation(additionalInformationMap);
        return super.enhance(oAuth2AccessToken, authentication);
    }
}
