package com.example.service;

import com.example.mapper.IndexCodeMapper;
import com.example.pojo.IndexCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public interface IndexCodeService {
    public List<IndexCode> getList();
}
