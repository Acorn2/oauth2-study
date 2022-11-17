package com.msdn.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/10/23 11:18 上午
 * @description
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  public static final String RESOURCE_ID = "res1";
  /**
   *  资源服务令牌解析服务，授权码模式需要该方法，jwt不需要
   * @return
   */
  @Bean
  public RemoteTokenServices tokenServices(){
    RemoteTokenServices services = new RemoteTokenServices();
    services.setCheckTokenEndpointUrl("http://localhost:8081/oauth/check_token");
    services.setClientId("client1");
    services.setClientSecret("secret");
    return services;
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId(RESOURCE_ID)
        .tokenServices(tokenServices())
    ;
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/r/r1").hasAuthority("p1")
        .antMatchers("/r/r2").hasAuthority("p2")
        .antMatchers("/r/**").authenticated()
        .anyRequest().permitAll()
        .and().cors()
    ;
  }
}
