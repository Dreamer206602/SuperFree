package com.zchx.lb.superfree.entity;

/**
 * Created on 2015/12/29 15:36
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

/**
 * 这是充值的实体类
 */
public class Recharge {

    private String bank;
    private String bankNum;

    public Recharge(String bank, String bankNum) {
        this.bank = bank;
        this.bankNum = bankNum;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }
}
