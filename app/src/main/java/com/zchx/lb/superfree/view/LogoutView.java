package com.zchx.lb.superfree.view;

/**
 * Created on 2016/1/15 15:46
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

/**
 * 退出注销
 */
public interface LogoutView {
    void LogoutSuccess(String logoutSuccessMsg);
    void LogoutFail(String logoutErrorMsg);
}
