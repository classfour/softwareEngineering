package com.example.demo.service;

import com.example.demo.domain.Student;

public interface StudentService {
    public Student selectStudent(String number);
    public boolean updateStatus(int status, String number);
}
