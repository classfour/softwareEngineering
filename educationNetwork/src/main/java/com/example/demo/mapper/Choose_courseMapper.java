package com.example.demo.mapper;

import com.example.demo.domain.GpaEntity;
import com.example.demo.domain.ScoreEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Choose_courseMapper {
    //查询学号为student_number的同学的选课情况
    @Select("select course.name,choose_course.score,choose_course.gpa from choose_course,course where choose_course.student_number=#{student_number} and choose_course.course_number=course.number")
    public List<ScoreEntity> score_query(String student_number);

    @Select("select choose_course.gpa,course.credits from choose_course,course where choose_course.student_number=#{student_number} and choose_course.course_number=course.number")
    public List<GpaEntity> gpa_query(String student_number);
}
