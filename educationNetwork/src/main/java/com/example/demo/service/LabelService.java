package com.example.demo.service;

import com.example.demo.domain.Label;

public interface LabelService {
    public String[] getSubjectNumber(String label, String[] number);
    public Label[] getAllLabel();
    public boolean updateLabel(String courseNumber ,int id);
    public Label selectById(int id);
    public Label selectBySubject(String serialnumber);
    public String[] selectByDepartment(String department, String[] number);
}
