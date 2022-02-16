package com.example.util;

import com.example.pojo.TestPojo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class Jensen {
    public static int FLAG = 20;

    // 求雨养量
    public static List<TestPojo> Jensen(TestPojo testPojo) {
        List<TestPojo> list = new ArrayList<>();

        double pe1 = testPojo.getPE1().doubleValue();
        double pe2 = testPojo.getPE2().doubleValue();
        double pe3 = testPojo.getPE3().doubleValue();
        double etm1 = testPojo.getETM1().doubleValue();
        double etm2 = testPojo.getETM2().doubleValue();
        double etm3 = testPojo.getETM3().doubleValue();
        double se1 = testPojo.getSE1().doubleValue();
        double se2 = testPojo.getSE2().doubleValue();
        double se3 = testPojo.getSE3().doubleValue();
        double yp = testPojo.getYP().doubleValue();

        // 需水量为0则直接返回0
        if (etm1 == 0 || etm2 == 0 || etm3 == 0) {
            testPojo.setYR(0);
            list.add(testPojo);
            return list;
        }
        double first = Math.floor((etm1 - pe1) / FLAG);
        double second = Math.floor((etm2 - pe2) / FLAG);
        double third = Math.floor((etm3 - pe3) / FLAG);
        // 不同灌溉次数组合需要用不同的循环组合
        if (first > 0 && second > 0 && third > 0) { // 1 三个阶段都大于0
            // 第一第二第三阶段正常灌溉
            for (int i = 1; i < first + 1; i++) {
                for (int j = 1; j < second + 1; j++) {
                    for (int k = 1; k < third + 1; k++) {
                        TestPojo test = new TestPojo();
                        double yr = 0;
                        // Jensen计算公式
                        yr = yp * Math.pow((pe1 + i * FLAG) / etm1, se1);
                        yr = yr * Math.pow((pe2 + j * FLAG) / etm2, se2);
                        yr = yr * Math.pow((pe3 + k * FLAG) / etm3, se3);

                        BeanUtils.copyProperties(testPojo, test);
                        test.setDE1(i * FLAG);
                        test.setDE2(j * FLAG);
                        test.setDE3(k * FLAG);
                        test.setDE((i + j + k) * FLAG);
                        test.setYR((int) Math.round(yr));
                        list.add(test);
                    }
                }
            }
            // 第一阶段灌溉量为0
            for (int j = 1; j < second + 1; j++) {
                for (int k = 1; k < third + 1; k++) {
                    TestPojo test = new TestPojo();
                    double yr = 0;
                    // Jensen计算公式
                    yr = yp * Math.pow((pe1 + 0 * FLAG) / etm1, se1);
                    yr = yr * Math.pow((pe2 + j * FLAG) / etm2, se2);
                    yr = yr * Math.pow((pe3 + k * FLAG) / etm3, se3);

                    BeanUtils.copyProperties(testPojo, test);
                    test.setDE1(0 * FLAG);
                    test.setDE2(j * FLAG);
                    test.setDE3(k * FLAG);
                    test.setDE((0 + j + k) * FLAG);
                    test.setYR((int) Math.round(yr));
                    test.setS_DE1(true);
                    list.add(test);
                }
            }
            // 第二阶段灌溉量为0
            for (int i = 1; i < first + 1; i++) {
                for (int k = 1; k < third + 1; k++) {
                    TestPojo test = new TestPojo();
                    double yr = 0;
                    // Jensen计算公式
                    yr = yp * Math.pow((pe1 + i * FLAG) / etm1, se1);
                    yr = yr * Math.pow((pe2 + 0 * FLAG) / etm2, se2);
                    yr = yr * Math.pow((pe3 + k * FLAG) / etm3, se3);

                    BeanUtils.copyProperties(testPojo, test);
                    test.setDE1(i * FLAG);
                    test.setDE2(0 * FLAG);
                    test.setDE3(k * FLAG);
                    test.setDE((i + 0 + k) * FLAG);
                    test.setYR((int) Math.round(yr));
                    test.setS_DE2(true);
                    list.add(test);
                }
            }
            // 第三阶段灌溉量为0
            for (int i = 1; i < first + 1; i++) {
                for (int j = 1; j < second + 1; j++) {
                    TestPojo test = new TestPojo();
                    double yr = 0;
                    // Jensen计算公式
                    yr = yp * Math.pow((pe1 + i * FLAG) / etm1, se1);
                    yr = yr * Math.pow((pe2 + j * FLAG) / etm2, se2);
                    yr = yr * Math.pow((pe3 + 0 * FLAG) / etm3, se3);

                    BeanUtils.copyProperties(testPojo, test);
                    test.setDE1(i * FLAG);
                    test.setDE2(j * FLAG);
                    test.setDE3(0 * FLAG);
                    test.setDE((i + j + 0) * FLAG);
                    test.setYR((int) Math.round(yr));
                    test.setS_DE3(true);
                    list.add(test);
                }
            }
            // 第一第二阶段灌溉量为0
            for (int k = 1; k < third + 1; k++) {
                TestPojo test = new TestPojo();
                double yr = 0;
                // Jensen计算公式
                yr = yp * Math.pow((pe1 + 0 * FLAG) / etm1, se1);
                yr = yr * Math.pow((pe2 + 0 * FLAG) / etm2, se2);
                yr = yr * Math.pow((pe3 + k * FLAG) / etm3, se3);

                BeanUtils.copyProperties(testPojo, test);
                test.setDE1(0 * FLAG);
                test.setDE2(0 * FLAG);
                test.setDE3(k * FLAG);
                test.setDE((0 + 0 + k) * FLAG);
                test.setYR((int) Math.round(yr));
                test.setS_DE1(true);
                test.setS_DE2(true);
                list.add(test);
            }
            // 第一第三阶段灌溉量为0
            for (int j = 1; j < second + 1; j++) {
                TestPojo test = new TestPojo();
                double yr = 0;
                // Jensen计算公式
                yr = yp * Math.pow((pe1 + 0 * FLAG) / etm1, se1);
                yr = yr * Math.pow((pe2 + j * FLAG) / etm2, se2);
                yr = yr * Math.pow((pe3 + 0 * FLAG) / etm3, se3);

                BeanUtils.copyProperties(testPojo, test);
                test.setDE1(0 * FLAG);
                test.setDE2(j * FLAG);
                test.setDE3(0 * FLAG);
                test.setDE((0 + j + 0) * FLAG);
                test.setYR((int) Math.round(yr));
                test.setS_DE1(true);
                test.setS_DE3(true);
                list.add(test);
            }
            // 第二第三阶段灌溉量为0
            for (int j = 1; j < second + 1; j++) {
                for (int k = 1; k < third + 1; k++) {
                    TestPojo test = new TestPojo();
                    double yr = 0;
                    // Jensen计算公式
                    yr = yp * Math.pow((pe1 + 0 * FLAG) / etm1, se1);
                    yr = yr * Math.pow((pe2 + j * FLAG) / etm2, se2);
                    yr = yr * Math.pow((pe3 + k * FLAG) / etm3, se3);

                    BeanUtils.copyProperties(testPojo, test);
                    test.setDE1(0 * FLAG);
                    test.setDE2(j * FLAG);
                    test.setDE3(k * FLAG);
                    test.setDE((0 + j + k) * FLAG);
                    test.setYR((int) Math.round(yr));
                    test.setS_DE2(true);
                    test.setS_DE3(true);
                    list.add(test);
                }
            }
            // 第一第二第三阶段灌溉量为0
            TestPojo test = new TestPojo();
            double yr = 0;
            // Jensen计算公式
            yr = yp * Math.pow(pe1 / etm1, se1) * Math.pow(pe2 / etm2, se2) * Math.pow(pe3 / etm3, se3);

            BeanUtils.copyProperties(testPojo, test);
            test.setDE1(0);
            test.setDE2(0);
            test.setDE3(0);
            test.setDE(0);
            test.setYR((int) Math.round(yr));
            test.setS_DE1(true);
            test.setS_DE2(true);
            test.setS_DE3(true);
            list.add(test);
        } else if (first > 0 && second > 0 && third <= 0) {  // 2 第一第二阶段大于0， 第三阶段小于等于0
            for (int i = 1; i < first + 1; i++) {
                for (int j = 1; j < second + 1; j++) {
                    TestPojo test = new TestPojo();
                    double yr = 0;
                    // Jensen计算公式
                    yr = yp * Math.pow((pe1 + i * FLAG) / etm1, se1);
                    yr = yr * Math.pow((pe2 + j * FLAG) / etm2, se2);
                    yr = yr * Math.pow((pe3 + 0 * FLAG) / etm3, se3);

                    BeanUtils.copyProperties(testPojo, test);
                    test.setDE1(i * FLAG);
                    test.setDE2(j * FLAG);
                    test.setDE3(0 * FLAG);
                    test.setDE((i + j + 0) * FLAG);
                    test.setYR((int) Math.round(yr));
                    list.add(test);
                }
            }
            // 第一阶段灌溉量为0
            for (int j = 1; j < second + 1; j++) {
                TestPojo test = new TestPojo();
                double yr = 0;
                // Jensen计算公式
                yr = yp * Math.pow((pe1 + 0 * FLAG) / etm1, se1);
                yr = yr * Math.pow((pe2 + j * FLAG) / etm2, se2);
                yr = yr * Math.pow((pe3 + 0 * FLAG) / etm3, se3);

                BeanUtils.copyProperties(testPojo, test);
                test.setDE1(0 * FLAG);
                test.setDE2(j * FLAG);
                test.setDE3(0 * FLAG);
                test.setDE((0 + j + 0) * FLAG);
                test.setYR((int) Math.round(yr));
                test.setS_DE1(true);
                list.add(test);
            }
            // 第二阶段灌溉量为0
            for (int i = 1; i < first + 1; i++) {
                TestPojo test = new TestPojo();
                double yr = 0;
                // Jensen计算公式
                yr = yp * Math.pow((pe1 + i * FLAG) / etm1, se1);
                yr = yr * Math.pow((pe2 + 0 * FLAG) / etm2, se2);
                yr = yr * Math.pow((pe3 + 0 * FLAG) / etm3, se3);

                BeanUtils.copyProperties(testPojo, test);
                test.setDE1(i * FLAG);
                test.setDE2(0 * FLAG);
                test.setDE3(0 * FLAG);
                test.setDE((i + 0 + 0) * FLAG);
                test.setYR((int) Math.round(yr));
                test.setS_DE2(true);
                list.add(test);
            }
            // 第一第二阶段灌溉量为0
            TestPojo test = new TestPojo();
            double yr = 0;
            // Jensen计算公式
            yr = yp * Math.pow(pe1 / etm1, se1) * Math.pow(pe2 / etm2, se2) * Math.pow(pe3 / etm3, se3);

            BeanUtils.copyProperties(testPojo, test);
            test.setDE1(0);
            test.setDE2(0);
            test.setDE3(0);
            test.setDE(0);
            test.setYR((int) Math.round(yr));
            test.setS_DE1(true);
            test.setS_DE2(true);
            list.add(test);
        } else if (first > 0 && second <= 0 && third <= 0) {  // 3 第一阶段大于0， 第二第三阶段小于等于0
            for (int i = 1; i < first + 1; i++) {
                TestPojo test = new TestPojo();
                double yr = 0;
                // Jensen计算公式
                yr = yp * Math.pow((pe1 + i * FLAG) / etm1, se1);
                yr = yr * Math.pow((pe2 + 0 * FLAG) / etm2, se2);
                yr = yr * Math.pow((pe3 + 0 * FLAG) / etm3, se3);

                BeanUtils.copyProperties(testPojo, test);
                test.setDE1(i * FLAG);
                test.setDE2(0 * FLAG);
                test.setDE3(0 * FLAG);
                test.setDE((i + 0 + 0) * FLAG);
                test.setYR((int) Math.round(yr));
                list.add(test);
            }
        } else if (first > 0 && second <= 0 && third > 0) {  // 4 第一第三阶段大于0， 第二阶段小于等于0
            for (int i = 1; i < first + 1; i++) {
                for (int j = 1; j < third + 1; j++) {
                    TestPojo test = new TestPojo();
                    double yr = 0;
                    // Jensen计算公式
                    yr = yp * Math.pow((pe1 + i * FLAG) / etm1, se1);
                    yr = yr * Math.pow((pe2 + 0 * FLAG) / etm2, se2);
                    yr = yr * Math.pow((pe3 + j * FLAG) / etm3, se3);

                    BeanUtils.copyProperties(testPojo, test);
                    test.setDE1(i * FLAG);
                    test.setDE2(0 * FLAG);
                    test.setDE3(j * FLAG);
                    test.setDE((i + 0 + j) * FLAG);
                    test.setYR((int) Math.round(yr));
                    list.add(test);
                }
            }
            // 第一阶段灌溉量为0
            for (int j = 1; j < third + 1; j++) {
                TestPojo test = new TestPojo();
                double yr = 0;
                // Jensen计算公式
                yr = yp * Math.pow((pe1 + 0 * FLAG) / etm1, se1);
                yr = yr * Math.pow((pe2 + 0 * FLAG) / etm2, se2);
                yr = yr * Math.pow((pe3 + j * FLAG) / etm3, se3);

                BeanUtils.copyProperties(testPojo, test);
                test.setDE1(0 * FLAG);
                test.setDE2(0 * FLAG);
                test.setDE3(j * FLAG);
                test.setDE((0 + 0 + j) * FLAG);
                test.setYR((int) Math.round(yr));
                test.setS_DE1(true);
                list.add(test);
            }
            // 第三阶段灌溉量为0
            for (int i = 1; i < first + 1; i++) {
                TestPojo test = new TestPojo();
                double yr = 0;
                // Jensen计算公式
                yr = yp * Math.pow((pe1 + i * FLAG) / etm1, se1);
                yr = yr * Math.pow((pe2 + 0 * FLAG) / etm2, se2);
                yr = yr * Math.pow((pe3 + 0 * FLAG) / etm3, se3);

                BeanUtils.copyProperties(testPojo, test);
                test.setDE1(i * FLAG);
                test.setDE2(0 * FLAG);
                test.setDE3(0 * FLAG);
                test.setDE((i + 0 + 0) * FLAG);
                test.setYR((int) Math.round(yr));
                list.add(test);
            }
            // 第一第三阶段灌溉量为0
            TestPojo test = new TestPojo();
            double yr = 0;
            // Jensen计算公式
            yr = yp * Math.pow(pe1 / etm1, se1) * Math.pow(pe2 / etm2, se2) * Math.pow(pe3 / etm3, se3);

            BeanUtils.copyProperties(testPojo, test);
            test.setDE1(0);
            test.setDE2(0);
            test.setDE3(0);
            test.setDE(0);
            test.setYR((int) Math.round(yr));
            test.setS_DE1(true);
            test.setS_DE3(true);
            list.add(test);
        } else if (first <= 0 && second <= 0 && third <= 0) {  // 5 第一第二第三阶段小于等于0
            TestPojo test = new TestPojo();
            double yr = 0;
            // Jensen计算公式
            yr = yp * Math.pow((pe1 + 0 * FLAG) / etm1, se1);
            yr = yr * Math.pow((pe2 + 0 * FLAG) / etm2, se2);
            yr = yr * Math.pow((pe3 + 0 * FLAG) / etm3, se3);

            BeanUtils.copyProperties(testPojo, test);
            test.setDE1(0 * FLAG);
            test.setDE2(0 * FLAG);
            test.setDE3(0 * FLAG);
            test.setDE((0 + 0 + 0) * FLAG);
            test.setYR((int) Math.round(yr));
            list.add(test);
        } else if (first <= 0 && second > 0 && third > 0) { // 6 第二第三阶段大于0， 第一阶段小于等于0
            for (int i = 1; i < second + 1; i++) {
                for (int j = 1; j < third + 1; j++) {
                    TestPojo test = new TestPojo();
                    double yr = 0;
                    // Jensen计算公式
                    yr = yp * Math.pow((pe1 + 0 * FLAG) / etm1, se1);
                    yr = yr * Math.pow((pe2 + i * FLAG) / etm2, se2);
                    yr = yr * Math.pow((pe3 + j * FLAG) / etm3, se3);

                    BeanUtils.copyProperties(testPojo, test);
                    test.setDE1(0 * FLAG);
                    test.setDE2(i * FLAG);
                    test.setDE3(j * FLAG);
                    test.setDE((0 + i + j) * FLAG);
                    test.setYR((int) Math.round(yr));
                    list.add(test);
                }
            }
            // 第二阶段灌溉量为0
            for (int j = 1; j < third + 1; j++) {
                TestPojo test = new TestPojo();
                double yr = 0;
                // Jensen计算公式
                yr = yp * Math.pow((pe1 + 0 * FLAG) / etm1, se1);
                yr = yr * Math.pow((pe2 + 0 * FLAG) / etm2, se2);
                yr = yr * Math.pow((pe3 + j * FLAG) / etm3, se3);

                BeanUtils.copyProperties(testPojo, test);
                test.setDE1(0 * FLAG);
                test.setDE2(0 * FLAG);
                test.setDE3(j * FLAG);
                test.setDE((0 + 0 + j) * FLAG);
                test.setYR((int) Math.round(yr));
                test.setS_DE2(true);
                list.add(test);
            }
            // 第三阶段灌溉量为0
            for (int j = 1; j < third + 1; j++) {
                TestPojo test = new TestPojo();
                double yr = 0;
                // Jensen计算公式
                yr = yp * Math.pow((pe1 + 0 * FLAG) / etm1, se1);
                yr = yr * Math.pow((pe2 + 0 * FLAG) / etm2, se2);
                yr = yr * Math.pow((pe3 + j * FLAG) / etm3, se3);

                BeanUtils.copyProperties(testPojo, test);
                test.setDE1(0 * FLAG);
                test.setDE2(0 * FLAG);
                test.setDE3(j * FLAG);
                test.setDE((0 + 0 + j) * FLAG);
                test.setYR((int) Math.round(yr));
                test.setS_DE3(true);
                list.add(test);
            }
            // 第二第三阶段灌溉量为0
            TestPojo test = new TestPojo();
            double yr = 0;
            // Jensen计算公式
            yr = yp * Math.pow(pe1 / etm1, se1) * Math.pow(pe2 / etm2, se2) * Math.pow(pe3 / etm3, se3);

            BeanUtils.copyProperties(testPojo, test);
            test.setDE1(0);
            test.setDE2(0);
            test.setDE3(0);
            test.setDE(0);
            test.setYR((int) Math.round(yr));
            test.setS_DE2(true);
            test.setS_DE3(true);
            list.add(test);
        } else if (first <= 0 && second <= 0 && third > 0) { // 7 第三阶段大于0， 第一第二阶段小于等于0
            for (int i = 1; i < third + 1; i++) {
                TestPojo test = new TestPojo();
                double yr = 0;
                // Jensen计算公式
                yr = yp * Math.pow((pe1 + 0 * FLAG) / etm1, se1);
                yr = yr * Math.pow((pe2 + 0 * FLAG) / etm2, se2);
                yr = yr * Math.pow((pe3 + i * FLAG) / etm3, se3);

                BeanUtils.copyProperties(testPojo, test);
                test.setDE1(0 * FLAG);
                test.setDE2(0 * FLAG);
                test.setDE3(i * FLAG);
                test.setDE((0 + 0 + i) * FLAG);
                test.setYR((int) Math.round(yr));
                list.add(test);
            }
        } else if (first <= 0 && second > 0 && third <= 0) { // 8 第二阶段大于0， 第一第三阶段小于等于0
            for (int i = 1; i < second + 1; i++) {
                TestPojo test = new TestPojo();
                double yr = 0;
                // Jensen计算公式
                yr = yp * Math.pow((pe1 + 0 * FLAG) / etm1, se1);
                yr = yr * Math.pow((pe2 + i * FLAG) / etm2, se2);
                yr = yr * Math.pow((pe3 + 0 * FLAG) / etm3, se3);

                BeanUtils.copyProperties(testPojo, test);
                test.setDE1(0 * FLAG);
                test.setDE2(i * FLAG);
                test.setDE3(0 * FLAG);
                test.setDE((0 + i + 0) * FLAG);
                test.setYR((int) Math.round(yr));
                list.add(test);
            }
        }

        return list;
    }
}
