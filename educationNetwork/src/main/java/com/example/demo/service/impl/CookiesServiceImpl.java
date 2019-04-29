package com.example.demo.service.impl;

import com.example.demo.service.CookiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookiesServiceImpl implements CookiesService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    /**
    * @Description: 设置cookie的值
    * @Param: [name cookie的名字, value cookie的值]
    * @return: boolean
    * @Author: klx
    * @Date: 2019-04-29
    */
    @Override
    public boolean setCookies(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        response.addCookie(cookie);
        return true;
    }

    /**
    * @Description: 获取cookie的值
    * @Param: [cookieName cookie的名字]
    * @return: java.lang.String
    * @Author: klx
    * @Date: 2019-04-29
    */
    @Override
    public String getCookies(String cookieName) {

        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(cookieName)){
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}
