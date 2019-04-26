package com.example.demo.service;

import com.example.demo.domain.GpaEntity;
import com.example.demo.domain.ScoreEntity;
import com.example.demo.domain.Score_excel;
import com.example.demo.domain.StudentScore;

import java.util.List;

public interface Choose_courseService {
    public List<ScoreEntity> Score_query(String student_number);

    public List<ScoreEntity> Score_query_course(String student_number,String course_name);

    public List<GpaEntity> Gpa_query(String student_number);

    public void SetScore(Score_excel score_excel);

    public List<StudentScore> studentScore_Query(String coursenumber);
}
