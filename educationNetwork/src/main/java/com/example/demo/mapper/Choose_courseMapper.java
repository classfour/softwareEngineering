package com.example.demo.mapper;

import com.example.demo.domain.*;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface Choose_courseMapper {
    //查询学号为student_number的同学的课程成绩
    @Select("select course.name,choose_course.score,choose_course.gpa,choose_course.time from choose_course,course where choose_course.student_number=#{student_number} and choose_course.course_number=course.number and choose_course.score is not null and choose_course.gpa is not null")
    public List<ScoreEntity> score_query(String student_number);

    //查询学号为student_number的同学所选的课程中课程名中含有course_name字段的课程成绩(好像不让函数重载，一重载启动的时候就报错......)
    @Select("select course.name,choose_course.score,choose_course.gpa,choose_course.time from choose_course,course where choose_course.student_number=#{student_number} and choose_course.course_number=course.number and course.name like #{course_name} and choose_course.score is not null and choose_course.gpa is not null")
    public List<ScoreEntity> score_query_course(String student_number,String course_name);

    //查询学号为student_number的所选所有课程的GPA情况(已经出了成绩的课程)
    @Select("select choose_course.gpa,course.credits,choose_course.time from choose_course,course where choose_course.student_number=#{student_number} and choose_course.course_number=course.number and choose_course.score is not null and choose_course.gpa is not null")
    public List<GpaEntity> gpa_query(String student_number);

    //导入学生成绩
    @Update("update choose_course set score=#{Score}, gpa=#{gpa} where student_number=#{student_number} and course_number=#{coursenumber}")
    public void setScore(Score_excel score_excel);

    //查询选了课程号为coursenumber的所有学生的成绩及学生信息
    @Select("select student.number,student.name,student.class,course.name as course_name,choose_course.score,choose_course.time,choose_course.gpa from choose_course,student,course where choose_course.course_number=course.number and choose_course.student_number=student.number and choose_course.course_number=#{coursenumber} and choose_course.score is not null and choose_course.gpa is not null")
    public List<StudentScore> studentScore_query(String coursenumber);
    //蔡秉岐部分
    //查询单科课程成绩sql语句

    @Select("select course.name,course.number,choose_course.score,choose_course.student_number,choose_course.gpa,choose_course.time from choose_course,course where choose_course.course_number=course.number and choose_course.course_number=#{coursenumber} and choose_course.score is not null and choose_course.gpa is not null")
    public List<ScoreAll> Score_rank(String coursenumber);

    //查询该生所修课程
    @Select("select choose_course.course_number,choose_course.student_number,choose_course.time from choose_course where choose_course.student_number=#{student_number}")
    public List<GetStudentCourseNumber> Student_Course_Number(String student_number);

    //单科成绩模糊查询
    @Select("select course.name,choose_course.course_number,choose_course.score,choose_course.student_number,choose_course.gpa,choose_course.time from choose_course,course where  choose_course.course_number=course.number and course.name like #{course_name} and choose_course.score is not null and choose_course.gpa is not null")
    public List<ScoreAll> Score_single_rank(String course_name);

    //查询id为teacher_number所教授的所有课程信息
    @Select("select course.number,course.name from course where course.teacher_number=#{teacher_number}")
    public List<Course_imformation> Course_query(String teacher_number);


    //查询选了课程号为course_number的所有学生的学号
    @Select("select choose_course.student_number from choose_course where choose_course.course_number=#{course_number}")
    public List<String> student_number_query(String course_number);
}
