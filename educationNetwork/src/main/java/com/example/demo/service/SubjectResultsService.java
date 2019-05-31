package com.example.demo.service;

import com.example.demo.domain.SubjectResults;

public interface SubjectResultsService {
    public boolean updateResult(int result,String evaluate,  String studentNumber);
    public boolean submit(String title, String content, String studentNumber);
    public int selectResult(String studentNumber);
    public SubjectResults selectByStudent(String studentNumber);
    public boolean updateStatus(int status, String studentNumber);
    public boolean insert(String studentNumber, String courseNumber);
    public SubjectResults[] selectBySerialnumber(String serialnumber);
}
