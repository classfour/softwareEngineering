package com.example.demo.domain;

public class GpaEntity {

    public GpaEntity(double gpa,double credit){
        this.gpa=gpa;
        this.credit=credit;
    }

    //课程gpa
    private double gpa;

    //课程学分
    private double credit;

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
