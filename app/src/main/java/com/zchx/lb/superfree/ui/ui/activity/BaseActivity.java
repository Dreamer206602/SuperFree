package com.zchx.lb.superfree.ui.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.utils.UiHelper;
import com.zhy.autolayout.AutoLayoutActivity;

import org.sunger.net.support.okhttp.OkHttpClientManager;
import org.sunger.net.utils.NetWorkUtils;
import org.sunger.net.widget.SolidToast;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created on 2015/12/17 13:48
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
//AutoLayoutActivity
public  class BaseActivity extends AutoLayoutActivity{

    public Handler taskHandler=new Handler();

    public void exitApp() {
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


    public void  showDialog(String msg){
        MaterialDialog materialDialog=new MaterialDialog(getApplicationContext());
        materialDialog.setMessage(msg);
        materialDialog.show();
    }
    public void showPopWindwow(View parent, String text) {
        final PopupWindow popupWindow = UiHelper.createSimplePopupWindow(text, R.color.colorPrimaryDark);
        popupWindow.showAsDropDown(parent);
        taskHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                popupWindow.dismiss();
            }
        }, 1500);
    }

    public void showMsgInBottom(String msg) {
        SolidToast.make(this, msg, Gravity.BOTTOM).setBackgroundColorId(R.color.colorPrimaryDark).show();
    }

    public void showMsgInBottom(int msgSringId) {
        showMsgInBottom(getString(msgSringId));
    }

    protected <T extends View> T findView(int id) {
        return (T) super.findViewById(id);
    }

    public void cancelHttpRequest(String tag) {
        OkHttpClientManager.getInstance().cancelTag(tag);
    }





}
