package com.msdn.oauth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/10/22 9:38 下午
 * @description
 */
@EnableAuthorizationServer
@Configuration
@RequiredArgsConstructor
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

  private final TokenStore tokenStore;
  private final ClientDetailsService clientDetailsService;

  @Bean
  AuthorizationServerTokenServices tokenServices() {
    DefaultTokenServices services = new DefaultTokenServices();
    services.setClientDetailsService(clientDetailsService);
    services.setSupportRefreshToken(true);
    services.setTokenStore(tokenStore);
    services.setAccessTokenValiditySeconds(60 * 60 * 2); // 令牌默认有效期2小时
    services.setRefreshTokenValiditySeconds(60 * 60 * 24); // 刷新令牌默认有效期1天
    return services;
  }


  //配置令牌端点(Token Endpoint)的安全约束
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess("permitAll()")///oauth/token_key公开
        .checkTokenAccess("permitAll()")///oauth/check_token公开
        .allowFormAuthenticationForClients();//允许表单认证
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient("client1")
        .secret(new BCryptPasswordEncoder().encode("secret")) //client的id和密码
        .resourceIds("res1")    //给client一个id,这个在client的配置里要用的
        .authorizedGrantTypes("authorization_code", "refresh_token")
        .scopes("all")  //授权的范围,每个resource会设置自己的范围.
        .autoApprove(false) //这个是设置要不要弹出确认授权页面的.
        .redirectUris("http://localhost:8083/resource")   //这个相当于是client的域名,重定向给code的时候会跳转这个域名
    ;
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authorizationCodeServices(authorizationCodeServices())
        .tokenServices(tokenServices());
  }

  //设置授权码模式的授权码如何存取，暂时采用内存方式
  @Bean
  AuthorizationCodeServices authorizationCodeServices() {
    return new InMemoryAuthorizationCodeServices();
  }
}
