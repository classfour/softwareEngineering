package com.example.demo.mapper;

import com.example.demo.domain.GpaEntity;
import com.example.demo.domain.ScoreEntity;
import com.example.demo.domain.Score_excel;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface Choose_courseMapper {
    //查询学号为student_number的同学的选课情况
    @Select("select course.name,choose_course.score,choose_course.gpa from choose_course,course where choose_course.student_number=#{student_number} and choose_course.course_number=course.number")
    public List<ScoreEntity> score_query(String student_number);

    @Select("select choose_course.gpa,course.credits from choose_course,course where choose_course.student_number=#{student_number} and choose_course.course_number=course.number")
    public List<GpaEntity> gpa_query(String student_number);

    @Update("update choose_course set score=#{Score}, gpa=#{gpa} where student_number=#{student_number} and course_number=#{coursenumber}")
    public void setScore(Score_excel score_excel);
}
