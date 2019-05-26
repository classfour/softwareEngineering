package com.example.demo.service.impl;

import com.example.demo.domain.Notice;
import com.example.demo.mapper.NoticeMapper;
import com.example.demo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;
    @Override
    public Notice[] getAllNotice() {
        return noticeMapper.select();
    }
}
