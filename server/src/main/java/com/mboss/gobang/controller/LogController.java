package com.mboss.gobang.controller;

import com.mboss.gobang.entity.ResponseData;
import com.mboss.gobang.util.RandomUtil;
import com.mboss.gobang.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    RedisUtil redisUtil;

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login/{username}/{password}/{remember}")
    public ResponseData login(
            @PathVariable("username") String username,
            @PathVariable("password") String password,
            @PathVariable("remember") boolean remember,
            HttpServletResponse response){
        String verify_password="test";
        // 验证是否登录成功
        if(password.equals(verify_password)) {
            // 设置cookie并返回
            String cookie_value=RandomUtil.random9Num();
            Cookie cookie=new Cookie("isLogin", cookie_value);
            cookie.setPath("/");
            if(remember){
                redisUtil.set(cookie_value,username,60*60*24*7);
                cookie.setMaxAge(60*60*24*7);
            }else{
                cookie.setMaxAge(-1);
                redisUtil.set(cookie_value,1,60*60*24);
            }
            response.addCookie(cookie);

            return ResponseData.success("login_success");
        }
        else return ResponseData.fail(40);
    }

    @GetMapping("/test")
    public String test(){
        System.out.println("log/test");
        return "test";
    }
}
