package com.example.demo.service.impl;

import com.example.demo.domain.GraduationSubject;
import com.example.demo.mapper.GraduationSubjectMapper;
import com.example.demo.service.GraduationSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraduationSubjectServiceImpl implements GraduationSubjectService {
    @Autowired
    private GraduationSubjectMapper graduationSubjectMapper;
    @Override
    public GraduationSubject getSubject(String serialnumber) {
        GraduationSubject graduationSubject = graduationSubjectMapper.select(serialnumber);
        return graduationSubject;
    }

    @Override
    public boolean insertSubject(GraduationSubject graduationSubject) {
        return graduationSubjectMapper.insert(graduationSubject);
    }

    @Override
    public boolean updateContent(String name, String introduce, String serialnumber) {
        return graduationSubjectMapper.updateContent(name, introduce, serialnumber);
    }

    @Override
    public GraduationSubject selectByNumber(String teacherNumber) {
        return graduationSubjectMapper.selectByNumber(teacherNumber);
    }

    @Override
    public boolean updateNumber(int number, String serialnumber) {
        return graduationSubjectMapper.updateNumber(number, serialnumber);
    }

    @Override
    public GraduationSubject[] selectAll() {
        return graduationSubjectMapper.selectAll();
    }

    @Override
    public boolean updateNwoNumber(int nowNumber, String serialnumber) {
        return graduationSubjectMapper.updateNowNumber(nowNumber, serialnumber);
    }

}
