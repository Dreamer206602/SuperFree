package com.zchx.lb.superfree.model;

/**
 * Created on 2016/1/26 11:22
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

import com.zchx.lb.superfree.api.ApiClient;
import com.zchx.lb.superfree.api.ParamsMap;
import com.zchx.lb.superfree.app.AppConstants;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

/**
 * 积分超市的Model
 */
public class IntegralMarketModel {

    public OkHttpRequest IntegralMarket( ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        return ApiClient.create(AppConstants.RequestPath.PICTURECPGOODS,paramsMap).tag("").post(callback);
    }

}
