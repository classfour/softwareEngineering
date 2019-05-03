package com.example.demo.service;

import com.example.demo.domain.ChooseSubject;

public interface ChooseSubjectService {
    public boolean insertChoose(ChooseSubject chooseSubject);
    public int selectChoose(String courseNumber);
    public String[] selectStudent(String courseNumber);
    public ChooseSubject[] selectByStudent(String studentNumber);
    public boolean delete(String courseNumber, String studentNumber);
}
