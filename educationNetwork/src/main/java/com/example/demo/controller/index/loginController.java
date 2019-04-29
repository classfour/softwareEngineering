package com.example.demo.controller.index;

import com.example.demo.domain.User;
import com.example.demo.service.CookiesService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.xml.ws.Service;

@Controller
public class loginController {
    @Autowired
    CookiesService cookiesService;
    @Autowired
    UserService userService;

    @RequestMapping("login")
    public String login(String usernmae, String password) {
        return "index/login";
    }
    @RequestMapping("home")
    public String index(Model model) {
        String username = cookiesService.getCookies("username");
        model.addAttribute("username", username);
        return "index/index";
    }
    @RequestMapping("submit")
    public String submit(String username, String password, Model model) {
        User user = userService.select(username, password);
//        System.out.println(user.getUsername());
        if(user == null) {
            //重定向
            return "redirect:/login";
        }else{
            cookiesService.setCookies("username", username);
            return "redirect:/home";
        }
    }
}
