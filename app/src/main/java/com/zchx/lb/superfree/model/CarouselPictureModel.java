package com.zchx.lb.superfree.model;

/**
 * Created on 2016/1/17 11:12
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

import com.google.gson.JsonObject;
import com.zchx.lb.superfree.api.ApiClient;
import com.zchx.lb.superfree.api.ParamsMap;
import com.zchx.lb.superfree.app.AppConstants;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

/**
 * 轮播图----->首页轮播图和积分轮播图
 */
public class CarouselPictureModel {

    /**
     * 首页轮播图
     * @param callback
     * @return
     */
    public OkHttpRequest pictureCpgoods(ResultCallback<JsonObject> callback) {
        ParamsMap paramsMap=new ParamsMap();
        paramsMap.put(AppConstants.ParamDefaultValue.METHOD,AppConstants.ParamsMethod.PICTURECPGOODS);
        return ApiClient.create(AppConstants.RequestPath.BASE_URL,paramsMap).tag("").post(callback);
    }


    /**
     * 积分轮播图
     * @param callback
     * @return
     */
    public OkHttpRequest doactionAll(ResultCallback<JsonObject> callback) {
        return ApiClient.create(AppConstants.RequestPath.PICTUREINTERALL).tag("").post(callback);
    }






}
