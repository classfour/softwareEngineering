package com.example.demo.mapper;

import com.example.demo.domain.SubjectResults;
import org.apache.ibatis.annotations.Insert;

public interface SubjectResultsMapper {
    @Insert("insert into subject_results values(#{studentNumber}, #{courseNumber}, #{title}, #{content}, #{result}, #{status})")
    public boolean insertResults(SubjectResults subjectResults);
}
