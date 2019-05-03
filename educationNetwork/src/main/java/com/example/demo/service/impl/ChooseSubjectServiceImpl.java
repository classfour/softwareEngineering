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

    @Override
    public String[] selectStudent(String courseNumber) {
        ChooseSubject[] chooseSubjects = chooseSubjectMapper.select(courseNumber);
        String[] student = new String[chooseSubjects.length];
        for(int i = 0; i < chooseSubjects.length; i++) {
            student[i] = chooseSubjects[i].getStudentNumber();
        }

        return student;
    }

    @Override
    public ChooseSubject[] selectByStudent(String studentNumber) {
        return chooseSubjectMapper.selectByStudent(studentNumber);
    }

    @Override
    public boolean delete(String courseNumber, String studentNumber) {
        return chooseSubjectMapper.delete(courseNumber, studentNumber);
    }
}
