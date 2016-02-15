package com.zchx.lb.superfree.presenter;

import com.google.gson.JsonObject;

import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/1/22 17:43
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public interface ConfirmPayPresenter {

    void ConfirmPay(String mobile, String proName,String intergration,String price,String balance,String userIntergration,String address,String name,String userphone,String payword,String pro_id);

}
