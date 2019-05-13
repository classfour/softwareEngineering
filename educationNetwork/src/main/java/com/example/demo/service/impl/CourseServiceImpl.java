package com.example.demo.service.impl;

import com.example.demo.domain.Course;
import com.example.demo.mapper.CourseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements com.example.demo.service.CourseService {
    @Autowired CourseMapper ud;

    @Override
    public List<Course> getUserByCardno(){
        return ud.selectCourse();
    }
    @Override

    public List<String> selectnumber(){
        return ud.selectnumber();
    }

    @Override
    public Course getCourseByNumber(String number){
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
}
