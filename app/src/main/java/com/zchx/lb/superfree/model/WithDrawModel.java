package com.zchx.lb.superfree.model;

/**
 * Created on 2016/1/22 11:39
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
 * 体现
 */
public class WithDrawModel {

    /**
     * 体现的Model
     * @param mobile
     * @param cnumber
     * @param bankname
     * @param balance
     * @param mcount
     * @param payword
     * @param callback
     * @return
     */
    public OkHttpRequest WithDraw(String mobile,String cnumber,String bankname,String balance,String mcount,String payword, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        paramsMap.put(AppConstants.ParamDefaultValue.CNUMBER, cnumber);
        paramsMap.put(AppConstants.ParamDefaultValue.BANKNAME, bankname);
        paramsMap.put(AppConstants.ParamDefaultValue.BALANCE,balance);
        paramsMap.put(AppConstants.ParamDefaultValue.MCOUNT,mcount);
        paramsMap.put(AppConstants.ParamDefaultValue.PAYWORD,payword);
        return ApiClient.create(AppConstants.RequestPath.DOACTIONMONEY, paramsMap).tag("").post(callback);
    }

}
