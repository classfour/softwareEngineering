package com.example.demo.domain;

/**
* @Description: 毕设课题实体
* @Author: klx
* @Date: 2019/4/17
*/
public class GraduationSubject {
    private String serialnumber;//课题编号

    private String name;//课题名称

    private String introduce;//课题介绍

    private int status;//课题状态

    private int max;//课题最大容量

    private String teacher_number;//教师编号

    private int number;//已选人数

    private int max_number;//课题最大可选人数

    private int now_number;

    private String teacher;

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getNowNumber() {
        return now_number;
    }

    public void setNowNumber(int nowNumber) {
        this.now_number = nowNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getTeacher_number() {
        return teacher_number;
    }

    public void setTeacher_number(String teacher_number) {
        this.teacher_number = teacher_number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getMax_number() {
        return max_number;
    }

    public void setMax_number(int max_number) {
        this.max_number = max_number;
    }

    public String getTeacherNumber() {
        return teacher_number;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacher_number = teacherNumber;
    }

    public int getMaxNumber() {
        return max_number;
    }

    public void setMaxNumber(int maxNumber) {
        this.max_number = maxNumber;
    }

}
