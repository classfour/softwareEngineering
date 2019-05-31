package com.example.demo.domain;

public class Course_for_choose_class__ {
    private String number;//课程编号
    private String name;//课程名字
    private String departments;//开课院系
    private int status;//课程状态
    private int people;//选课人数
    private  int max_number;//最大容量
    private String location;//上课地点
    private String teacher_number;//教师编号
    private String introduce;//课程介绍
    private String type;//课程类别
    private double credits;//学分
    private String occupation;//
   // private int time;//学时
    private String time;//学时
    private int begin;//起始周
    private int grade;//年级
    private int volunteer;//自选否
    private int end;
    public int  choosestatus;//是否选上这门课程
    public String teachername;//教授这门课程的老师的名字



    public int getChoose_class_status() {
        return choosestatus;
    }

    public void setChoose_class_status(int choose_class_status) {
        this.choosestatus = choose_class_status;
    }

    public int getChoosestatus() {
        return choosestatus;
    }

    public void setChoosestatus(int choosestatus) {
        this.choosestatus = choosestatus;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getVolunteer(){
        return volunteer;
    }
    public void setVolunteer(int volunteer){
        this.volunteer=volunteer;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getMax_number() {
        return max_number;
    }

    public void setMax_number(int max_number) {
        this.max_number = max_number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeacher_number() {
        return teacher_number;
    }

    public void setTeacher_number(String teacher_number) {
        this.teacher_number = teacher_number;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

//    public int getTime() {
//        return time;
//    }
//
//    public void setTime(int time) {
//        this.time = time;
//    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


        public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
