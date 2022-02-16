package com.example.pojo;

public class TestPojo {
    private Integer YEAR;    // 年份
    private Integer PE1; // 第一阶段降水量
    private Integer PE2;
    private Integer PE3;
    private Integer ETM1;    // 第一阶段需水量
    private Integer ETM2;
    private Integer ETM3;
    private Integer DE1; // 第一阶段灌溉量
    private Integer DE2;
    private Integer DE3;
    private Integer DE;  // 总灌溉量
    private Double SE1; // 第一阶段敏感指数
    private Double SE2;
    private Double SE3;
    private Integer YP;  // 潜在产量
    private Integer YR;  // 雨养产量
    private Boolean S_DE1;  // 第一阶段灌溉量是否为0
    private Boolean S_DE2;
    private Boolean S_DE3;

    public Boolean getS_DE1() {
        return S_DE1;
    }

    public void setS_DE1(Boolean s_DE1) {
        S_DE1 = s_DE1;
    }

    public Boolean getS_DE2() {
        return S_DE2;
    }

    public void setS_DE2(Boolean s_DE2) {
        S_DE2 = s_DE2;
    }

    public Boolean getS_DE3() {
        return S_DE3;
    }

    public void setS_DE3(Boolean s_DE3) {
        S_DE3 = s_DE3;
    }

    public Integer getYEAR() {
        return YEAR;
    }

    public void setYEAR(Integer YEAR) {
        this.YEAR = YEAR;
    }

    public Integer getPE1() {
        return PE1;
    }

    public void setPE1(Integer PE1) {
        this.PE1 = PE1;
    }

    public Integer getPE2() {
        return PE2;
    }

    public void setPE2(Integer PE2) {
        this.PE2 = PE2;
    }

    public Integer getPE3() {
        return PE3;
    }

    public void setPE3(Integer PE3) {
        this.PE3 = PE3;
    }

    public Integer getETM1() {
        return ETM1;
    }

    public void setETM1(Integer ETM1) {
        this.ETM1 = ETM1;
    }

    public Integer getETM2() {
        return ETM2;
    }

    public void setETM2(Integer ETM2) {
        this.ETM2 = ETM2;
    }

    public Integer getETM3() {
        return ETM3;
    }

    public void setETM3(Integer ETM3) {
        this.ETM3 = ETM3;
    }

    public Integer getDE1() {
        return DE1;
    }

    public void setDE1(Integer DE1) {
        this.DE1 = DE1;
    }

    public Integer getDE2() {
        return DE2;
    }

    public void setDE2(Integer DE2) {
        this.DE2 = DE2;
    }

    public Integer getDE3() {
        return DE3;
    }

    public void setDE3(Integer DE3) {
        this.DE3 = DE3;
    }

    public Integer getDE() {
        return DE;
    }

    public void setDE(Integer DE) {
        this.DE = DE;
    }

    public Double getSE1() {
        return SE1;
    }

    public void setSE1(Double SE1) {
        this.SE1 = SE1;
    }

    public Double getSE2() {
        return SE2;
    }

    public void setSE2(Double SE2) {
        this.SE2 = SE2;
    }

    public Double getSE3() {
        return SE3;
    }

    public void setSE3(Double SE3) {
        this.SE3 = SE3;
    }

    public Integer getYP() {
        return YP;
    }

    public void setYP(Integer YP) {
        this.YP = YP;
    }

    public Integer getYR() {
        return YR;
    }

    public void setYR(Integer YR) {
        this.YR = YR;
    }
}
