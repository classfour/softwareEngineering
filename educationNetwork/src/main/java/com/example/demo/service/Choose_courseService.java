package com.example.demo.service;

import com.example.demo.domain.*;

import java.util.List;

public interface Choose_courseService {
    public List<ScoreEntity> Score_query(String student_number);

    public List<ScoreEntity> Score_query_course(String student_number,String course_name);

    public List<GpaEntity> Gpa_query(String student_number);

    public void SetScore(Score_excel score_excel);

    public List<StudentScore> studentScore_Query(String coursenumber);
    //成绩排名查询
    public List<ScoreAll> Score_All(String coursenumber);
    //该生所修课程的课程号
    public List<GetStudentCourseNumber> Student_Course_Number(String student_number);
    //单科成绩模糊查询
    public List<ScoreAll> Score_single_rank(String coursenumber);

    public List<Course_imformation> Course_query(String teacher_number);

    public List<String> student_number_query(String course_number);
}
