package com.zchx.lb.superfree.entity;

/**
 * Created on 2015/12/26 15:18
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

/**
 * 确认兑换信息的实体类
 */
public class ExchangeInfo {
    private String projectName;
    private int numIntegral;//积分数目
    private int rmb;//所需要的人民币

    public ExchangeInfo(String projectName, int numIntegral, int rmb) {
        this.projectName = projectName;
        this.numIntegral = numIntegral;
        this.rmb = rmb;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getNumIntegral() {
        return numIntegral;
    }

    public void setNumIntegral(int numIntegral) {
        this.numIntegral = numIntegral;
    }

    public int getRmb() {
        return rmb;
    }

    public void setRmb(int rmb) {
        this.rmb = rmb;
    }
}
