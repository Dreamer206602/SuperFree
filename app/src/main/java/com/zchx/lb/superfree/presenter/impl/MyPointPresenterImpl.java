package com.zchx.lb.superfree.presenter.impl;

import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.model.MyPointsModel;
import com.zchx.lb.superfree.presenter.MyPointPresenter;
import com.zchx.lb.superfree.view.MyPointView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/1/26 14:36
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class MyPointPresenterImpl implements MyPointPresenter {
    private MyPointView view;
    private MyPointsModel model;

    public MyPointPresenterImpl(MyPointView view) {
        this.view = view;
        model=new MyPointsModel();
    }

    @Override
    public void myPoint(String mobile) {
        model.myPoint(mobile, new ResultCallback<String>() {
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
                        view.showMyPointSuccess(result_msg);
                    }else{
                        view.showMyPointFail(result_msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
