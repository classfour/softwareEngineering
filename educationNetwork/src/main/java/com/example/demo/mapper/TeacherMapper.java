package com.example.demo.mapper;

import com.example.demo.domain.Teacher;
import org.apache.ibatis.annotations.Select;

public interface TeacherMapper {
    @Select("select * from teacher where number=#{number}")
    public Teacher selectByNumber(String number);
}
