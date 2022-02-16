package com.example.pojo;

import org.springframework.stereotype.Component;

public class IndexCode {
    private String bigclassCode;

    private String bigclassName;

    private String middleclassCode;

    private String middleclassName;

    private String subclassCode;

    private String subclassName;

    private String indexCode;

    private String indexName;

    private String indexBreakdown1;

    private String indexBreakdown2;

    private String unit;

    private String frequency;

    private String flag;

    private String seqNo;

    private String remark;

    public String getBigclassCode() {
        return bigclassCode;
    }

    public void setBigclassCode(String bigclassCode) {
        this.bigclassCode = bigclassCode == null ? null : bigclassCode.trim();
    }

    public String getBigclassName() {
        return bigclassName;
    }

    public void setBigclassName(String bigclassName) {
        this.bigclassName = bigclassName == null ? null : bigclassName.trim();
    }

    public String getMiddleclassCode() {
        return middleclassCode;
    }

    public void setMiddleclassCode(String middleclassCode) {
        this.middleclassCode = middleclassCode == null ? null : middleclassCode.trim();
    }

    public String getMiddleclassName() {
        return middleclassName;
    }

    public void setMiddleclassName(String middleclassName) {
        this.middleclassName = middleclassName == null ? null : middleclassName.trim();
    }

    public String getSubclassCode() {
        return subclassCode;
    }

    public void setSubclassCode(String subclassCode) {
        this.subclassCode = subclassCode == null ? null : subclassCode.trim();
    }

    public String getSubclassName() {
        return subclassName;
    }

    public void setSubclassName(String subclassName) {
        this.subclassName = subclassName == null ? null : subclassName.trim();
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode == null ? null : indexCode.trim();
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName == null ? null : indexName.trim();
    }

    public String getIndexBreakdown1() {
        return indexBreakdown1;
    }

    public void setIndexBreakdown1(String indexBreakdown1) {
        this.indexBreakdown1 = indexBreakdown1 == null ? null : indexBreakdown1.trim();
    }

    public String getIndexBreakdown2() {
        return indexBreakdown2;
    }

    public void setIndexBreakdown2(String indexBreakdown2) {
        this.indexBreakdown2 = indexBreakdown2 == null ? null : indexBreakdown2.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency == null ? null : frequency.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "IndexCode{" +
                "bigclassCode='" + bigclassCode + '\'' +
                ", bigclassName='" + bigclassName + '\'' +
                ", middleclassCode='" + middleclassCode + '\'' +
                ", middleclassName='" + middleclassName + '\'' +
                ", subclassCode='" + subclassCode + '\'' +
                ", subclassName='" + subclassName + '\'' +
                ", indexCode='" + indexCode + '\'' +
                ", indexName='" + indexName + '\'' +
                ", indexBreakdown1='" + indexBreakdown1 + '\'' +
                ", indexBreakdown2='" + indexBreakdown2 + '\'' +
                ", unit='" + unit + '\'' +
                ", frequency='" + frequency + '\'' +
                ", flag='" + flag + '\'' +
                ", seqNo='" + seqNo + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}