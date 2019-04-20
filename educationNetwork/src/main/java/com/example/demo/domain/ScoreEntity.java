package com.example.demo.domain;

public class ScoreEntity {
    public ScoreEntity(String coursename,String score,double gpa){
        this.coursename=coursename;
        this.score_tmp=score;
        this.gpa=gpa;
        handle();
    }

    //将混合的String类型的成绩转换为最终成绩
    //例如查询出来的"成绩"为:100,20,80,10,85,70
    //表明平时成绩100分，占比20%.期中成绩80分，占比10%，期末成绩85分，占比70%;
    private void handle(){
        int i=0,cnt=0;
        char ch;
        while(i<score_tmp.length()){
            while(i<score_tmp.length()&&score_tmp.charAt(i)!=','){
                ch=score_tmp.charAt(i);
                score[cnt]=score[cnt]*10.0+(double)(ch-'0');
                i++;
            }
            i++;
            while(i<score_tmp.length()&&score_tmp.charAt(i)!=','){
                ch=score_tmp.charAt(i);
                rate[cnt]=rate[cnt]*10.0+(double)(ch-'0');
                i++;
            }
            i++;
            cnt++;
        }
        double ans=0;
        for(i=0;i<3;i++){
            ans=ans+score[i]*rate[i]/100.0;
        }
        total=ans;
    }

    double score[]=new double[3];
    double rate[]=new double[3];

    private String coursename;

    private double total;

    private String score_tmp;

    private double gpa;

    //平时成绩查询接口
    public double getScoreUsual(){
        return score[0];
    }

    //平时成绩比例查询接口
    public double getScoreUsualRate(){
        return rate[0];
    }

    //期中成绩查询接口
    public double getScoreMid(){
        return score[1];
    }

    //期中成绩比例查询接口
    public double getScoreMidRate(){
        return rate[1];
    }

    //期末成绩查询接口
    public double getScoreFinal(){
        return score[2];
    }

    //期末成绩比例查询接口
    public double getScoreFinalRate(){
        return rate[2];
    }

    public double[] getScore() {
        return score;
    }

    public void setScore(double[] score) {
        this.score = score;
    }

    public double[] getRate() {
        return rate;
    }

    public void setRate(double[] rate) {
        this.rate = rate;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    //总成绩查询接口
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getScore_tmp() {
        return score_tmp;
    }

    public void setScore_tmp(String score_tmp) {
        this.score_tmp = score_tmp;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
