package com.zchx.lb.superfree.presenter.impl;

import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.model.RechargeModel;
import com.zchx.lb.superfree.presenter.RechargePresenter;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.view.RechargeView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/1/22 10:41
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class RechargePresenterImpl implements RechargePresenter {

    private RechargeView view;
    private RechargeModel model;

    public RechargePresenterImpl(RechargeView view) {
        this.view = view;
        model = new RechargeModel();
    }

    //获得验证码
    @Override
    public void GetVerifyCode(String mobile, String price) {

        model.GetVerifyCode(mobile, price, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {

//                "result":"2",
//                        "result_code":"2007",
//                        "result_msg":"无效的银行卡",
//                        "orderId":"无效的银行卡"

                try {
                    JSONObject object=new JSONObject(response);
                    String result = object.getString("result");
                    String result_code = object.getString("result_code");
                    String result_msg = object.getString("result_msg");
                    String orderId = object.getString("orderId");
                    if(result.equals("1")){
                        view.showGetIdentifyCodeSuccess(result_msg,orderId);
                    }else{
                        view.showGetIdentifyCodeFail(result_msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //确认支付
    @Override
    public void ConfirmPay(String mobile, String orderId, String valiCode, String price) {

        model.ConfirmPay(mobile, orderId, valiCode, price, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                //result：
               // "result_code":"1003",
                //"result_msg":"订单号不存在",
                L.d("--------============================================------>"+response);
                try {
                    JSONObject object=new JSONObject(response);
                    String result = object.getString("result");
                    String result_code = object.getString("result_code");
                    String result_msg = object.getString("result_msg");
                    L.d("RechargePresenterImpl"+result);
                    L.d("RechargePresenterImpl"+result_code);
                    L.d("RechargePresenterImpl"+result_msg);
                    if(result.equals("1")){
                        view.showRechargeSuccess(result_msg);
                    }else{
                        view.showRechargeFail(result_msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
