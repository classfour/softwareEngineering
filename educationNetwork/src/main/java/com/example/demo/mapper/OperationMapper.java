package com.example.demo.mapper;

import com.example.demo.domain.Operation;
import org.apache.ibatis.annotations.Select;

public interface OperationMapper {
    @Select("select * from operation where type=#{type} and content=#{content}")
    public Operation[] select(int type, String content);
}
