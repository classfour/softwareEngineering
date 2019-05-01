package com.example.demo.service;

import com.example.demo.domain.Label;

public interface LabelService {
    public String[] getSubjectNumber(String label);
    public Label[] getAllLabel();
    public boolean updateLabel(String courseNumber ,int id);
    public Label selectById(int id);
}
