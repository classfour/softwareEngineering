package com.example.demo.mapper;

import com.example.demo.domain.ArrangeCourseClassroomEntity;
import com.example.demo.domain.ArrangeCourseTeacherEntity;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Select("select number from teacher where name=#{teacherName}")
    int getTeacherNumberByName(String teacherName);

    @Select("select class_number,occupied from classroom where capacity=#{courseMaxNumber}")
    List<ArrangeCourseClassroomEntity> getAvailableClassroomsInfo(int courseMaxNumber);

    @Update("update course set teacher_number=#{teacherNumber},occupation=#{occupation} where name =#{courseName}")
    boolean updateCourseTeacher(String teacherNumber, String occupation, String courseNumber);

    @Update("update classroom set occupied=#{occupied} where class_number=#{classNumber}")
    boolean updateClassroomOccupied(String occupied, String classNumber);

    @Select("select occupied from classroom where class_number=#{classNumber}")
    String getClassroomOccupied(String classNumber);
}
