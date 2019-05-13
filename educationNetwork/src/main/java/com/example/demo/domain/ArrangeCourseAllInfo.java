package com.example.demo.domain;

public class ArrangeCourseAllInfo {

    private String teacher_number;
    private String course;
    private String week;
    private String detail;
    private String class_number;

    public String getTeacherName() {
        return teacher_number;
    }

    public void setTeacherName(String teacherName) {
        this.teacher_number = teacherName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getClass_number() {
        return class_number;
    }

    public void setClass_number(String class_number) {
        this.class_number = class_number;
    }


}
