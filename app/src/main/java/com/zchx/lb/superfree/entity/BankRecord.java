package com.zchx.lb.superfree.entity;

/**
 * Created on 2016/1/18 15:55
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

/**
 * 银行提现记录的实体
 */
public class BankRecord {
    /**
     * addtime : 2016-01-1109: 37: 06
     * charge : 5.0
     * bankname : 中国银行
     * state : 银行处理中
     * mcount : 1000
     */

    private String addtime;
    private double charge;
    private String bankname;
    private String state;
    private int mcount;

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setMcount(int mcount) {
        this.mcount = mcount;
    }

    public String getAddtime() {
        return addtime;
    }

    public double getCharge() {
        return charge;
    }

    public String getBankname() {
        return bankname;
    }

    public String getState() {
        return state;
    }

    public int getMcount() {
        return mcount;
    }
}
