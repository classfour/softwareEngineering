package com.example.demo.domain;

public class GpaEntity {

    public GpaEntity(double gpa,double credit,String study_year){
        this.gpa=gpa;
        this.credit=credit;
        this.study_year=study_year;
    }

    //课程gpa
    private double gpa;

    //课程学分
    private double credit;

    //课程修读学年和学期，如2017-2018-1表示该课程在2017-2018学年第1学期修完
    private String study_year;

    public String getStudy_year() {
        return study_year;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
