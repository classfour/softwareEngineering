package com.example.demo.domain;
import java.util.*;
//新建实体性成绩
public class ScoreRank{
    public ScoreRank(String coursename,String coursenum,String score,String student_num,double gpa,String study_year){
        this.coursename=coursename;
        this.stu_num=student_num;
        this.coursenum=coursenum;
        this.stu_gpa=gpa;
        this.stu_scoretemp=score;
        this.stu_score=new ScoreEntity(coursename,score,gpa,study_year);
        this.stu_total=stu_score.getTotal();
        this.study_year=study_year;
    }

    //课程名
    private String coursename;
    //学号
    private String stu_num;
    //课程号
    private String coursenum;
    //GPA
    private double stu_gpa;
    //混杂的成绩
    private String stu_scoretemp;
    //学生实体，用来求课程的总分
    private ScoreEntity stu_score;
    //学生单科课程的总分
    private double stu_total;
    //修课时间
    private String study_year;
    public String getCoursename() {
        return coursename;
    }
    public double getStu_total(){return stu_total;}
    public String getStu_num()
    {
        return stu_num;
    }
}
