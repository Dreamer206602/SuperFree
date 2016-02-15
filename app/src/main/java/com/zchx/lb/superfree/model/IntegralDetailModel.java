package com.zchx.lb.superfree.model;

import android.media.midi.MidiOutputPort;

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

/**
 * 积分详情的Model
 */
public class IntegralDetailModel {


    /**
     * 积分详情
     * @param pro_id
     * @param callback
     * @return
     */
    public OkHttpRequest IntegralDetail(String pro_id, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.PRO_ID, pro_id);
        return ApiClient.create(AppConstants.RequestPath.INTERPRODUCT, paramsMap).tag("").post(callback);
    }

    /**
     * 立即兑换
     * @param mobile
     * @param proName
     * @param intergration
     * @param price
     * @param pro_id
     * @param callback
     * @return
     */
    public OkHttpRequest exChange(String mobile,String proName,String intergration,String price,String pro_id, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        paramsMap.put(AppConstants.ParamDefaultValue.PRONAME, proName);
        paramsMap.put(AppConstants.ParamDefaultValue.INTERGRATION, intergration);
        paramsMap.put(AppConstants.ParamDefaultValue.PRICE, price);
        paramsMap.put(AppConstants.ParamDefaultValue.PRO_ID, pro_id);
        return ApiClient.create(AppConstants.RequestPath.INSERTADDRESS, paramsMap).tag("").post(callback);
    }




}
