package com.zchx.lb.superfree.presenter;

import org.sunger.net.support.okhttp.request.OkHttpRequest;

/**
 * Created on 2016/1/22 10:39
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public interface RechargePresenter {

    void GetVerifyCode(String mobile, String price );
    void ConfirmPay(String mobile, String orderId, String valiCode, String price);
}
