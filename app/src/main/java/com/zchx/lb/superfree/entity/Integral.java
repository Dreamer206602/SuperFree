package com.zchx.lb.superfree.entity;

/**
 * Created on 2016/1/19 15:25
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

/**
 * 积分超市的实体类
 */
public class Integral {


    /**
     * pro_name : AppleiPhone564G玫瑰金色手机
     * pro_end_time : 1452908741
     * pro_status : 2
     * pro_id : 19
     * pro_integral : 100
     * pro_store : 72
     * pro_content : 颜色随机发送
     * pro_comment_amount : 0
     * pro_limit : 1
     * pro_price : 100.0
     * pro_attribute : 0
     * pro_img :  http: //7xogmi.com1.z0.glb.clouddn.com/PC端积分商城产品展示图-6s.jpg
     * delete_flag : 1
     * pro_start_time : 1452822334
     * pro_subtime : 1452822345
     */

    private String pro_name;
    private int pro_end_time;
    private int pro_status;
    private int pro_id;
    private int pro_integral;
    private int pro_store;
    private String pro_content;
    private int pro_comment_amount;
    private int pro_limit;
    private double pro_price;
    private int pro_attribute;
    private String pro_img;
    private int delete_flag;
    private int pro_start_time;
    private int pro_subtime;

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public void setPro_end_time(int pro_end_time) {
        this.pro_end_time = pro_end_time;
    }

    public void setPro_status(int pro_status) {
        this.pro_status = pro_status;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public void setPro_integral(int pro_integral) {
        this.pro_integral = pro_integral;
    }

    public void setPro_store(int pro_store) {
        this.pro_store = pro_store;
    }

    public void setPro_content(String pro_content) {
        this.pro_content = pro_content;
    }

    public void setPro_comment_amount(int pro_comment_amount) {
        this.pro_comment_amount = pro_comment_amount;
    }

    public void setPro_limit(int pro_limit) {
        this.pro_limit = pro_limit;
    }

    public void setPro_price(double pro_price) {
        this.pro_price = pro_price;
    }

    public void setPro_attribute(int pro_attribute) {
        this.pro_attribute = pro_attribute;
    }

    public void setPro_img(String pro_img) {
        this.pro_img = pro_img;
    }

    public void setDelete_flag(int delete_flag) {
        this.delete_flag = delete_flag;
    }

    public void setPro_start_time(int pro_start_time) {
        this.pro_start_time = pro_start_time;
    }

    public void setPro_subtime(int pro_subtime) {
        this.pro_subtime = pro_subtime;
    }

    public String getPro_name() {
        return pro_name;
    }

    public int getPro_end_time() {
        return pro_end_time;
    }

    public int getPro_status() {
        return pro_status;
    }

    public int getPro_id() {
        return pro_id;
    }

    public int getPro_integral() {
        return pro_integral;
    }

    public int getPro_store() {
        return pro_store;
    }

    public String getPro_content() {
        return pro_content;
    }

    public int getPro_comment_amount() {
        return pro_comment_amount;
    }

    public int getPro_limit() {
        return pro_limit;
    }

    public double getPro_price() {
        return pro_price;
    }

    public int getPro_attribute() {
        return pro_attribute;
    }

    public String getPro_img() {
        return pro_img;
    }

    public int getDelete_flag() {
        return delete_flag;
    }

    public int getPro_start_time() {
        return pro_start_time;
    }

    public int getPro_subtime() {
        return pro_subtime;
    }
}
