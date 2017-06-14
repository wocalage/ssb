package com.wocalage.ssb.net;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.wocalage.ssb.callback.SSBCallBack;
import com.wocalage.ssb.config.Config;
import com.wocalage.ssb.net.entity.RankListData;
import com.wocalage.ssb.net.entity.UserInfo;
import com.wocalage.ssb.util.LogUtil;

import java.io.IOException;

/**
 * Created by jiaojian on 2017-6-12.
 * the implement of INetManager
 */

public class NetManagerImpl implements INetManager {

    private OkHttpClient mOkHttpClient;

    public NetManagerImpl() {
        mOkHttpClient = new OkHttpClient();
    }

    @Override
    public void login(SSBCallBack<UserInfo> callBack, KVParam... params) {
        Request request = buildRequest(NetConfig.LOGIN_URL, params);
        fetch(request, callBack, UserInfo.class);
    }

    @Override
    public void fetchTotalList(SSBCallBack<RankListData> callBack, KVParam... params) {
        Request request = buildRequest(NetConfig.TOTAL_RANK_URL, params);
        fetch(request, callBack, RankListData.class);
    }

    @Override
    public void fetchWeekList(SSBCallBack<RankListData> callBack, KVParam... params) {
        Request request = buildRequest(NetConfig.WEEK_RANK_URL, params);
        fetch(request, callBack, RankListData.class);
    }

    @Override
    public void fetchUserInfo(SSBCallBack<UserInfo> callBack, KVParam... params) {
        Request request = buildRequest(NetConfig.USERINFO_URL, params);
        fetch(request, callBack, UserInfo.class);
    }

    private <T> void fetch(final Request request, final SSBCallBack<T> callBack, final Class<T> cls) {
        LogUtil.d(this,"fetch url:"+request.url()+"---class:"+cls);
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                callBack.callBack(SSBCallBack.CODE_FAILED, "" + e.toString(), null);
            }

            @Override
            public void onResponse(final Response response) {
                try {
                    String str = response.body().string();
                    T o = JsonHelper.getInstance().json2Object(str, cls);
                    callBack.callBack(SSBCallBack.CODE_SUCCESS,"",o);
                } catch (IOException e) {
                    callBack.callBack(SSBCallBack.CODE_FAILED, "" + e.toString(), null);
                } catch (com.google.gson.JsonParseException e)//Json解析的错误
                {
                    callBack.callBack(SSBCallBack.CODE_FAILED, "" + e.toString(), null);
                }

            }
        });
    }

    private Request buildRequest(String url, KVParam[] params) {
        if (params == null) {
            params = new KVParam[0];
        }
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (KVParam param : params) {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(buildUrl(url))
                .post(requestBody)
                .build();
    }

    private String buildUrl(String hostUrl){
        StringBuffer sb = new StringBuffer(hostUrl);
        sb.append("?");
        sb.append(new NetParam().getParamUrl());
        return sb.toString();
    }

}
