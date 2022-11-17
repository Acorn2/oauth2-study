package com.msdn.oauth.config;

import java.util.Map;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * @author hresh
 * @date 2021/9/16 21:31
 * @description JWT 默认生成的用户信息主要是用户角色、用户名等，如果我们希望在生成的 JWT 上面添加额外的信息
 */
@Configuration
public class MyTokenEnhancer implements TokenEnhancer {

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken,
      OAuth2Authentication oAuth2Authentication) {
    Map<String, Object> info = oAuth2AccessToken.getAdditionalInformation();
    info.put("author", "hresh");
    ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);

    return oAuth2AccessToken;
  }
}
