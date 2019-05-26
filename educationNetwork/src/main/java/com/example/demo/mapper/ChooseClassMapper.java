package com.example.demo.mapper;


import com.example.demo.domain.ChooseClass;
import com.example.demo.domain.Course_for_choose_class__;
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
    //    @Select("select number,name,departments,course.status,people,max_number,location,teacher_number,introduce,type,credits,occupation,time,begin,grade,volunteer,choose_course.status form  course left join choose_course on student_number=#{student_number} and choose_course.course_number=course.number")
    //    public Course_for_choose_class__ getStatus(@Param("student_number") String student_number, @Param("course_number") String course_number);
    //@Select("select course.*,choose_course.choosestatus,teacher.name from course inner join choose_course on student_number=#{student_number} and choose_course.course_number=course.number inner join teacher on teacher.number=course.number WHERE choose_course.choosestatus=1" )
    @Select("select course.*,choose_course.choosestatus,teacher.name as teachername from course inner join choose_course on choose_course.course_number=course.number left join teacher on teacher.number=course.teacher_number WHERE choose_course.choosestatus=1 and student_number=#{student_number}")
    public List<Course_for_choose_class__> getStatus(@Param("student_number") String student_number);
    //@Select("select course.number,course.name,course.departments,course.status,course.people,course.max_number,course.location,course.teacher_number,course.introduce,course.type,course.credits,course.occupation,course.time,course.begin,course.grade,course.volunteer,course.end,choose_course.status form  course inner join choose_course on student_number=#{student_number} and choose_course.course_number=course.number and course_number=#{course_number}")
    //public Course_for_choose_class__ getStatus(@Param("student_number") String student_number, @Param("course_number") String course_number);
    @Update("update choose_course set choosestatus=0 where student_number=#{student_number} and course_number=#{course_number} ")
    public int changeStatus(@Param("student_number") String student_number, @Param("course_number") String course_number);
    @Select("select * from choose_course where student_number=#{student_number} and course_number=#{course_number} ")
    ChooseClass findChooseClass(@Param("student_number") String student_number, @Param("course_number") String course_number);
    @Update("update choose_course set choosestatus=1 where student_number=#{student_number} and course_number=#{course_number}")
    public int changeStatusone(@Param("student_number") String student_number, @Param("course_number") String course_number);
    @Select("select course.*,choose_course.choosestatus,teacher.name as teachername from course left join choose_course on student_number=#{student_number} and choose_course.course_number=course.number left join teacher on teacher.number=course.teacher_number " )
    public List<Course_for_choose_class__> getAll(@Param("student_number") String student_number);
    @Select("select course.*,choose_course.choosestatus,teacher.name as teachername from course left join choose_course on choose_course.course_number=course.number left join teacher on teacher.number=course.teacher_number WHERE course.number=#{number}" )
    public Course_for_choose_class__ getsingle(@Param("number") String number);
}
