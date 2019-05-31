package com.example.demo.service;

import com.example.demo.domain.Student_for_choose_class__;

public interface StudentService_for_choose_class {
    public Student_for_choose_class__ selectStudent(String number);
    public boolean updateStatus(int status, String number);
    public int changeSchedule(String number, String Schedule);
}
