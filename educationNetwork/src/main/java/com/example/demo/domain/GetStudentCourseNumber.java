package com.example.demo.domain;

public class GetStudentCourseNumber {
    private String stu_num;
    private String coursenumber;
    private String study_year;

    public GetStudentCourseNumber(String coursenumber,String stu_num,String study_year)
    {
        this.stu_num=stu_num;
        this.coursenumber=coursenumber;
        this.study_year=study_year;
    }
    public String getStu_num()
    {
        return stu_num;
    }
    public String getCoursenumber()
    {
        return coursenumber;
    }
    public void setStu_num(String sn)
    {
        stu_num=sn;
    }
    public void setCoursenumber(String cn)
    {
        coursenumber=cn;
    }

    public String getStudy_year() {
        return study_year;
    }
}
