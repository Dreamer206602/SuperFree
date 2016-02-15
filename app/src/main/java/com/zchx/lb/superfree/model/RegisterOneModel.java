package com.zchx.lb.superfree.model;


import com.google.gson.JsonObject;
import com.zchx.lb.superfree.api.ApiClient;
import com.zchx.lb.superfree.api.ParamsMap;
import com.zchx.lb.superfree.app.AppConstants;

import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;


public class RegisterOneModel {

    /**
     * 用户注册---》第一步
     * @param mobile
     * @param password
     * @param rePassword
     * @param callback
     * @return
     */
    public OkHttpRequest RegisterOne(String mobile, String password,String rePassword,String recPrsonMobile, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        paramsMap.put(AppConstants.ParamDefaultValue.PASSWORD, password);
        paramsMap.put(AppConstants.ParamDefaultValue.REPASSWORD, rePassword);
        paramsMap.put(AppConstants.ParamDefaultValue.RECPERSONMOBILE,recPrsonMobile);
        return ApiClient.create(AppConstants.RequestPath.REGISTER, paramsMap).tag("").post(callback);
    }










}
