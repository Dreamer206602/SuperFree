package com.zchx.lb.superfree.utils;

/**
 * Created by
 * Author: wswenyue
 * Email: wswenyue@163.com
 * GitHub: https://github.com/wswenyue
 * Date: 2015/11/3
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


/**
 * 通用的工具类、放一些常用的工具
 */
public final class Common {
    /**
     * 打开或关闭WIFI
     *
     * @param mContext Context
     * @param action   打开使用on 关闭使用off
     */
    public static void onWifi(Context mContext, String action) {
        WifiManager wm = ((WifiManager) mContext
                .getSystemService(Context.WIFI_SERVICE));
        if (action.toLowerCase().equalsIgnoreCase("on")) {
            if (!wm.isWifiEnabled()) {
                wm.setWifiEnabled(true);
            }
        }

        if (action.toLowerCase().equalsIgnoreCase("off")) {
            if (wm.isWifiEnabled()) {
                wm.setWifiEnabled(false);
            }
        }
    }

    /**
     * 隐藏键盘
     *
     * @param mContext
     * @param v
     */
    public static void hideKeyboard(Context mContext, View v) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static boolean ishaveNetwork(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();

    }

}

