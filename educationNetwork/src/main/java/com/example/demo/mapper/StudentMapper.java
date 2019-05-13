package com.example.demo.mapper;

import com.example.demo.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface StudentMapper {
    @Select("select * from student where number=#{number}")
    public Student select(String number);
    @Update("update student set status=#{status} where number=#{number}")
    public boolean updateStatus(int status, String number);
}
