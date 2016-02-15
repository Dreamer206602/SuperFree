package com.zchx.lb.superfree.entity;

/**
 * Created on 2016/1/18 11:51
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class SelectNewProduct {
        private String id;
        private int goods_term;
        private int percentage;
        private int goods_total_amount;
        private double goods_rate;
        private String goods_title;

    public SelectNewProduct(String id, int goods_term, int percentage, int goods_total_amount, double goods_rate, String goods_title) {
        this.id = id;
        this.goods_term = goods_term;
        this.percentage = percentage;
        this.goods_total_amount = goods_total_amount;
        this.goods_rate = goods_rate;
        this.goods_title = goods_title;
    }

    public void setId(String id) {
            this.id = id;
        }

        public void setGoods_term(int goods_term) {
            this.goods_term = goods_term;
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

