package com.example.demo.mapper;


import com.example.demo.domain.GraduationSubject;
import org.apache.ibatis.annotations.Select;

/**
* @Description: 毕设课题表接口
* @Author: klx
* @Date: 2019/4/17
*/
public interface GraduationSubjectMapper {
    @Select("select * from graduation_subject where serialnumber = #{serialnumber}")
    public GraduationSubject select(String serialnumber);
}
