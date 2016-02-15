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
 *充值的model
 */
public class RechargeModel {

    /**接口5
     * 支付并且发送验证码
     * @param mobile
     * @param price
     * @param callback
     * @return
     */
    public OkHttpRequest GetVerifyCode(String mobile, String price, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        paramsMap.put(AppConstants.ParamDefaultValue.PRICE,price);
        return ApiClient.create(AppConstants.RequestPath.GETPAYVALICODE, paramsMap).tag("").post(callback);
    }


    /**接口6
     * 确认支付
     * @param mobile
     * @param orderId
     * @param valiCode
     * @param price
     * @param callback
     * @return
     */
    public OkHttpRequest ConfirmPay(String mobile, String orderId,String valiCode,String price, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        paramsMap.put(AppConstants.ParamDefaultValue.ORDERID, orderId);
        paramsMap.put(AppConstants.ParamDefaultValue.VALICODE2,valiCode);
        paramsMap.put(AppConstants.ParamDefaultValue.PRICE,price);
        return ApiClient.create(AppConstants.RequestPath.SUBPAY, paramsMap).tag("").post(callback);
    }

}
