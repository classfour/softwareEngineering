package com.example.demo.service.impl;

import com.example.demo.domain.*;
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
    public List<ScoreEntity> Score_query_course(String student_number,String course_name){
        return choose_courseMapper.score_query_course(student_number,course_name);
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
    //成绩排名查询
    @Override
    public List<ScoreAll> Score_All(String coursenumber)
    {
        return choose_courseMapper.Score_rank(coursenumber);
    }
    //该生所修课程查询
    @Override
    public List<GetStudentCourseNumber> Student_Course_Number(String student_number)
    {
        return choose_courseMapper.Student_Course_Number(student_number);
    }
    //单科成绩模糊查询
    @Override
    public List<ScoreAll> Score_single_rank(String coursenumber)
    {
        return choose_courseMapper.Score_single_rank(coursenumber);
    }
}
