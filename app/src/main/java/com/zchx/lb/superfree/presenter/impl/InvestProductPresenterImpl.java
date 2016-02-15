package com.zchx.lb.superfree.presenter.impl;

import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.model.InvestProductModel;
import com.zchx.lb.superfree.presenter.InvestProductPresenter;
import com.zchx.lb.superfree.view.InvestProductView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/1/22 18:26
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class InvestProductPresenterImpl implements InvestProductPresenter {
    private InvestProductView view;
    private InvestProductModel model;

    public InvestProductPresenterImpl(InvestProductView view) {
        this.view = view;
        model=new InvestProductModel();
    }

    @Override
    public void InvestProduct(String mobile, String pro_id, String truepay, String payword) {

        model.InvestProduct(mobile, pro_id, truepay, payword, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {

               // result: 1,
                        //result_msg: 投资成功
                //earn：56
                try {
                    JSONObject object=new JSONObject(response);
                    String result = object.getString("result");
                    String result_msg = object.getString("result_msg");
                    String earn = object.getString("earn");
                    if(result.equals("1")){
                        view.showInvestSuccess(result_msg);
                    }else{
                        view.showInvestFail(result_msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
