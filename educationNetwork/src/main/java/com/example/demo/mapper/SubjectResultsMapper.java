package com.example.demo.mapper;

import com.example.demo.domain.SubjectResults;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SubjectResultsMapper {
    @Insert("insert into subject_results values(#{studentNumber}, #{courseNumber}, #{title}, #{content}, #{result}, #{status}, #{evaluate})")
    public boolean insertResults(SubjectResults subjectResults);
    @Update("update subject_results set result = #{result} where student_number = #{studentNumber}")
    public boolean updateResult(int result, String studentNumber);
    @Update("update subject_results set title=#{title}, content=#{content} where student_number = #{studentNumber}")
    public boolean submit(String title, String content, String studentNumber);
    @Select("select result from subject_results where student_number = #{studentNumber}")
    public int selectResult(String studentNumber);
    @Select("select * from subject_results where student_number = #{studentNumber}")
    public SubjectResults selectByStudent(String studentNumber);
    @Update("update subject_results set status = #{status} where student_number = #{studentNumber}")
    public boolean updateStauts(int status, String studentNumber);
    @Select("select * from subject_results where course_number=#{courseNumber}")
    public SubjectResults[] selectBySerialnumber(String courseNumber);
}
