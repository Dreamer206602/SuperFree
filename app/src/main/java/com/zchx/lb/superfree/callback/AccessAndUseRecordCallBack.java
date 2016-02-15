package com.zchx.lb.superfree.callback;
import com.google.gson.Gson;
import com.zchx.lb.superfree.entity.AccessAndUseRecord;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created on 2016/1/18 14:37
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public abstract class AccessAndUseRecordCallBack extends Callback<AccessAndUseRecord>{

    @Override
    public AccessAndUseRecord parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        AccessAndUseRecord accessAndUseRecord = new Gson().fromJson(string, AccessAndUseRecord.class);
        return accessAndUseRecord;
    }
}
