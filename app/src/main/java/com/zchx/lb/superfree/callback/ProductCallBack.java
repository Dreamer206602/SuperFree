package com.zchx.lb.superfree.callback;
import com.google.gson.Gson;
import com.zchx.lb.superfree.entity.AccessAndUseRecord;
import com.zchx.lb.superfree.entity.ProductAndRecord;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created on 2016/1/18 14:37
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public abstract class ProductCallBack extends Callback<ProductAndRecord>{

    @Override
    public ProductAndRecord parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        ProductAndRecord productAndRecord = new Gson().fromJson(string, ProductAndRecord.class);
        return productAndRecord;
    }
}
