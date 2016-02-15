package com.zchx.lb.superfree.entity;

/**
 * Created on 2015/12/23 16:42
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

import java.util.List;

/**
 * 产品记录
 */
public class ProductAndRecord {

    private String result;
    private List<Product>result_msg;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Product> getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(List<Product> result_msg) {
        this.result_msg = result_msg;
    }
}
