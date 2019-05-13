package com.example.demo.service.impl;

import com.example.demo.domain.GraduationSubject;
import com.example.demo.mapper.GraduationSubjectMapper;
import com.example.demo.service.GraduationSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraduationSubjectServiceImpl implements GraduationSubjectService {
    @Autowired
    private GraduationSubjectMapper graduationSubjectMapper;
    @Override
    public GraduationSubject getSubject(String serialnumber) {
        GraduationSubject graduationSubject = graduationSubjectMapper.select(serialnumber);
        return graduationSubject;
    }
}
