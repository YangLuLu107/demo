package com.example.util;

import com.example.pojo.TestPojo;
import jdk.nashorn.internal.ir.Flags;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test01 {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\杨路路\\Desktop\\irri2.xlsx");
        String exportPath = "C:\\Users\\杨路路\\Desktop\\irri2导出-20.xlsx";
        Map<String, List<TestPojo>> map = ExcelUtils.importExcel(file);

        for (String key : map.keySet()) {
            List<TestPojo> testPojos = map.get(key);
            List<TestPojo> listAll = new ArrayList<>();
            for (TestPojo testPojo : testPojos) {
                List<TestPojo> jensen = Jensen.Jensen(testPojo);
                listAll.addAll(jensen);
            }
            map.put(key, listAll);
        }

        ExcelUtils.exportExcel(map, exportPath);
        System.out.println("导出成功！");
    }


}