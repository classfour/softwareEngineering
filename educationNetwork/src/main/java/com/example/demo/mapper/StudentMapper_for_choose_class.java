package com.example.demo.mapper;

import com.example.demo.domain.Student_for_choose_class__;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentMapper_for_choose_class {
    @Select("select * from student where number=#{number}")
    public Student_for_choose_class__ select(String number);
    @Update("update student set status=#{status} where number=#{number}")
    public boolean updateStatus(int status, String number);
    @Update("update student set schedule=#{schedule} where number=#{number}")
    public int changeSchedule(@Param("number") String number, @Param("schedule") String Schedule);
}
