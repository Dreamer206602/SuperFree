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
public class ForgetPayPasswordModel {


    /**
     * 忘记支付密码
     * @param mobile
     * @param newPayPass
     * @param reNewPayPass
     * @param callback
     * @return
     */

    public OkHttpRequest ForgetPayPassword(String mobile, String newPayPass,String reNewPayPass, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        paramsMap.put(AppConstants.ParamDefaultValue.NEWPAYPASS, newPayPass);
        paramsMap.put(AppConstants.ParamDefaultValue.RENEWPAYPASS,reNewPayPass);
        return ApiClient.create(AppConstants.RequestPath.FORGETPAYPASSWORD, paramsMap).tag("").post(callback);
    }

}
