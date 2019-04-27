package com.example.demo.service.impl;

import com.example.demo.domain.Label;
import com.example.demo.mapper.LabelMapper;
import com.example.demo.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl implements LabelService {
    @Autowired
    LabelMapper labelMapper;

    public String getSubjectNumber(String name) {
        Label label = labelMapper.select(name);
//        System.out.println(label.getId());
//        System.out.println(label.getCourseNumber());
//        System.out.println(label.getName());
        return label.getCourseNumber();
    }
}
