package com.zchx.lb.superfree.model;


import com.zchx.lb.superfree.api.ApiClient;
import com.zchx.lb.superfree.api.ParamsMap;
import com.zchx.lb.superfree.app.AppConstants;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;


public class MyPointsModel {

    /**
     * 我的积分
     * @param mobile
     * @param callback
     * @return
     */
    public OkHttpRequest myPoint(String mobile, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        return ApiClient.create(AppConstants.RequestPath.GETINTER, paramsMap).tag("").post(callback);
    }
}
