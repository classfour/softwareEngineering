package com.example.demo.service.impl;

import com.example.demo.domain.ChooseSubject;
import com.example.demo.domain.GraduationSubject;
import com.example.demo.domain.SubjectResults;
import com.example.demo.mapper.ChooseSubjectMapper;
import com.example.demo.mapper.GraduationSubjectMapper;
import com.example.demo.mapper.SubjectResultsMapper;
import com.example.demo.service.ChooseSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChooseSubjectServiceImpl implements ChooseSubjectService {
    @Autowired
    ChooseSubjectMapper chooseSubjectMapper;
    public boolean insertChoose(ChooseSubject chooseSubject) {
//        System.out.println(chooseSubject.getCourseNumber());
//        System.out.println(chooseSubject.getStudentNumber());
        return chooseSubjectMapper.insert(chooseSubject);
    }
    public int selectChoose(String courseNumber) {
        ChooseSubject[] chooseArray =  chooseSubjectMapper.select(courseNumber);
        return chooseArray.length;
    }
}
