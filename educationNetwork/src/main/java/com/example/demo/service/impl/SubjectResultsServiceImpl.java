package com.example.demo.service.impl;

import com.example.demo.mapper.SubjectResultsMapper;
import com.example.demo.service.SubjectResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectResultsServiceImpl implements SubjectResultsService {
    @Autowired
    SubjectResultsMapper subjectResultsMapper;
    @Override
    public boolean updateResult(int result, String studentNumber) {
        return subjectResultsMapper.updateResult(result, studentNumber);
    }

    @Override
    public boolean submit(String title, String content, String studentNumber) {
        return subjectResultsMapper.submit(title, content, studentNumber);
    }

    @Override
    public int selectResult(String studentNumber) {
        return subjectResultsMapper.selectResult(studentNumber);
    }

    @Override
    public boolean updateStatus(int status, String studentNumber) {
        return subjectResultsMapper.updateStauts(status, studentNumber);
    }
}
