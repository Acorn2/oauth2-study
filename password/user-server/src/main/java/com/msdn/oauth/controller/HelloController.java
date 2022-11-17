package com.msdn.oauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/10/23 11:20 上午
 * @description
 */
@RestController
@CrossOrigin(value = "*")
public class HelloController {

  @GetMapping("/r/r1")
  @PreAuthorize("hasAnyAuthority('p1')") //拥有p1权限才可以访问
  public String r1() {
    return " 访问资源1";
  }

  @GetMapping("/r/r2")
  @PreAuthorize("hasAnyAuthority('p2')") //拥有p2权限才可以访问
  public String r2() {
    return " 访问资源2";
  }

  @GetMapping("/hello")
  public String hello() {
    return "恭喜你登录成功！";
  }

  @GetMapping("/error")
  public String error() {
    return "登录失败，请重试！";
  }
}
