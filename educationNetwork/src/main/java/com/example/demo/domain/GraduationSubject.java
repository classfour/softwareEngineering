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

    private String teacherNumber;//教师编号

    private int number;//已选人数

    private int maxNumber;//课题最大可选人数

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

    public int getNumber() {
        return number;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
