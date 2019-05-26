package com.example.demo.service.impl;

import com.example.demo.domain.ChooseClass;
import com.example.demo.domain.Course_for_choose_class__;
import com.example.demo.mapper.ChooseClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChooseClassServiceImpl implements com.example.demo.service.ChooseClassService{
    @Autowired ChooseClassMapper ud;
    @Override
    public ChooseClass findstatus(String student_number, String course_number){
        return ud.findstatus(student_number,course_number);
    }
    @Override
    public boolean insertval(ChooseClass chooseClass){
        return ud.insertval(chooseClass);
    }
    @Override
    public String getStudent_number(String course_number){
        return ud.getStudent_number(course_number);
    }
    @Override
    public List<ChooseClass> getChooseClassByStunum(String student_number){
        return ud.getChooseClassByStunum(student_number);
    }
    @Override
    public List<String > getCounumByStunum(String student_number){
        return ud.getCounumByStunum(student_number);
    }
    @Override
    public boolean deleteevent(String student_number, String course_number){
        return ud.deleteevent(student_number,course_number);
    }
    @Override
    public List<Course_for_choose_class__> getStatus(String student_number){
        return ud.getStatus(student_number);
    }
    @Override
    public int changeStatus(String student_number,String course_number){
        return ud.changeStatus(student_number,course_number);
    }
    @Override
    public ChooseClass findChooseClass(String student_number,String course_number){
        return ud.findChooseClass(student_number,course_number);
    }
    @Override
    public int  changeStatusone(String student_number,String course_number){
        return ud.changeStatusone(student_number,course_number);
    }
    @Override
    public List<Course_for_choose_class__> getAll(String student_number){
        return ud.getAll(student_number);
    }
    @Override
    public Course_for_choose_class__ getsingle(String course_number){
        return ud.getsingle(course_number);
    }
}
