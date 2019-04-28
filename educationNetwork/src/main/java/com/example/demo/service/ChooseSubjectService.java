package com.example.demo.service;

import com.example.demo.domain.ChooseSubject;

public interface ChooseSubjectService {
    public boolean insertChoose(ChooseSubject chooseSubject);
    public int selectChoose(String courseNumber);
}
