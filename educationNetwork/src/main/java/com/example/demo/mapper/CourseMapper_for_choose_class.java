package com.example.demo.mapper;

import com.example.demo.domain.Course_for_choose_class__;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CourseMapper_for_choose_class {
    @Select("select * from course")
    List<Course_for_choose_class__> selectCourse();
    @Select("select number from course")
    List<String> selectnumber();
    @Select("select * from course where number=#{number}")
    Course_for_choose_class__ getCourseByNumber(@Param("number") String number);
    @Update("update course set status=#{status} where  number=#{number}")
    int updateStatusByNumber(@Param("number") String number, @Param("status") int status);
    @Select("select occupation from course where number=#{number}")
    String getoccupation(@Param("number") String number);
    @Update("update course set people=#{people} where number=#{number}")
    int upadtePeopleByNumber(@Param("people") int people, @Param("number") String number);
    @Select("select * from course WHERE " +
                "IF(''=#{grade_201x},1,grade_201x=#{grade_201x} ) " +
                "AND  IF(''=#{major},1,specialty=#{major} ) " +
                "AND  IF(''=#{departments},1,departments=#{departments} )" +
                "AND  IF(''=#{type},1,type=#{type} ) " +
                "AND  IF('1'=#{people},1,people>=max_number )"
        )
     Course_for_choose_class__[] selectCourseRepaint(@Param("grade_201x") String grade_201x,
                                                     @Param("major") String major,
                                                     @Param("departments") String departments,
                                                     @Param("type") String type,
                                                     @Param("people") String people
    );

}
