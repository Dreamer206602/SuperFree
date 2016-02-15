package com.zchx.lb.superfree.presenter.impl;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.entity.ProductAndRecord;
import com.zchx.lb.superfree.model.InvestmentRecordModel;
import com.zchx.lb.superfree.presenter.InvestProductPresenter;
import com.zchx.lb.superfree.presenter.InvestmentRecordPresenter;
import com.zchx.lb.superfree.view.InvestmentRecordView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/1/25 16:22
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class InvestmentRecordPresenterImpl implements InvestmentRecordPresenter{

    private InvestmentRecordView view;
    private InvestmentRecordModel model;

    public InvestmentRecordPresenterImpl(InvestmentRecordView view) {
        this.view = view;
        model=new InvestmentRecordModel();
    }

    @Override
    public void InvestmentRecord(String mobile) {
        model.InvestmentRecord(mobile, new ResultCallback<String>() {
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
                        ProductAndRecord productAndRecord = new Gson().fromJson(response, ProductAndRecord.class);
                        view.showInvestmentRecordSuccess(productAndRecord.getResult_msg());
                    }else{
                        view.showInvestmentRecordFail(result_msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
