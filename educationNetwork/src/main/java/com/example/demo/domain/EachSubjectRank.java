package com.example.demo.domain;
//返回单科成绩排名及课程名
public class EachSubjectRank {
    private String coursename;
    private int rank;
    public EachSubjectRank(String coursename,int rank)
    {
        this.coursename=coursename;
        this.rank=rank;
    }
    public String getCoursename()
    {
        return coursename;
    }
    public int getRank()
    {
        return rank;
    }
    public void setCoursename(String cn)
    {
        this.coursename=cn;
    }
    public void setRank(int r)
    {
        this.rank=r;
    }
}
