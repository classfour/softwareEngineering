package com.example.demo.mapper;


import com.example.demo.domain.ChooseClass;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ChooseClassMapper {

    @Select("select * from choose_course where student_number=#{student_number} and course_number=#{course_number} order by student_number")
    ChooseClass findstatus(@Param("student_number") String student_number, @Param("course_number") String course_number);
    @Insert("insert into choose_course(student_number, course_number,score) values(#{student_number}, #{course_number},100)")
    public boolean insertval(ChooseClass chooseClass);
    @Select("select student_number from choose_course where course_number=#{course_number}")
    String getStudent_number(@Param("course_number") String course_number);
    @Select("select * from choose_course where student_number=#{student_number}")
    List<ChooseClass> getChooseClassByStunum(String student_number);
    @Select("select course_number from choose_course where student_number=#{student_number}")
    List<String> getCounumByStunum(String student_number);
    @Delete("delete from choose_course where student_number=#{student_number} and course_number=#{course_number}")
    public boolean deleteevent(@Param("student_number") String student_number, @Param("course_number") String course_number);

}
