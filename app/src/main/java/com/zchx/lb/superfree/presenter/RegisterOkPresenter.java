package com.zchx.lb.superfree.presenter;

/**
 * Created on 2016/1/14 15:33
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public interface RegisterOkPresenter {
    void getVerifyCode(String mobile);
    void confirmVerify(String mobile,String verifyCode);
    void registerSuccess(String mobile,String password,String rePersonMobile);


}
