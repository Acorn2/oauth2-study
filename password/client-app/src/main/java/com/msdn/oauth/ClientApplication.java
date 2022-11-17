package com.msdn.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/10/23 11:23 上午
 * @description
 */
@SpringBootApplication
public class ClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClientApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
//  @Bean
//  public RestTemplate restTemplate() {
//    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//    requestFactory.setOutputStreaming(
//        false); // 解决401报错时，报java.net.HttpRetryException: cannot retry due to server authentication, in streaming mode
//    RestTemplate restTemplate = new RestTemplate(requestFactory);
//
//    return restTemplate;
//  }

}
