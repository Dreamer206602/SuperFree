package com.zchx.lb.superfree.presenter.impl;

import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.model.BalanceAndAssetsModel;
import com.zchx.lb.superfree.presenter.BalanceAndAssetsPresenter;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.view.BalanceAndAssetsView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/1/25 17:23
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class BalanceAndAssetsPresenterImpl implements BalanceAndAssetsPresenter {
    private BalanceAndAssetsView view;
    private BalanceAndAssetsModel model;

    public BalanceAndAssetsPresenterImpl(BalanceAndAssetsView view) {
        this.view = view;
        model = new BalanceAndAssetsModel();
    }

   //返回余额
    @Override
    public void returnBalance(String mobile) {
        model.returnBalance(mobile, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                L.d("BalanceAndAssetsPresenterImpl-->returnBalance",response);
                try {
                    JSONObject object=new JSONObject(response);
                    String result = object.getString("result");
                    String result_msg = object.getString("result_msg");
                    if(result.equals("1")){
                        view.showBalanceSuccess(result_msg);
                    }else{
                        view.showBalanceFail(result_msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    //返回总资产
    @Override
    public void returnAssets(String mobile) {
        model.returnAssets(mobile, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }
            @Override
            public void onResponse(String response) {
                L.d("BalanceAndAssetsPresenterImpl-->returnAssets",response);
                try {
                    JSONObject object=new JSONObject(response);
                    String result = object.getString("result");
                    if(result.equals("1")){
                        JSONObject result_msg = object.getJSONObject("result_msg");
                        String interest_received = result_msg.getString("interest_received");
                        String no_received = result_msg.getString("no_received");
                        String all_assets = result_msg.getString("all_assets");
                        view.showAssetsSuccess(interest_received,no_received,all_assets);
                    }else{
                        view.showAssetsFail(object.getString("result_msg"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
