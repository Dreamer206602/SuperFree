package com.zchx.lb.superfree.model;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.zchx.lb.superfree.api.ApiClient;
import com.zchx.lb.superfree.api.ParamsMap;
import com.zchx.lb.superfree.app.AppConstants;

import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;


public class LoginModel {
    /**
     * 用户登陆
     *
     * @param mobile    手机号码
     * @param password 密码
     * @param callback 回调函数
     * @return
     */
    public OkHttpRequest login(String mobile, String password, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        paramsMap.put(AppConstants.ParamDefaultValue.PASSWORD, password);
        return ApiClient.create(AppConstants.RequestPath.LOGIN, paramsMap).tag("").post(callback);
    }
}
