package com.zchx.lb.superfree.model;

import com.google.gson.JsonObject;
import com.zchx.lb.superfree.api.ApiClient;
import com.zchx.lb.superfree.api.ParamsMap;
import com.zchx.lb.superfree.app.AppConstants;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

/**
 * Created on 2016/1/15 15:51
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class LogoutModel {

    /**
     * 注销退出
     * @param mobile
     * @param callback
     * @return
     */
    public OkHttpRequest logout(String mobile, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        return ApiClient.create(AppConstants.RequestPath.LOGOUT, paramsMap).tag("").post(callback);
    }

}
