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
public class ManagePayPasswordModel {

    /**
     * 管理支付密码
     * @param mobile
     * @param oldPayPassord
     * @param newPayPassword
     * @param newRePayPassword
     * @param callback
     * @return
     */
    public OkHttpRequest ManagePayPassword(String mobile, String oldPayPassord,String newPayPassword,String newRePayPassword, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        paramsMap.put(AppConstants.ParamDefaultValue.OLDPAYPASSWORD, oldPayPassord);
        paramsMap.put(AppConstants.ParamDefaultValue.NEWPAYPASSWORD, newPayPassword);
        paramsMap.put(AppConstants.ParamDefaultValue.NEWREPAYPASSWORD,newRePayPassword);
        return ApiClient.create(AppConstants.RequestPath.UPDATEPAYPASSWORD, paramsMap).tag("").post(callback);
    }

}
