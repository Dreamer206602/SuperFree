package com.zchx.lb.superfree.app;

import android.app.Application;

import com.squareup.okhttp.OkHttpClient;

import org.sunger.net.support.okhttp.OkHttpClientManager;


import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * Created on 2015/12/9 10:59
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

/**
 * 自己的Application主要实现一些初始化的操作
 */
public class App extends Application{
    // 用于存放倒计时时间
    public static Map<String, Long> map;
    private static final int CONNECT_TIMEOUT_MILLIS = 10 * 1000; // 15s
    private static final int READ_TIMEOUT_MILLIS = 15 * 1000; // 20s
    private static App instance;
    private OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        initOkHttp();
    }

    private void initOkHttp() {
        okHttpClient =
                OkHttpClientManager.getInstance().getOkHttpClient();
        okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
    }




    public static App getInstance() {
        return instance;
    }

















}
