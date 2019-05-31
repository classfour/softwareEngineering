package com.example.demo.service;

import com.example.demo.domain.Course;
import com.example.demo.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseService {

    public List<Course> getUserByCardno();
    public List<String> selectnumber();
    public Course getCourseByNumber( String number);
    public int updateStatusByNumber(String number,int status);
    public String getoccupation(String student_number);
}
