package com.example.demo.mapper;

import com.example.demo.domain.Label;
import org.apache.ibatis.annotations.Select;

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
}
