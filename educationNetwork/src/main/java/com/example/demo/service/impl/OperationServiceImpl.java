package com.example.demo.service.impl;

import com.example.demo.domain.Operation;
import com.example.demo.mapper.OperationMapper;
import com.example.demo.service.OperationService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {
    @Autowired
    OperationMapper operationMapper;
    @Override
    public int isSubjectOpen() {
        Operation[] close = operationMapper.select(2, "close");
        Operation[] open = operationMapper.select(2, "open");
        int closeLength = close.length;
        int openLength = open.length;
        if(closeLength+1==openLength) {
            return 1;
        }

        return 0;
    }
}
