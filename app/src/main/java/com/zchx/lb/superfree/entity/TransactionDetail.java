package com.zchx.lb.superfree.entity;

/**
 * Created on 2016/1/7 10:26
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

import java.util.List;

/**
 * 交易明细的实体类
 */
public class TransactionDetail {


    private String result;
    private List<Transaction> result_msg;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Transaction> getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(List<Transaction> result_msg) {
        this.result_msg = result_msg;
    }


}
