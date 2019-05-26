package com.example.demo.service.impl;

import com.example.demo.domain.Student_for_choose_class__;
import com.example.demo.mapper.StudentMapper_for_choose_class;
import com.example.demo.service.StudentService_for_choose_class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService_for_choose_classImpl implements StudentService_for_choose_class {
    @Autowired
    StudentMapper_for_choose_class studentMapperForchooseclass;
    @Override
    public Student_for_choose_class__ selectStudent(String number) {
        return studentMapperForchooseclass.select(number);
    }

    @Override
    public boolean updateStatus(int status, String number) {
        return studentMapperForchooseclass.updateStatus(status, number);
    }
    @Override
    public int changeSchedule( String number, String schedule){
        return studentMapperForchooseclass.changeSchedule(number,schedule);
    }
}
