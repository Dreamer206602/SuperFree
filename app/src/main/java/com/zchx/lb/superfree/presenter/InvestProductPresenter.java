package com.zchx.lb.superfree.presenter;

import com.google.gson.JsonObject;

import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/1/22 18:26
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public interface InvestProductPresenter {
    void InvestProduct (String mobile,String pro_id,String truepay,String payword);
}
