package com.example.demo.controller.Score_management_system;

import com.example.demo.domain.User;
import com.example.demo.service.CookiesService;
import com.example.demo.service.User_Service;
import com.example.demo.service.impl.User_Service_impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//测试登录
@Controller
public class get_Cookie {
    @Autowired
    private User_Service user_service;
    @Autowired
    private CookiesService cookiesService;
    @RequestMapping("/test_user")
//    @ResponseBody
    public String Test(@RequestParam ("username") String username,@RequestParam("pwd") String pwd){
        User user=user_service.User_select(username,pwd);
        if(user!=null)
        {
            cookiesService.setCookies("username",username);
            String user_name=cookiesService.getCookies("username");
            System.out.println(user_name);
            System.out.println(pwd);
            System.out.println("yes");

            if(username.equals("2016001"))
                return "redirect:/score_query";
            else
                return "redirect:/tscore_query";
        }
        System.out.println("no");
        return "redirect:/login";
    }

}
