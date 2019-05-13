package com.example.demo.domain;

public class ArrangeCourseTeacherEntity {
    private String name;
    private String enable_teacher_courses;
    private String occupation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnable_teach_courses() {
        return enable_teacher_courses;
    }

    public void setEnable_teach_courses(String enable_teach_courses) {
        this.enable_teacher_courses = enable_teach_courses;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
