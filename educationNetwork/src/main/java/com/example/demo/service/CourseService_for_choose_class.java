package com.example.demo.service;

import com.example.demo.domain.Course_for_choose_class__;

import java.util.List;

public interface CourseService_for_choose_class {

    public List<Course_for_choose_class__> getUserByCardno();
    public List<String> selectnumber();
    public Course_for_choose_class__ getCourseByNumber(String number);
    public int updateStatusByNumber(String number, int status);
    public String getoccupation(String student_number);
    public int upadtePeopleByNumber(int people, String number);
    public Course_for_choose_class__[] selectCourseRepaint(String grade_201x, String major, String departments, String type, String people);
}
