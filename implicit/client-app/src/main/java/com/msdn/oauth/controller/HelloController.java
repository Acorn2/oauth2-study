package com.msdn.oauth.controller;

import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/10/23 11:24 上午
 * @description
 */
@Controller
public class HelloController {

  @Autowired
  RestTemplate restTemplate;

  @GetMapping("/")
  public String hello() {
    return "index";
  }

  @GetMapping("/resource")
  public String toResource() {
    return "resource";
  }

}
