package com.zchx.lb.superfree.presenter;

import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.model.WithDrawModel;
import com.zchx.lb.superfree.view.WithDrawView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/1/22 11:50
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class WithDrawPresenterImpl implements WithDrawPresenter {
    private WithDrawView view;
    private WithDrawModel model;

    public WithDrawPresenterImpl(WithDrawView view) {
        this.view = view;
        model=new WithDrawModel();
    }

    @Override
    public void WithDrawRMB(String mobile, String cnumber, String bankname, String balance, String mcount, String payword) {

        model.WithDraw(mobile, cnumber, bankname, balance, mcount, payword, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {


            }

            @Override
            public void onResponse(String response) {

//                result: 1, result_msg: 提现成功
                try {
                    JSONObject object=new JSONObject(response);
                    String result = object.getString("result");
                    String result_msg = object.getString("result_msg");
                    if (result.equals("1")) {
                        view.showWithDrawSuccess(result_msg);
                    }else{
                        view.showWithDrawFail(result_msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
