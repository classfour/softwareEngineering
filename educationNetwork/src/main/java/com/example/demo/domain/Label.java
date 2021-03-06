package com.example.demo.domain;

/**
* @Description: 标签实体
* @Author: klx
* @Date: 2019/4/17
*/
public class Label {
    private int Id;//Id值

    private String name;//标签名

    private String course_number;//下属课题编号

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseNumber() {
        return course_number;
    }

    public void setCourseNumber(String courseNumber) {
        this.course_number = courseNumber;
    }

//    public String getCourse_number() {
//        return course_number;
//    }
//
//    public void setCourse_number(String course_number) {
//        this.course_number = course_number;
//    }
}
