package com.example.demo.domain;

public class ChooseClass {
    private int id;
    private  String student_number;
    private  String course_number;
    private  int score;
    private  int status;
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public void setCourse_number(String course_number) {
        this.course_number = course_number;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    private String datatime;


    public int getId() {
        return id;
    }

    public String getStudent_number() {
        return student_number;
    }

    public String getCourse_number() {
        return course_number;
    }

    public int getScore() {
        return score;
    }

    public String getDatatime() {
        return datatime;
    }


}
