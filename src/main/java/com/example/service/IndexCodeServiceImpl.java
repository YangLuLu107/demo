package com.example.service;

import com.example.mapper.IndexCodeMapper;
import com.example.pojo.IndexCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class IndexCodeServiceImpl implements IndexCodeService {
    @Resource
    IndexCodeMapper indexCodeMapper;

    public List<IndexCode> getList() {
        indexCodeMapper.delete();

        List<IndexCode> list = indexCodeMapper.getList();
        String subStr = "";
        String code1 = "";
        String code2 = "";
        int i = 0;
        int j = 0;
        int k = 0;
        String indexCodeStr1 = "";
        String indexCodeStr2 = "";
        int indexCodeInt = 0;
        for (IndexCode indexCode : list) {

            String subclassCode = indexCode.getSubclassCode();
            String indexBreakdown1 = indexCode.getIndexBreakdown1();
            String indexBreakdown2 = indexCode.getIndexBreakdown2();

            StringBuilder builder = new StringBuilder();
            builder.append(subclassCode);

            if (subclassCode.equals(subStr)) {

            } else {
                i = 0;

                subStr = subclassCode;
            }
            if (indexBreakdown1 == null) {
                i++;
                builder.append(autoAdd(i));
                builder.append("0000");
                indexCode.setIndexCode(builder.toString());
                j = 0;
            } else if (indexBreakdown1 != null && indexBreakdown2 == null) {
                j++;
                if (j == 1) {
                    i++;
                    code1 = builder.append(autoAdd(i)).toString();
                    builder.append(autoAdd(j));
                    builder.append("00");
                    indexCode.setIndexCode(builder.toString());
                    indexCodeStr1 = indexCode.getIndexName();
                } else {
                    if (indexCodeStr1.equals(indexCode.getIndexName())) {
                        StringBuilder builder1 = new StringBuilder(code1);
                        builder1.append(autoAdd(j));
                        builder1.append("00");
                        indexCode.setIndexCode(builder1.toString());
                    } else {
                        j = 0;

                        i++;
                        code1 = builder.append(autoAdd(i)).toString();
                        builder.append(autoAdd(++j));
                        builder.append("00");
                        indexCode.setIndexCode(builder.toString());
                        indexCodeStr1 = indexCode.getIndexName();
                    }
                }
                k = 0;
            } else if (indexBreakdown1 != null && indexBreakdown2 != null) {
                k++;
                if (k == 1) {

                    if (!indexCodeStr1.equals(indexCode.getIndexName())) {
                        j = 0;
                        i++;
                        j++;
                    }
                    builder.append(autoAdd(i));
                    builder.append(autoAdd(j));
                    code2 = builder.toString();
                    builder.append(autoAdd(k));
                    indexCode.setIndexCode(builder.toString());
                    indexCodeStr1 = indexCode.getIndexName();
                    indexCodeStr2 = indexCode.getIndexBreakdown1();
                } else {
                    if (indexCodeStr1.equals(indexCode.getIndexName()) && indexCodeStr2.equals(indexBreakdown1)) {
                        StringBuilder builder2 = new StringBuilder(code2);
                        builder2.append(autoAdd(k));
                        indexCode.setIndexCode(builder2.toString());
                    } else {
                        k = 0;
                        j++;
                        builder.append(autoAdd(i));
                        builder.append(autoAdd(j));
                        code2 = builder.toString();
                        builder.append(autoAdd(++k));
                        indexCode.setIndexCode(builder.toString());
                        indexCodeStr1 = indexCode.getIndexName();
                        indexCodeStr2 = indexCode.getIndexBreakdown1();
                    }
                }
            }

            indexCodeMapper.insert(indexCode);
        }
        return list;
    }

    private String autoAdd(int i) {
        return i < 10 ? "0" + i : i + "";
    }
}
