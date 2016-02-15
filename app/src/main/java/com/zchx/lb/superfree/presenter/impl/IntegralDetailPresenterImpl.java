package com.zchx.lb.superfree.presenter.impl;

import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.model.IntegralDetailModel;
import com.zchx.lb.superfree.presenter.IntegralDetailPresenter;
import com.zchx.lb.superfree.view.IntegralDetailView;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;

/**
 * Created on 2016/1/26 15:36
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

/**
 * 积分详情
 */
public class IntegralDetailPresenterImpl implements IntegralDetailPresenter {
    private IntegralDetailView view;
    private IntegralDetailModel model;

    public IntegralDetailPresenterImpl(IntegralDetailView view) {
        this.view = view;
        model = new IntegralDetailModel();
    }

    @Override
    public void IntegralDetail(String pro_id) {
        model.IntegralDetail(pro_id, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String result = object.getString("result");
                    JSONObject result_msg = object.getJSONObject("result_msg");
                    if (result.equals("1")) {
                        // pro_name: AppleiPhone564G玫瑰金色手机,
                        // pro_price: 100.00,
                        //  pro_img: http: //7xogmi.com1.z0.glb.clouddn.com/PC端积分商城产品展示图-6s.jpg,
                        //pro_store: 100,
                        //  pro_integral: 100,
                        //pro_content
                        String pro_name = result_msg.getString("pro_name");
                        String pro_price = result_msg.getString("pro_price");
                        String img_url = result_msg.getString("pro_img");
                        String pro_store = result_msg.getString("pro_store");
                        String pro_integral = result_msg.getString("pro_integral");
                        String pro_content = result_msg.getString("pro_content");
                        view.showIntegralDetailsSuccess(pro_name, pro_price, img_url, pro_store, pro_integral, pro_content);
                    } else {
                        view.showIntegralDetailsFail(object.getString("result_msg"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //立即兑换
    @Override
    public void exChange(String mobile, String proName, String intergration, String price, String pro_id) {
        model.exChange(mobile, proName, intergration, price, pro_id, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    String result = object.getString("result");
                    String  result_msg = object.getString("result_msg");
                    if(result.equals("1")){
                        view.showExchangeSuccess(result_msg);
                    }else{
                        view.showExchangeFail(result_msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
