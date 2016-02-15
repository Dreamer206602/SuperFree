package com.zchx.lb.superfree.entity;

/**
 * Created on 2016/1/25 09:07
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
//这是交易明细中有关交易的实体类
public class Transaction {


    /**
     * balance : 2000
     * addtime : 2015-11-2601: 16: 12
     * mcount : 1999.0
     */

    private double balance;
    private String addtime;
    private double mcount;

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public void setMcount(double mcount) {
        this.mcount = mcount;
    }

    public double getBalance() {
        return balance;
    }

    public String getAddtime() {
        return addtime;
    }

    public double getMcount() {
        return mcount;
    }
}
