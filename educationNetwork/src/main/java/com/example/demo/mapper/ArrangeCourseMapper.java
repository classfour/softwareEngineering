package com.example.demo.mapper;

import com.example.demo.domain.ArrangeCourseClassroomEntity;
import com.example.demo.domain.ArrangeCourseTeacherEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ArrangeCourseMapper {

    @Select("select name from course where status = 0 and specialty =  #{specialty}")
    List<String> getAllCoursesBySpecialty(String specialty);

    @Select("select name,enable_teach_courses,occupation from teacher")
    List<ArrangeCourseTeacherEntity> getAllTeachersInfo();

    @Select("select number from course where name =#{courseName}")
    int getCourseNumber(String courseName);

    @Select("select max_number from course where name = #{courseName}")
    int getCourseMaxNumber(String courseName);

    @Select("select class_number,occupied from classroom where capacity=#{courseMaxNumber}")
    List<ArrangeCourseClassroomEntity> getAvailableClassroomsInfo(int courseMaxNumber);

}
