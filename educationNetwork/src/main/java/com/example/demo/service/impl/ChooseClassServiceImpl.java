package com.example.demo.service.impl;

import com.example.demo.domain.ChooseClass;
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

}
