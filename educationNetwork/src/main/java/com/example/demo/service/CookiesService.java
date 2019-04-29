package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookiesService {
    public boolean setCookies(String name, String value);
    public String getCookies(String cookieName);
}
