package com.example.demo.mapper;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from user where username = #{username} and password = #{password}")
    public User select(String username, String password);

}
