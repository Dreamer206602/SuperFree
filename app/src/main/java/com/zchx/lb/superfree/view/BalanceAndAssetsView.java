package com.zchx.lb.superfree.view;

/**
 * Created on 2016/1/25 17:16
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

/**
 * 余额和总资产
 */
public interface BalanceAndAssetsView {

    void showBalanceSuccess(String successBalance);
    void showBalanceFail(String failBalance);

    void showAssetsSuccess(String receivedInterest,String waitingInterest,String allAssets);
    void showAssetsFail(String failAssetsMsg);

}
