package com.example.demo.service;

import com.example.demo.domain.GraduationSubject;

public interface GraduationSubjectService {
    public GraduationSubject getSubject(String serialnumber);
    public boolean insertSubject(GraduationSubject graduationSubject);
    public boolean updateContent(String name, String introduce, String serialnumber);
    public GraduationSubject selectByNumber(String teacherNumber);
    public boolean updateNumber(int number, String serialnumber);
}
