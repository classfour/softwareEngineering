package com.example.demo.service.impl;

import com.example.demo.domain.Teacher;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Override
    public Teacher selectByNumber(String number) {
        return teacherMapper.selectByNumber(number);
    }
}
