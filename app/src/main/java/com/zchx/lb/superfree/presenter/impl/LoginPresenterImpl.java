package com.zchx.lb.superfree.presenter.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.app.App;
import com.zchx.lb.superfree.model.LoginModel;
import com.zchx.lb.superfree.presenter.LoginPresenter;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.view.LoginView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;


/**
 * Created by sunger on 2015/11/17.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private LoginView view;

    private LoginModel model;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        model = new LoginModel();
    }
    @Override
    public void login(String phone, String password) {
        model.login(phone, password, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

                view.showLoginFail(e.getMessage());
                L.d("Login-------------+>" + e.getMessage());

            }
            @Override
            public void onResponse(String  data) {

                L.d("Login-------------+>" + data);
                try {
                    JSONObject object=new JSONObject(data);
                    String result = object.getString("result");
                    String result_msg = object.getString("result_msg");

                    if(result.equals("1")){
                        view.showLoginSuccess();
                    }else{
                        view.showLoginFail(result_msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
