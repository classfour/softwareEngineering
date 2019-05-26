package com.example.demo.service.impl;

import com.example.demo.domain.Course_for_choose_class__;
import com.example.demo.mapper.CourseMapper_for_choose_class;
import com.example.demo.service.CourseService_for_choose_class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService_for_choose_classclassImpl implements CourseService_for_choose_class {
    @Autowired
    CourseMapper_for_choose_class ud;

    @Override
    public List<Course_for_choose_class__> getUserByCardno(){
        return ud.selectCourse();
    }
    @Override

    public List<String> selectnumber(){
        return ud.selectnumber();
    }

    @Override
    public Course_for_choose_class__ getCourseByNumber(String number){
        return ud.getCourseByNumber(number);
    }

    @Override
    public int updateStatusByNumber(String number,int status){
        return ud.updateStatusByNumber(number,status);
    }
    @Override
    public String getoccupation(String student_number){
        return ud.getoccupation(student_number);
    }
    @Override
    public int upadtePeopleByNumber( int people,String number){
        return ud.upadtePeopleByNumber(people,number);
    }

        //0     年纪      2020
        //1     专业      环境工程
        //2     开设学院    化学工程学院
        //3     课程类别    创新创业教育
        //4     有无课程余量      有
    @Override
    public Course_for_choose_class__[] selectCourseRepaint(String grade_201x, String major, String departments, String type, String people){
        return ud.selectCourseRepaint(grade_201x, major, departments, type, people );
    }
}
