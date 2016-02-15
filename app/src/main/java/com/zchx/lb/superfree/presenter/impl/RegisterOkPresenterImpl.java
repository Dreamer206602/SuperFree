package com.zchx.lb.superfree.presenter.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.model.RegisterOkModel;
import com.zchx.lb.superfree.presenter.RegisterOkPresenter;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.view.RegisterOkView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/1/14 16:10
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class RegisterOkPresenterImpl implements RegisterOkPresenter {
    private RegisterOkView view;
    private RegisterOkModel model;

    public RegisterOkPresenterImpl(RegisterOkView view) {
        this.view = view;
        model = new RegisterOkModel();
    }

    /**
     * 获得验证码
     * @param mobile
     */
    @Override
    public void getVerifyCode(String mobile) {
        model.GetVerifyCode(mobile, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                L.d("getRegValicode----+++++------------>" + response);
                JSONObject object= null;
                try {
                    object = new JSONObject(response);
                    String result = object.getString("result");
                    String result_msg = object.getString("result_msg");
                    if(result.equals("1")){
                        view.showVerifySuccess(result_msg);
                    }else{
                        view.showVerifyError(result_msg);
                    }
                } catch (JSONException e) {

                }

            }
        });

    }

    //验证验证码
    @Override
    public void confirmVerify(String mobile, String verifyCode) {
        model.ConfirmVerifyCode(mobile, verifyCode, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                L.d("response---confirmVerify----++++>" + response);
                JSONObject object= null;
                try {
                    object = new JSONObject(response);
                    String result = object.getString("result");
                    String result_msg = object.getString("result_msg");
                    if(result.equals("1")){
                        view.confirmVerifySuccess(result_msg);
                    }else{
                        view.confirmVerifyError(result_msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //注册成功
    @Override
    public void registerSuccess(String mobile, String password, String rePersonMobile) {

        model.RegisterOk(mobile, password, rePersonMobile, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                L.d("response---RegisterSuccess----++++>" + response);
                JSONObject object= null;
                try {
                    object = new JSONObject(response);
                    String result = object.getString("result");
                    String result_msg = object.getString("result_msg");
                    if(result.equals("1")){
                        view.showRegisterSuccess(result_msg);
                    }else{
                        view.showRegisterError(result_msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}

