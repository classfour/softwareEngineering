package com.example.demo.mapper;

import com.example.demo.domain.ArrangeCourseClassroomEntity;
import com.example.demo.domain.ArrangeCourseTeacherEntity;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ArrangeCourseMapper {

    //    获得这个专业没有排的所有的课
    @Select("select name from course where status = 0 and specialty =  #{specialty} and grade=#{grade}")
    List<String> getUnarrangedCoursesBySpecialty(String specialty, int grade);

    //获得此专业已经排的课所有时间
    @Select("select occupation from course where status=1 and specialty=#{specialty} and grade=#{grade}")
    List<String> getArrangedCoursesOccupation(String specialty, int grade);

    //    获得排课所需要的老师的所有信息
    @Select("select name,enable_teach_courses,occupation from teacher")
    List<ArrangeCourseTeacherEntity> getAllTeachersInfo();

    //    获得课程编号
    @Select("select number from course where name =#{courseName}")
    int getCourseNumber(String courseName);

    //    获得课程的最大选课人数
    @Select("select max_number from course where name = #{courseName}")
    int getCourseMaxNumber(String courseName);

    //    获得老师的编号
    @Select("select number from teacher where name=#{teacherName}")
    int getTeacherNumberByName(String teacherName);

    //    获得排课需要的教室需要的信息
    @Select("select class_number,occupied from classroom where capacity=#{courseMaxNumber}")
    List<ArrangeCourseClassroomEntity> getAvailableClassroomsInfo(int courseMaxNumber);

    //    更新course中的所有信息
    @Update("update course set teacher_number=#{teacherNumber},occupation=#{occupation},status=1,location=#{class_number},begin=1,end=19 where name =#{courseName}")
    boolean updateCourse(int teacherNumber, String occupation, String class_number, String courseName);

    //    更新教室的占用情况
    @Update("update classroom set occupied=#{occupied} where class_number=#{classNumber}")
    boolean updateClassroomOccupied(String occupied, String classNumber);

    //更新老师空闲时间
    @Update("update teacher set occupation=#{occupation} where name=#{teachername}")
    boolean updateTeacherOccupation(String occupation, String teachername);


    //    获得教室的占用情况
    @Select("select occupied from classroom where class_number=#{classNumber}")
    String getClassroomOccupied(String classNumber);

    //    获得老师的时间
    @Select("select occupation from teacher where name=#{teachername}")
    String getTeacherOccupied(String teachername);
}
