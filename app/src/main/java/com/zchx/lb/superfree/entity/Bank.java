package com.zchx.lb.superfree.entity;

import java.util.List;

/**
 * Created on 2016/1/20 16:44
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
//关于银行的实体类
public class Bank {
    private String result;
    private List<String>result_msg;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<String> getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(List<String> result_msg) {
        this.result_msg = result_msg;
    }
}
