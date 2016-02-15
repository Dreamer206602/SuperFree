package com.zchx.lb.superfree.model;

import com.zchx.lb.superfree.api.ApiClient;
import com.zchx.lb.superfree.api.ParamsMap;
import com.zchx.lb.superfree.app.AppConstants;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

/**
 * Created on 2016/1/25 17:20
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class BalanceAndAssetsModel {


    /**
     * 返回余额
     * @param mobile
     * @param callback
     * @return
     */
    public OkHttpRequest returnBalance(String mobile, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        return ApiClient.create(AppConstants.RequestPath.RETURNBALANCE, paramsMap).tag("").post(callback);
    }

    /**
     * 返回总资产
     * @param mobile
     * @param callback
     * @return
     */
    public OkHttpRequest returnAssets(String mobile, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        return ApiClient.create(AppConstants.RequestPath.RETURNASSETS, paramsMap).tag("").post(callback);

    }


}
