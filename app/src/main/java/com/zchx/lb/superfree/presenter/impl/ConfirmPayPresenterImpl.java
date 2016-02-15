package com.zchx.lb.superfree.presenter.impl;

import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.model.ConfirmPayModel;
import com.zchx.lb.superfree.presenter.ConfirmPayPresenter;
import com.zchx.lb.superfree.view.ConfirmPayView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/1/22 17:44
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class ConfirmPayPresenterImpl implements ConfirmPayPresenter {
    private ConfirmPayView view;
    private ConfirmPayModel model;

    public ConfirmPayPresenterImpl(ConfirmPayView view) {
        this.view = view;
        model = new ConfirmPayModel();
    }

    @Override
    public void ConfirmPay(String mobile, String proName, String intergration, String price, String balance, String userIntergration, String address, String name, String userphone, String payword, String pro_id) {
        model.ConfirmPay(mobile, proName, intergration, price, balance, userIntergration, address, name, userphone, payword, pro_id, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String result = object.getString("result");
                    String result_msg = object.getString("result_msg");
                    //result: 1,
                    // result_msg: 确认支付成功
                    if (result.equals("1")) {
                        view.showConfirmPaySuccess(result_msg);
                    } else {
                        view.showConfirmPayFail(result_msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
