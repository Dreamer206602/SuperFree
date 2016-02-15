package com.zchx.lb.superfree.model;

import com.google.gson.JsonObject;
import com.zchx.lb.superfree.api.ApiClient;
import com.zchx.lb.superfree.api.ParamsMap;
import com.zchx.lb.superfree.app.AppConstants;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

/**
 * Created on 2016/1/22 17:36
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class ConfirmPayModel {

    public OkHttpRequest ConfirmPay(String mobile, String proName,String intergration,String price,String balance,String userIntergration,String address,String name,String userphone,String payword,String pro_id, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        paramsMap.put(AppConstants.ParamDefaultValue.PRONAME, proName);
        paramsMap.put(AppConstants.ParamDefaultValue.INTERGRATION,intergration);
        paramsMap.put(AppConstants.ParamDefaultValue.PRICE,price);
        paramsMap.put(AppConstants.ParamDefaultValue.BALANCE,balance);
        paramsMap.put(AppConstants.ParamDefaultValue.USERINTERGRATION,userIntergration);
        paramsMap.put(AppConstants.ParamDefaultValue.ADDRESS,address);
        paramsMap.put(AppConstants.ParamDefaultValue.NAME,name);
        paramsMap.put(AppConstants.ParamDefaultValue.USERPHONE,userphone);
        paramsMap.put(AppConstants.ParamDefaultValue.PAYWORD,payword);
        paramsMap.put(AppConstants.ParamDefaultValue.PRO_ID,pro_id);
        return ApiClient.create(AppConstants.RequestPath.CONFIRMPAY, paramsMap).tag("").post(callback);
    }

}
