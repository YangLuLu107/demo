package com.example.demo;

import com.example.DemoApplication;
import com.example.pojo.IndexCode;
import com.example.service.IndexCodeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
class DemoApplicationTests {

    @Resource
    IndexCodeService indexCodeService;
    @Test
    void contextLoads() {

    }

    @Test
    public void getList() {
        List<IndexCode> list = indexCodeService.getList();
    }

}
