package com.example.demo.domain;

import javax.xml.crypto.Data;
import java.util.Date;

public class StudentScore {
    public StudentScore(String studentnumber, String studentname, String studentclass, String coursename, String score_tmp,String study_year,double gpa) {
        this.studentnumber = studentnumber;
        this.studentname = studentname;
        this.studentclass = studentclass;
        this.score_tmp = score_tmp;
        Score=new ScoreEntity(coursename,score_tmp,gpa,study_year);
    }

    //学生学号
    private String studentnumber;

    //学生姓名
    private String studentname;

    //学生班级
    private String studentclass;

    //学生成绩(数据库中查询出来的暂时的varchar类型的包含平时成绩，期中成绩，期末成绩的混合成绩)
    private String score_tmp;

    //学生成绩实体(之前已经写好，包含了学生基本课程成绩)
    private ScoreEntity Score;

    public String getStudentnumber() { return studentnumber; }

    public String getStudentname() {
        return studentname;
    }

    public String getStudentclass() {
        return studentclass;
    }

    public ScoreEntity getScore() {
        return Score;
    }
}
