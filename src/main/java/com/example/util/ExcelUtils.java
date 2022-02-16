package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.example.pojo.TestPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExcelUtils {

    private final static Logger log = LoggerFactory.getLogger(ExcelUtils.class);

    // 导入excel
    public static Map<String, List<TestPojo>> importExcel(File file) {
        Map<String, List<TestPojo>> map = new LinkedHashMap<>();
        Workbook wb = null;

        // 判断是xls还是xlxs
        try {
            if (file == null) {
                return null;
            } else if (file.getPath().equals("xls")) {
                wb = new HSSFWorkbook(new FileInputStream(file));
            } else {
                wb = new XSSFWorkbook(new FileInputStream(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        for (int i = 0; i < 6; i++) {
            List<TestPojo> list = new ArrayList<>();
            Sheet sheet = wb.getSheetAt(i);
            String sheetName = sheet.getSheetName();
            for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {
                Row row = sheet.getRow(j);
                if (row == null) {
                    break;
                }
                TestPojo testPojo = new TestPojo();
                testPojo.setYEAR((int) row.getCell(0).getNumericCellValue());
                testPojo.setPE1(formatInt(row.getCell(1).getNumericCellValue()));
                testPojo.setPE2(formatInt(row.getCell(2).getNumericCellValue()));
                testPojo.setPE3(formatInt(row.getCell(3).getNumericCellValue()));
                testPojo.setETM1(formatInt(row.getCell(4).getNumericCellValue()));
                testPojo.setETM2(formatInt(row.getCell(5).getNumericCellValue()));
                testPojo.setETM3(formatInt(row.getCell(6).getNumericCellValue()));
                testPojo.setSE1(formatDouble(row.getCell(7).getNumericCellValue()));
                testPojo.setSE2(formatDouble(row.getCell(8).getNumericCellValue()));
                testPojo.setSE3(formatDouble(row.getCell(9).getNumericCellValue()));
                testPojo.setYP(formatInt(row.getCell(10).getNumericCellValue()));
                list.add(testPojo);
            }
            map.put(sheetName, list);
        }
        return map;
    }

    // 导出excel
    public static void exportExcel(Map<String, List<TestPojo>> map, String exportPath) {
//        String[] title = {"第一阶段降水量", "第二阶段降水量", "第三阶段降水量", "第一阶段需水量", "第二阶段需水量", "第三阶段需水量", "第一阶段灌溉量", "第二阶段灌溉量", "第三阶段灌溉量", "灌溉总量",
//                "第一阶段敏感指数", "第二阶段敏感指数", "第三阶段敏感指数", "潜在产量", "雨养产量"};
        String[] title = {"mark", "PE1", "PE2", "PE3", "ETM1", "ETM2", "ETM3", "DE1", "DE2", "DE3",
                "DE", "SE1", "SE2", "SE3", "YP", "YR", "YR/DE"};
        Workbook wb = new XSSFWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        for (String key : map.keySet()) {
            Sheet sheet = wb.createSheet(key);
            Row titleRow = sheet.createRow(0);
            Cell titleCell = null;

            for (int k = 0; k < title.length; k++) {
                titleCell = titleRow.createCell(k);
                titleCell.setCellValue(title[k]);
                titleCell.setCellStyle(cellStyle);
            }

            List<TestPojo> list = map.get(key);
            for (int j = 0; j < list.size(); j++) {
                String YR = String.valueOf(list.get(j).getYR() == null ? "" : Math.round(list.get(j).getYR()));
                String DE = String.valueOf(list.get(j).getDE() == null ? "" : list.get(j).getDE());
                Row row = sheet.createRow(j + 1);
                row.createCell(0).setCellValue(list.get(j).getYEAR());
                row.createCell(1).setCellValue(list.get(j).getPE1());
                row.createCell(2).setCellValue(list.get(j).getPE2());
                row.createCell(3).setCellValue(list.get(j).getPE3());
                row.createCell(4).setCellValue(list.get(j).getETM1());
                row.createCell(5).setCellValue(list.get(j).getETM2());
                row.createCell(6).setCellValue(list.get(j).getETM3());
                row.createCell(7).setCellValue(String.valueOf(list.get(j).getDE1() == null ? "" : list.get(j).getDE1()));
                row.createCell(8).setCellValue(String.valueOf(list.get(j).getDE2() == null ? "" : list.get(j).getDE2()));
                row.createCell(9).setCellValue(String.valueOf(list.get(j).getDE3() == null ? "" : list.get(j).getDE3()));
                row.createCell(10).setCellValue(DE);
                row.createCell(11).setCellValue(list.get(j).getSE1());
                row.createCell(12).setCellValue(list.get(j).getSE2());
                row.createCell(13).setCellValue(list.get(j).getSE3());
                row.createCell(14).setCellValue(list.get(j).getYP());
                row.createCell(15).setCellValue(YR);
                if (StringUtils.isBlank(YR) || StringUtils.isBlank(DE)) {
                    row.createCell(16).setCellValue("");
                } else {
                    row.createCell(16).setCellValue(formatDouble2(Double.valueOf(YR) / Double.valueOf(DE)));
                }

            }
        }


        // 生成文件
        File file = new File(exportPath);
        try {
            wb.write(new FileOutputStream(file));
//            if (file.exists()) {
//                file.delete();
//            } else {
//                    wb.write(new FileOutputStream(file));
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int formatInt(Double value) {
        return (int) Math.round(value);
    }

    private static double formatDouble(Double value) {
        DecimalFormat df = new DecimalFormat("#.000");
        String format = df.format(value.doubleValue());
        return Double.valueOf(format);
    }

    private static double formatDouble2(Double value) {
        DecimalFormat df = new DecimalFormat("#.00");
        String format = df.format(value.doubleValue());
        return Double.valueOf(format);
    }
}