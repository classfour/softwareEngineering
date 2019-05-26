package com.example.demo.mapper;

import com.example.demo.domain.Label;
import com.example.demo.domain.Notice;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface NoticeMapper {
    @Select("select * from notice")
    public Notice[] select();
}
