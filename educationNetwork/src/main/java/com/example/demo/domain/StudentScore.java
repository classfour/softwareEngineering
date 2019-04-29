package com.example.demo.domain;

import javax.xml.crypto.Data;
import java.util.Date;

public class StudentScore {
    public StudentScore(String studentnumber, String studentname, String studentclass, String coursename, String score_tmp,double gpa) {
        this.studentnumber = studentnumber;
        this.studentname = studentname;
        this.studentclass = studentclass;
        this.coursename = coursename;
        this.score_tmp = score_tmp;
        Score=new ScoreEntity(coursename,score_tmp,gpa,"2016-2017-1");
    }

    //学生学号
    private String studentnumber;

    //学生姓名
    private String studentname;

    //学生班级
    private String studentclass;

    //课程名称
    private String coursename;

    //学生成绩(数据库中查询出来的暂时的varchar类型的包含平时成绩，期中成绩，期末成绩的混合成绩)
    private String score_tmp;

    //学生成绩实体(之前已经写好，包含了学生基本课程成绩)
    private ScoreEntity Score;

    public String getStudentnumber() {
        return studentnumber;
    }

    public String getStudentname() {
        return studentname;
    }

    public String getStudentclass() {
        return studentclass;
    }

    public String getCoursename() {
        return coursename;
    }

    public ScoreEntity getScore() {
        return Score;
    }
}
