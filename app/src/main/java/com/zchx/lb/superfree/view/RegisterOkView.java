package com.zchx.lb.superfree.view;

/**
 * Created on 2016/1/14 14:07
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public interface RegisterOkView {

    //获得验证码失败
    void showVerifyError(String getVerifyErrorMsg);
    //获得验证码成功
    void showVerifySuccess(String getVerifySuccessMsg);


    //验证验证码成功
    void confirmVerifySuccess(String confirmVerifySuccessMsg);
    //验证验证码失败
    void confirmVerifyError(String confirmVerifyErrorMsg);


    //注册失败
    void showRegisterError(String RegisterErrorMsg);
    //注册成功
    void showRegisterSuccess(String RegisterSuccessMsg);

}
