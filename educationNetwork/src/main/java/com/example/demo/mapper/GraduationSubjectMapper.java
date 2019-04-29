package com.example.demo.mapper;


import com.example.demo.domain.GraduationSubject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* @Description: 毕设课题表接口
* @Author: klx
* @Date: 2019/4/17
*/
public interface GraduationSubjectMapper {
    @Select("select * from graduation_subject where serialnumber = #{serialnumber}")
    public GraduationSubject select(String serialnumber);
    @Update("update graduation_subject set number=#{number} where serialnumber=#{serialnumber}")
    public boolean updateNumber(int number, String serialnumber);
    @Insert("insert into graduation_subject values(#{serialnumber}, #{name}, #{introduce}, #{status}, #{max}, #{teacherNumber}, #{number}, #{maxNumber})")
    public boolean insert(GraduationSubject graduationSubject);
    @Update("update graduation_subject set name=#{name}, introduce = #{introduce} where serialnumber = #{serialnumber}")
    public boolean updateContent(String name, String introduce, String serialnumber);
}
