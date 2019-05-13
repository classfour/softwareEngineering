package com.example.demo.mapper;

import com.example.demo.domain.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface CourseMapper {
    @Select("select * from course")
    List<Course> selectCourse();
    @Select("select number from course")
    List<String> selectnumber();
    @Select("select * from course where number=#{number}")
    Course getCourseByNumber(@Param("number") String number);
    @Update("update course set status=#{status} where  number=#{number}")
    int updateStatusByNumber(@Param("number") String number,@Param("status")  int status);
    @Select("select occupation from course where number=#{number}")
    String getoccupation(@Param("number") String number);


}
