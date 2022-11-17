package com.msdn.oauth;

import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/10/29 8:49 下午
 * @description
 */
@SpringBootTest
class TokenTest {

  @Autowired
  RestTemplate restTemplate;

  @Test
  void contextLoads() {
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("client_id", "client1");
    map.add("client_secret", "secret");
    map.add("grant_type", "client_credentials");
    Map<String, String> resp = restTemplate
        .postForObject("http://localhost:8081/oauth/token", map, Map.class);
    if (Objects.isNull(resp)) {
      return;
    }
    String access_token = resp.get("access_token");
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + access_token);
    HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
    ResponseEntity<String> entity = restTemplate
        .exchange("http://localhost:8082/hello", HttpMethod.GET, httpEntity, String.class);
    System.out.println(entity.getBody());
  }
}
