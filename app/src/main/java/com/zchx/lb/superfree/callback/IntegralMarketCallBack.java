package com.zchx.lb.superfree.callback;

import com.google.gson.Gson;
import com.zchx.lb.superfree.entity.CarouselPicture;
import com.zchx.lb.superfree.entity.IntegralMarket;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created on 2016/1/17 22:07
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public abstract class IntegralMarketCallBack extends Callback<IntegralMarket>{
    @Override
    public IntegralMarket parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        IntegralMarket integralMarket = new Gson().fromJson(string, IntegralMarket.class);
        return integralMarket;
    }

}
