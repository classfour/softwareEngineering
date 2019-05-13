package com.example.demo.mapper;

import com.example.demo.domain.Label;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* @Description: 标签表接口 
* @Author: klx 
* @Date: 2019/4/17 
*/
public interface LabelMapper {
    @Select("select * from label where name = #{name}")
    public Label select(String name);
    @Select("select * from label")
    public Label[] getAllLabel();
    @Update("update label set course_number = #{courseNumber} where id = #{id}")
    public boolean update(String courseNumber, int id);
    @Select("select * from label where id=#{id}")
    public Label selectById(int id);
}
