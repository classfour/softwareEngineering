package com.example.demo.domain;

public class GetStudentCourseNumber {
    private String stu_num;
    private String coursenumber;
    public GetStudentCourseNumber(String coursenumber,String stu_num)
    {
        this.stu_num=stu_num;
        this.coursenumber=coursenumber;
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
}
