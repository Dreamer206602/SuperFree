package com.zchx.lb.superfree.api;



import com.google.gson.JsonObject;
import com.squareup.okhttp.OkHttpClient;
import com.zchx.lb.superfree.app.AppConstants;
//import com.zhy.http.okhttp.request.OkHttpRequest;

import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;


import java.util.Map;

/**
 * Created by sunger on 2015/10/26.
 */
public class ApiClient {

    public static OkHttpRequest.Builder create(String url, Map paramsMap, Map headerMap) {
        return new OkHttpRequest.Builder()
                .url(url).params(paramsMap).headers(headerMap);
    }
    public static OkHttpRequest.Builder create(String url, Map paramsMap) {
        return create(url, paramsMap, null);
    }

    public static OkHttpRequest.Builder create(String path) {
        return create(path, new ParamsMap());

    }








}
