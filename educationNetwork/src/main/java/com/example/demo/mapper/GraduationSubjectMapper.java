package com.example.demo.mapper;


import com.example.demo.domain.GraduationSubject;
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
}
