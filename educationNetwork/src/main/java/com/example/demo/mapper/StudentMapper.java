package com.example.demo.mapper;

import com.example.demo.domain.Student;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper {
    @Select("select * from student where number=#{number}")
    public Student select(String number);
}
