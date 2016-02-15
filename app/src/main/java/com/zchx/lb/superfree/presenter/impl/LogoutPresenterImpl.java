package com.zchx.lb.superfree.presenter.impl;

import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.model.LogoutModel;
import com.zchx.lb.superfree.presenter.LogoutPresenter;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.view.LogoutView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/1/15 15:54
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class LogoutPresenterImpl implements LogoutPresenter {
    private LogoutView view;
    private LogoutModel model;

    public LogoutPresenterImpl(LogoutView view) {
        this.view = view;
        model=new LogoutModel();
    }

    @Override
    public void logout(String mobile) {
        model.logout(mobile, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }
            @Override
            public void onResponse(String response) {
                JSONObject object= null;
                try {
                    object = new JSONObject(response);
                    String result = object.getString("result");
                    String result_msg = object.getString("result_msg");
                    if(result.equals("1")){
                        view.LogoutSuccess(result_msg);
                    }else{
                        view.LogoutFail(result_msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
