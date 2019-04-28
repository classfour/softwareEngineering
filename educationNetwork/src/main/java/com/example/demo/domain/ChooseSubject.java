package com.example.demo.domain;

/**
* @Description:    课题选择实体表
* @Author:         klx
* @CreateDate:     2019-04-28 14:37
*/
public class ChooseSubject {
    private int id;

    private String studentNumber;

    private String courseNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }
}
