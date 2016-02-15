package com.zchx.lb.superfree.view;

/**
 * Created on 2016/1/22 10:25
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

/**
 * 充值的view
 */
public interface RechargeView {

    void showGetIdentifyCodeSuccess(String successMsg,String orderId);
    void showGetIdentifyCodeFail(String failMsg);

    void showRechargeSuccess(String successMsg);
    void showRechargeFail(String failMsg);
}
