package com.example.demo.service;

import com.example.demo.domain.ChooseClass;
import com.example.demo.domain.Course_for_choose_class__;

import java.util.List;


public interface ChooseClassService {
    public ChooseClass findstatus(String student_number,String course_number);
    public boolean insertval(ChooseClass chooseClass);
    public String getStudent_number(String course_number);
    public List<ChooseClass> getChooseClassByStunum(String student_number);
    public List<String> getCounumByStunum(String student_number);
    public boolean deleteevent(String student_number, String course_number);
    public List<Course_for_choose_class__> getStatus(String student_number);
    public int  changeStatus(String student_number,String course_number);
    public ChooseClass findChooseClass(String student_number,String course_number);
    public int  changeStatusone(String student_number,String course_number);
    public List<Course_for_choose_class__> getAll(String student_number);
    public Course_for_choose_class__ getsingle(String course_number);
}
