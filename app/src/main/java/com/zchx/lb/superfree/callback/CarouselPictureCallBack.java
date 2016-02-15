package com.zchx.lb.superfree.callback;

import com.google.gson.Gson;
import com.zchx.lb.superfree.entity.CarouselPicture;

import java.io.IOException;

import okhttp3.Response;
import com.zhy.http.okhttp.callback.Callback;

/**
 * Created on 2016/1/17 22:07
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public abstract class CarouselPictureCallBack extends Callback<CarouselPicture>{
    @Override
    public CarouselPicture parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        CarouselPicture carouselPicture = new Gson().fromJson(string, CarouselPicture.class);
        return carouselPicture;
    }

}
