package com.zchx.lb.superfree.presenter.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.model.CarouselPictureModel;
import com.zchx.lb.superfree.presenter.CarouselPicturePresenter;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.view.CarouselPictureView;

import org.sunger.net.support.okhttp.callback.ResultCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2016/1/17 11:19
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class CarouselPicturePresenterImpl implements CarouselPicturePresenter{

    private CarouselPictureView view;
    private CarouselPictureModel model;
    private List<String>mData=new ArrayList<>();

    public CarouselPicturePresenterImpl(CarouselPictureView view) {
        this.view = view;
        model=new CarouselPictureModel();
    }
    //获得首页轮播图
    @Override
    public void getPictureCpgoods() {
        model.pictureCpgoods(new ResultCallback<JsonObject>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(JsonObject object) {
                L.d("getPictureCpgoods--------------->"+object);
                    if(object.get("result").toString().equals("1")){
                        JsonArray result_msg = object.get("result_msg").getAsJsonArray();
                        for (int i = 0; i < result_msg.size(); i++) {
                            mData.add(result_msg.get(i).toString());
                        }
                        view.getCarouselPictureSuccess(mData);

                    }else{
                        view.getCarouselPicturePresenterFail(object.get("result_msg").toString());
                    }
            }
        });
    }

    //获得积分轮播图
    @Override
    public void getDoactionAll() {
            model.doactionAll(new ResultCallback<JsonObject>() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(JsonObject response) {

                }
            });
    }
}
