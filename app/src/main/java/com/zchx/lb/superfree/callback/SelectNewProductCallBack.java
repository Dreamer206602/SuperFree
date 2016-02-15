package com.zchx.lb.superfree.callback;

import com.google.gson.Gson;
import com.zchx.lb.superfree.entity.CarouselPicture;
import com.zchx.lb.superfree.entity.SelectNewProduct;

import java.io.IOException;

import okhttp3.Response;
import com.zhy.http.okhttp.callback.Callback;

/**
 * Created on 2016/1/18 11:53
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public abstract class SelectNewProductCallBack extends Callback<SelectNewProduct>{

    @Override
    public SelectNewProduct parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        SelectNewProduct selectNewProduct = new Gson().fromJson(string, SelectNewProduct.class);
        return selectNewProduct;
    }
}
