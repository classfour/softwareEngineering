package com.example.demo.mapper;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Select;

public interface User_Mapper {

    @Select("select user.username,user.password,user.level from user where user.username=#{username} and user.password=#{password}")
    public User User_select(String username, String password);
}
