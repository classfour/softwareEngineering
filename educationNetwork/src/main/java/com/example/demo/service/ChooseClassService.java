package com.example.demo.service;

import com.example.demo.domain.ChooseClass;
import com.example.demo.domain.User;
import com.example.demo.mapper.ChooseClassMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ChooseClassService {
    public ChooseClass findstatus(String student_number,String course_number);
    public boolean insertval(ChooseClass chooseClass);
    public String getStudent_number(String course_number);
    public List<ChooseClass> getChooseClassByStunum(String student_number);
    public List<String> getCounumByStunum(String student_number);
    public boolean deleteevent(String student_number, String course_number);

}
