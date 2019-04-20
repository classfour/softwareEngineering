package com.example.demo.service.impl;

import com.example.demo.domain.ScoreEntity;
import com.example.demo.mapper.Choose_courseMapper;
import com.example.demo.service.Choose_courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Choose_courseServiceimpl implements Choose_courseService {
    @Autowired
    private Choose_courseMapper choose_courseMapper;

    public List<ScoreEntity> Score_query(String student_number){
        return choose_courseMapper.score_query(student_number);
    }

}
