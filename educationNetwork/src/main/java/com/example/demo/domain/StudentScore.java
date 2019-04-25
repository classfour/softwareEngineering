package com.example.demo.domain;

public class StudentScore {
    public StudentScore(String studennumber, String studentname, String studentclass, String coursename, String score_tmp,double gpa) {
        this.studennumber = studennumber;
        this.studentname = studentname;
        this.studentclass = studentclass;
        this.coursename = coursename;
        this.score_tmp = score_tmp;
        Score=new ScoreEntity(coursename,score_tmp,gpa);
    }

    //学生学号
    private String studennumber;

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

    public String getStudennumber() {
        return studennumber;
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
}
