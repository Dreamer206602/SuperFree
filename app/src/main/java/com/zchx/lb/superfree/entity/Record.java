package com.zchx.lb.superfree.entity;

/**
 * Created on 2016/1/18 14:34
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class Record {


    /**
     * addtime : 2016-01-1315: 32: 58.0
     * mark : 推荐人购买产品
     * user_integration : 4
     */

    private String addtime;
    private String mark;
    private int user_integration;

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setUser_integration(int user_integration) {
        this.user_integration = user_integration;
    }

    public String getAddtime() {
        return addtime;
    }

    public String getMark() {
        return mark;
    }

    public int getUser_integration() {
        return user_integration;
    }
}
