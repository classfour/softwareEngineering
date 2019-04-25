package com.example.demo.service;

import com.example.demo.domain.GpaEntity;
import com.example.demo.domain.ScoreEntity;
import com.example.demo.domain.Score_excel;

import java.util.List;

public interface Choose_courseService {
    public List<ScoreEntity> Score_query(String student_number);

    public List<GpaEntity> Gpa_query(String student_number);

    public void SetScore(Score_excel score_excel);
}