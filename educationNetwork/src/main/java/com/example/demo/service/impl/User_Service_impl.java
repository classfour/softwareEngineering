package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.mapper.User_Mapper;
import com.example.demo.service.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User_Service_impl implements User_Service {
    @Autowired
    private User_Mapper user_mapper;

    @Override
    public User User_select(String username,String password){
        return user_mapper.User_select(username,password);
    }
}
