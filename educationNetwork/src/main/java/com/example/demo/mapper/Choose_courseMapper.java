package com.example.demo.mapper;

import com.example.demo.domain.GpaEntity;
import com.example.demo.domain.ScoreEntity;
import com.example.demo.domain.Score_excel;
import com.example.demo.domain.StudentScore;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface Choose_courseMapper {
    //查询学号为student_number的同学的课程成绩
    @Select("select course.name,choose_course.score,choose_course.gpa from choose_course,course where choose_course.student_number=#{student_number} and choose_course.course_number=course.number")
    public List<ScoreEntity> score_query(String student_number);

    //查询学号为student_number的同学所选的课程中课程名中含有course_name字段的课程成绩(好像不让函数重载，一重载启动的时候就报错......)
    @Select("select course.name,choose_course.score,choose_course.gpa from choose_course,course where choose_course.student_number=#{student_number} and choose_course.course_number=course.number and course.name like #{course_name}")
    public List<ScoreEntity> score_query_course(String student_number,String course_name);

    //查询学号为student_number的GPA
    @Select("select choose_course.gpa,course.credits from choose_course,course where choose_course.student_number=#{student_number} and choose_course.course_number=course.number")
    public List<GpaEntity> gpa_query(String student_number);

    //导入学生成绩
    @Update("update choose_course set score=#{Score}, gpa=#{gpa} where student_number=#{student_number} and course_number=#{coursenumber}")
    public void setScore(Score_excel score_excel);

    //查询选了课程号为coursenumber的所有学生的成绩及学生信息
    @Select("select student.number,student.name,student.class,course.name,choose_course.score,choose_course.gpa from choose_course,student,course where choose_course.course_number=course.number and choose_course.student_number=student.number and choose_course.course_number=#{coursenumber}")
    public List<StudentScore> studentScore_query(String coursenumber);
}
