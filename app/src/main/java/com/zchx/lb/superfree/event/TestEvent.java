package com.zchx.lb.superfree.event;

/**
 * Created on 2016/1/14 10:24
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

/**
 * 这是注册的事件
 */
public class TestEvent {

    private String mobile;
    private String password;
    private String rePersonMobile;

    public TestEvent(String mobile, String password, String rePersonMobile) {
        this.mobile = mobile;
        this.password = password;
        this.rePersonMobile = rePersonMobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRePersonMobile() {
        return rePersonMobile;
    }

    public void setRePersonMobile(String rePersonMobile) {
        this.rePersonMobile = rePersonMobile;
    }
}
