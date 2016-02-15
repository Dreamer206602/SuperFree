package com.zchx.lb.superfree.presenter.impl;

import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.model.ModifyLoginPasswordModel;
import com.zchx.lb.superfree.presenter.ModifyLoginPasswordPresenter;
import com.zchx.lb.superfree.view.ModifyLoginPasswordView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/1/21 10:24
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class ModifyLoginPasswordPresenterImpl implements ModifyLoginPasswordPresenter {

    private ModifyLoginPasswordView view;
    private ModifyLoginPasswordModel model;

    public ModifyLoginPasswordPresenterImpl(ModifyLoginPasswordView view) {
        this.view = view;
        model = new ModifyLoginPasswordModel();
    }

    @Override
    public void modifyLoginPassword(String mobile, String oldPassword, String newPassword, String newRePassword) {

        model.ModifyLoginPassword(mobile, oldPassword, newPassword, newRePassword, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String result = object.getString("result");
                    String result_msg = object.getString("result_msg");
                    if (result.equals("1")) {
                        view.showModifyLoginPasswordSuccess(result_msg);
                    } else {
                        view.showModifyLoginPasswordFail(result_msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
