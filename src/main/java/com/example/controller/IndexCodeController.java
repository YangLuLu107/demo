package com.example.controller;

import com.example.pojo.IndexCode;
import com.example.service.IndexCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class IndexCodeController {
    @Resource
    private IndexCodeService indexCodeService;

    @GetMapping("getlist")
    public String getList() {
        List<IndexCode> list = indexCodeService.getList();
        return list.toString();
    }
}
