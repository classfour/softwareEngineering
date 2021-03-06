package com.example.demo.domain;

/**
* @Description:    课题选择实体表
* @Author:         klx
* @CreateDate:     2019-04-28 14:37
*/
public class ChooseSubject {
    private int id;

    private String student_number;

    private String course_number;

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return student_number;
    }

    public void setStudentNumber(String studentNumber) {
        this.student_number = studentNumber;
    }

    public String getCourseNumber() {
        return course_number;
    }

    public void setCourseNumber(String courseNumber) {
        this.course_number = courseNumber;
    }
}
