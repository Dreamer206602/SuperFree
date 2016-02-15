package com.zchx.lb.superfree.presenter.impl;

import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.model.CheckBankModel;
import com.zchx.lb.superfree.presenter.CheckBankPresenter;
import com.zchx.lb.superfree.view.CheckBankView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/1/21 17:26
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class CheckBankPresenterImpl implements CheckBankPresenter {

    private CheckBankView view;
    private CheckBankModel model;

    public CheckBankPresenterImpl(CheckBankView view) {
        this.view = view;
        model=new CheckBankModel();
    }

    @Override
    public void CheckBak(String mobile, String username, String idcard, String cardno, String bindMobile, String payPass, String bankname) {
        model.CheckBak(mobile, username, idcard, cardno, bindMobile, payPass, bankname, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object=new JSONObject(response);
                    String result = object.getString("result");
                    String result_msg = object.getString("resultMsg");
                    String errorCode = object.getString("errorCode");
                    if(result.equals("1")){
                        view.showCheckBankViewSuccess(result_msg);
                    }else{
                        view.showCheckBankViewFail(result_msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
