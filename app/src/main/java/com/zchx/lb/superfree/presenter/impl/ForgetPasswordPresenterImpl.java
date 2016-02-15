package com.zchx.lb.superfree.presenter.impl;

/**
 * Created on 2016/1/21 15:53
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.model.ForgetPasswordModel;
import com.zchx.lb.superfree.presenter.ForgetPasswordPresenter;
import com.zchx.lb.superfree.view.ForgetPasswordView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * 忘记密码
 */
public class ForgetPasswordPresenterImpl  implements ForgetPasswordPresenter{
    private ForgetPasswordView view;
    private ForgetPasswordModel model;

    public ForgetPasswordPresenterImpl(ForgetPasswordView view) {
        this.view = view;
        model=new ForgetPasswordModel();

    }

    @Override
    public void ForgetPassword(String mobile, String newPass, final String reNewPass) {

        model.ForgetPassword(mobile, newPass, reNewPass, new ResultCallback<String>() {
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
                        view.showForgerPasswordSuccess(result_msg);
                    }else{
                        view.showForgetPasswordFail(result_msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
