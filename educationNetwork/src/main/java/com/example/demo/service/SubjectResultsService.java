package com.example.demo.service;

public interface SubjectResultsService {
    public boolean updateResult(int result, String studentNumber);
    public boolean submit(String title, String content, String studentNumber);
    public int selectResult(String studentNumber);
    public boolean updateStatus(int status, String studentNumber);
}
