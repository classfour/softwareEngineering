package com.example.demo.domain;

public class Score_excel {
    public Score_excel(String[] sourse){
        coursenumber=sourse[0];
        student_number=sourse[1];
        score[0]=sourse[2];
        rate[0]=sourse[3];
        score[1]=sourse[4];
        rate[1]=sourse[5];
        score[2]=sourse[6];
        rate[2]=sourse[7];
        handle();
    }

    private void handle(){
        double s,r;
        double ans=0;
        int ans_tmp;
        Score=new String();
        for(int i=0;i<3;i++){
            Score+=score[i]+',';
            s=Double.valueOf(score[i]);
            r=Double.valueOf(rate[i]);
            ans+=s*r/100.0;
            if(i==2)
                Score+=rate[i];
            else
                Score+=rate[i]+',';
        }
        ans_tmp=(int)(ans+0.5);
        if(ans_tmp>=95){
            gpa=4.33;
        }
        else if(ans_tmp>=90&&ans_tmp<=94){
            gpa=4.00;
        }
        else if(ans_tmp>=85&&ans_tmp<=89){
            gpa=3.67;
        }
        else if(ans_tmp>=82&&ans_tmp<=84){
            gpa=3.33;
        }
        else if(ans_tmp>=78&&ans_tmp<=81){
            gpa=3.00;
        }
        else if(ans_tmp>=75&&ans_tmp<=77){
            gpa=2.67;
        }
        else if(ans_tmp>=72&&ans_tmp<=74){
            gpa=2.33;
        }
        else if(ans_tmp>=68&&ans_tmp<=71){
            gpa=2.00;
        }
        else if(ans_tmp>=67&&ans_tmp<=64){
            gpa=1.67;
        }
        else if(ans_tmp>=61&&ans_tmp<=63){
            gpa=1.33;
        }
        else if(ans_tmp==60){
            gpa=1.00;
        }
        else{
            gpa=0.00;
        }
    }

    //课程编号
    private String coursenumber;

    //学生学号
    private String student_number;

    //平时成绩，期中成绩，期末成绩
    private String[] score=new String[3];

    //平时成绩占比，期中成绩占比，期末成绩占比
    private String[] rate=new String[3];

    //最后的混合成绩
    private String Score;

    //课程gpa
    private double gpa;

    public String getCoursenumber() {
        return coursenumber;
    }

    public String getStudent_number() {
        return student_number;
    }

    public String getScore() {
        return Score;
    }

    public double getGpa() {
        return gpa;
    }
}
