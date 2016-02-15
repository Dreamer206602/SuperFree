package com.zchx.lb.superfree.presenter.impl;

import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.model.ForgetPayPasswordModel;
import com.zchx.lb.superfree.presenter.ForgetPayPassWordPresenter;
import com.zchx.lb.superfree.view.ForgetPayPasswordView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/1/21 14:31
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class ForgetPayPassWordPresenterImpl implements ForgetPayPassWordPresenter {

    private ForgetPayPasswordView view;
    private ForgetPayPasswordModel model;

    public ForgetPayPassWordPresenterImpl(ForgetPayPasswordView view) {
        this.view = view;
        model=new ForgetPayPasswordModel();
    }

    @Override
    public void ForgetPayPassword(String mobile, String newPayPass, final String reNewPayPass) {

        model.ForgetPayPassword(mobile, newPayPass, reNewPayPass, new ResultCallback<String>() {
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
                        view.showForgetPayPasswordSuccess(result_msg);
                    }else{
                        view.showForgetPayPasswordFail(result_msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
