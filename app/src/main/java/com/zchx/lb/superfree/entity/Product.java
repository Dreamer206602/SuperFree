package com.zchx.lb.superfree.entity;

/**
 * Created on 2016/1/18 16:47
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

public class Product {


    /**
     * id : 25
     * goods_term : 150
     * goods_price : 3800
     * percentage : 100
     * goods_total_amount : 570000
     * goods_rate : 8.8
     * goods_title : 太湖三号山居计划
     */

    private String id;
    private int goods_term;
    private int goods_price;
    private int percentage;
    private int goods_total_amount;
    private double goods_rate;
    private String goods_title;

    public void setId(String id) {
        this.id = id;
    }

    public void setGoods_term(int goods_term) {
        this.goods_term = goods_term;
    }

    public void setGoods_price(int goods_price) {
        this.goods_price = goods_price;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public void setGoods_total_amount(int goods_total_amount) {
        this.goods_total_amount = goods_total_amount;
    }

    public void setGoods_rate(double goods_rate) {
        this.goods_rate = goods_rate;
    }

    public void setGoods_title(String goods_title) {
        this.goods_title = goods_title;
    }

    public String getId() {
        return id;
    }

    public int getGoods_term() {
        return goods_term;
    }

    public int getGoods_price() {
        return goods_price;
    }

    public int getPercentage() {
        return percentage;
    }

    public int getGoods_total_amount() {
        return goods_total_amount;
    }

    public double getGoods_rate() {
        return goods_rate;
    }

    public String getGoods_title() {
        return goods_title;
    }
}
