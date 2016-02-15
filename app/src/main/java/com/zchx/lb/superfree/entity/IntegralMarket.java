package com.zchx.lb.superfree.entity;

/**
 * Created on 2015/12/24 14:35
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

import java.util.List;

/**
 * 这是积分超市的实体类
 */
public class IntegralMarket {

    private String result;
    private List<Integral>result_msg;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Integral> getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(List<Integral> result_msg) {
        this.result_msg = result_msg;
    }
}
