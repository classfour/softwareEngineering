package com.example.demo.service.impl;

import com.example.demo.domain.GpaEntity;
import com.example.demo.domain.ScoreEntity;
import com.example.demo.domain.Score_excel;
import com.example.demo.domain.StudentScore;
import com.example.demo.mapper.Choose_courseMapper;
import com.example.demo.service.Choose_courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Choose_courseServiceimpl implements Choose_courseService {
    @Autowired
    private Choose_courseMapper choose_courseMapper;

    @Override
    public List<ScoreEntity> Score_query(String student_number){
        return choose_courseMapper.score_query(student_number);
    }

    @Override
    public List<GpaEntity> Gpa_query(String student_number){
        return choose_courseMapper.gpa_query(student_number);
    }

    @Override
    public void SetScore(Score_excel score_excel) {
        choose_courseMapper.setScore(score_excel);
    }

    @Override
    public List<StudentScore> studentScore_Query(String coursenumber){
        return choose_courseMapper.studentScore_query(coursenumber);
    }
}
