package com.example.demo.domain;

public class Course_imformation {
    public Course_imformation(String course_number, String course_name) {
        this.course_number = course_number;
        this.course_name = course_name;
    }

    //课程编号
    private String  course_number;

    //课程名称
    private String course_name;

    public String getCourse_number() {
        return course_number;
    }

    public String getCourse_name() {
        return course_name;
    }
}
