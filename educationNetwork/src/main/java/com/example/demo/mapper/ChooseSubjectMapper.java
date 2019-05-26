package com.example.demo.mapper;

import com.example.demo.domain.ChooseSubject;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
* @Description:    课题选择表操作接口
* @Author:         klx
* @CreateDate:     2019-04-28 14:30
*/
public interface ChooseSubjectMapper {
    @Insert("insert into choose_subject(student_number, course_number, status) values(#{studentNumber}, #{courseNumber}, 1)")
    public boolean insert(ChooseSubject chooseSubject);
//    @Delete("delete from choose_subject where ")
    @Select("select * from choose_subject where course_number=#{courseNumber} and status=1")
    public ChooseSubject[] select(String courseNumber);
    @Select("select * from choose_subject where student_number=#{studentNumber} and status=1")
    public ChooseSubject[] selectByStudent(String studentNumber);
    @Delete("delete from choose_subject where course_number=#{courseNumber} and student_number=#{studentNumber}")
    public boolean delete(String courseNumber, String studentNumber);
}
