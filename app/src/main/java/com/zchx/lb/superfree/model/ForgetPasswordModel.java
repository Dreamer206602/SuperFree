package com.zchx.lb.superfree.model;

import com.google.gson.JsonObject;
import com.zchx.lb.superfree.api.ApiClient;
import com.zchx.lb.superfree.api.ParamsMap;
import com.zchx.lb.superfree.app.AppConstants;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

/**
 * Created on 2016/1/21 11:46
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class ForgetPasswordModel {


    /**
     * 忘记密码
     * @param mobile
     * @param newPass
     * @param reNewPass
     * @param callback
     * @return
     */

    public OkHttpRequest ForgetPassword(String mobile, String newPass,String reNewPass, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        paramsMap.put(AppConstants.ParamDefaultValue.NEWPASS, newPass);
        paramsMap.put(AppConstants.ParamDefaultValue.RENEWPASS,reNewPass);
        return ApiClient.create(AppConstants.RequestPath.FORGETPASSWORD, paramsMap).tag("").post(callback);
    }

}
