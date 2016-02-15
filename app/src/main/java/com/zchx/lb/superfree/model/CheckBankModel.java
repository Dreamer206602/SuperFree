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
public class CheckBankModel {


    /**
     * 检查绑定的银行卡
     * @param mobile
     * @param username
     * @param idcard
     * @param cardno
     * @param bindMobile
     * @param payPass
     * @param bankname
     * @param callback
     * @return
     */
    public OkHttpRequest CheckBak(String mobile, String username,String idcard,String cardno,String bindMobile,String payPass,String bankname, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.MOBILE, mobile);
        paramsMap.put(AppConstants.ParamDefaultValue.USERNAME, username);
        paramsMap.put(AppConstants.ParamDefaultValue.IDCARD, idcard);
        paramsMap.put(AppConstants.ParamDefaultValue.CARDNO,cardno);
        paramsMap.put(AppConstants.ParamDefaultValue.BINDMOBILE, bindMobile);
        paramsMap.put(AppConstants.ParamDefaultValue.PAYPASS,payPass);
        paramsMap.put(AppConstants.ParamDefaultValue.BANKNAME, bankname);
        return ApiClient.create(AppConstants.RequestPath.CHECKBANK, paramsMap).tag("").post(callback);
    }

}
