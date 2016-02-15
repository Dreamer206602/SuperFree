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
public class InvestProductModel {


    /**
     * 投资产品
     * @param mobile
     * @param pro_id
     * @param truepay
     * @param payword
     * @param callback
     * @return
     */

    public OkHttpRequest InvestProduct (String mobile,String pro_id,String truepay,String payword, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        paramsMap.put(AppConstants.ParamDefaultValue.PRO_ID, pro_id);
        paramsMap.put(AppConstants.ParamDefaultValue.TRUEPAY, truepay);
        paramsMap.put(AppConstants.ParamDefaultValue.PAYWORD,payword);
        return ApiClient.create(AppConstants.RequestPath.INVESTPRODUCT, paramsMap).tag("").post(callback);
    }

}
