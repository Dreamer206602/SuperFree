package com.zchx.lb.superfree.model;

/**
 * Created on 2016/1/21 10:18
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

import com.google.gson.JsonObject;
import com.zchx.lb.superfree.api.ApiClient;
import com.zchx.lb.superfree.api.ParamsMap;
import com.zchx.lb.superfree.app.AppConstants;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

/**
 * 修改登录密码
 */
public class ModifyLoginPasswordModel {

    /**
     * 修改登录密码
     * @param mobile
     * @param oldPassword
     * @param newPassword
     * @param newRePassword
     * @param callback
     * @return
     */
    public OkHttpRequest ModifyLoginPassword(String mobile, String oldPassword,String newPassword,String newRePassword, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        paramsMap.put(AppConstants.ParamDefaultValue.OLDPASSWORD, oldPassword);
        paramsMap.put(AppConstants.ParamDefaultValue.NEWPASSWORD, newPassword);
        paramsMap.put(AppConstants.ParamDefaultValue.newRePassword,newRePassword);
        return ApiClient.create(AppConstants.RequestPath.UPDATELOGINPASSWORD, paramsMap).tag("").post(callback);
    }
}
