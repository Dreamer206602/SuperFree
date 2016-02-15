package com.zchx.lb.superfree.entity;

/**
 * Created on 2016/1/4 16:27
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

import java.util.List;

/**
 * 提现记录
 */
public class WithDrawRecord {

    private String result;
    private List<BankRecord> result_msg;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<BankRecord> getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(List<BankRecord> result_msg) {
        this.result_msg = result_msg;
    }
}
