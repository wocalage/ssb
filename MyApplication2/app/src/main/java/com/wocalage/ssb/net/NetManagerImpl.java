package com.wocalage.ssb.net;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.wocalage.ssb.callback.SSBCallBack;
import com.wocalage.ssb.config.Config;

import java.io.IOException;

/**
 * Created by jiaojian on 2017-6-12.
 * the implement of INetManager
 */

public class NetManagerImpl implements INetManager{

    private OkHttpClient mOkHttpClient;

    public NetManagerImpl(){
        mOkHttpClient = new OkHttpClient();
    }

    @Override
    public void login(SSBCallBack<?> callBack, KVParam... params) {
        Request request = buildRequest(NetConfig.LOGIN_URL, params);
        fetch(request,callBack);
    }

    @Override
    public void fetchTotalList(SSBCallBack<?> callBack, KVParam... params) {
        Request request = buildRequest(NetConfig.TOTAL_RANK_URL, params);
        fetch(request,callBack);
    }

    @Override
    public void fetchWeekList(SSBCallBack<?> callBack, KVParam... params) {
        Request request = buildRequest(NetConfig.WEEK_RANK_URL, params);
        fetch(request,callBack);
    }

    @Override
    public void fetchUserInfo(SSBCallBack<?> callBack, KVParam... params) {
        Request request = buildRequest(NetConfig.USERINFO_URL, params);
        fetch(request,callBack);
    }

    private void fetch(Request request , SSBCallBack<?> callBack){
//        mOkHttpClient.newCall(request).enqueue(new Callback()
//        {
//            @Override
//            public void onFailure(final Request request, final IOException e)
//            {
//                sendFailedStringCallback(request, e, callback);
//            }
//
//            @Override
//            public void onResponse(final Response response)
//            {
//                try
//                {
//                    final String string = response.body().string();
//                    if (callback.mType == String.class)
//                    {
//                        sendSuccessResultCallback(string, callback);
//                    } else
//                    {
//                        Object o = mGson.fromJson(string, callback.mType);
//                        sendSuccessResultCallback(o, callback);
//                    }
//
//
//                } catch (IOException e)
//                {
//                    sendFailedStringCallback(response.request(), e, callback);
//                } catch (com.google.gson.JsonParseException e)//Json解析的错误
//                {
//                    sendFailedStringCallback(response.request(), e, callback);
//                }
//
//            }
//        });
    }

    private Request buildRequest(String url, KVParam[] params){
        if (params == null)
        {
            params = new KVParam[0];
        }
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (KVParam param : params)
        {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }
}
