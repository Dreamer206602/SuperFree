package com.zchx.lb.superfree.presenter.impl;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.entity.Transaction;
import com.zchx.lb.superfree.entity.TransactionDetail;
import com.zchx.lb.superfree.model.TransactionDetailModel;
import com.zchx.lb.superfree.presenter.TransactionDetailPresenter;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.view.TransactionDetailView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/2/2 22:14
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class TransactionDetailPresenterImpl implements TransactionDetailPresenter {
    private TransactionDetailView view;
    private TransactionDetailModel model;

    public TransactionDetailPresenterImpl(TransactionDetailView view) {
        this.view = view;
        model=new TransactionDetailModel();
    }

    @Override
    public void transactionDetail(String mobile) {
        model.transactionDetail(mobile, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                L.d("TransactionDetailPresenterImpl-->"+response);
                if (response != null) {
                    try {
                        JSONObject object=new JSONObject(response);
                        String result = object.getString("result");
                        String result_msg = object.getString("result_msg");
                        if (result.equals("1")) {
                            TransactionDetail transactionDetail=new Gson().fromJson(response,TransactionDetail.class);
                           view.showTransactionDetailSuccess(transactionDetail.getResult_msg());
                        }else{
                            view.showTransactionDetailFail(result_msg);
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }

            }
        });

    }
}
