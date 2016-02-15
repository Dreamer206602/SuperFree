package com.zchx.lb.superfree.entity;

/**
 * Created on 2016/1/8 17:21
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

import java.util.List;

/**
 * 获取和使用记录
 */
public class AccessAndUseRecord {

    private String result;
    public List<Record>result_msg;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Record> getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(List<Record> result_msg) {
        this.result_msg = result_msg;
    }
}
