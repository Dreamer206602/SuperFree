package com.zchx.lb.superfree.presenter.impl;

import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.model.ManagePayPasswordModel;
import com.zchx.lb.superfree.presenter.ManagePayPasswordPresenter;
import com.zchx.lb.superfree.view.ManagePayView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/1/21 12:46
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class ManagePayPasswordImpl implements ManagePayPasswordPresenter {
    private ManagePayView view;
    private ManagePayPasswordModel model;

    public ManagePayPasswordImpl(ManagePayView view) {
        this.view = view;
        model=new ManagePayPasswordModel();
    }

    @Override
    public void managePayPassword(String mobile, String oldPayPassword, String newPayPassword, String newRePayPassword) {
        model.ManagePayPassword(mobile, oldPayPassword, newPayPassword, newRePayPassword, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    String result = object.getString("result");
                    String result_msg = object.getString("result_msg");
                    if(result.equals("1")){
                        view.showManagePaySuccess(result_msg);
                    }else{
                        view.showManagePayFail(result_msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
