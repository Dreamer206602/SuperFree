package com.zchx.lb.superfree.model;

/**
 * Created on 2016/1/14 15:44
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
 *注册完成
 */
public class RegisterOkModel {

    /**
     * 获得验证码
     * @param mobile
     * @param callback
     * @return
     */
    public OkHttpRequest GetVerifyCode(String mobile,  ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        return ApiClient.create(AppConstants.RequestPath.GETREGVALICODE, paramsMap).tag("").post(callback);
    }


    /**
     * 验证验证码
     * @param mobile
     * @param verifyCode
     * @param callback
     * @return
     */
    public OkHttpRequest ConfirmVerifyCode(String mobile, String verifyCode, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        paramsMap.put(AppConstants.ParamDefaultValue.VALICODE,verifyCode);
        return ApiClient.create(AppConstants.RequestPath.CONFIRMVALICODE, paramsMap).tag("").post(callback);
    }

    /**
     * 注册完成
     * @param mobile
     * @param password
     * @param recPrsonMobile
     * @param callback
     * @return
     */
    public OkHttpRequest RegisterOk(String mobile, String password,String recPrsonMobile, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        paramsMap.put(AppConstants.ParamDefaultValue.PASSWORD, password);
        paramsMap.put(AppConstants.ParamDefaultValue.RECPERSONMOBILE,recPrsonMobile);
        return ApiClient.create(AppConstants.RequestPath.FINISHREGISTER, paramsMap).tag("").post(callback);
    }

}
