package com.wocalage.ssb.net;

import com.google.gson.Gson;
import com.wocalage.ssb.util.LogUtil;

/**
 * Created by jiaojian on 2017/6/12.
 * the jsonHelper
 */

public class JsonHelper {
    private static JsonHelper mInstance;
    private Gson mGson;

    private JsonHelper(){

    }

    public static JsonHelper getInstance(){
        if (mInstance == null){
            synchronized (JsonHelper.class){
                if (mInstance == null){
                    mInstance = new JsonHelper();
                }
            }
        }
        return mInstance;
    }

    private Gson Gson(){
        if (mGson == null){
            synchronized (JsonHelper.class){
                if (mGson == null){
                    mGson = new Gson();
                }
            }
        }
        return mGson;
    }

    public <T>T json2Object(String jsonStr,Class<T> type){
        LogUtil.d(this,"json2Oject jsonStr:"+jsonStr+"--type:"+type);
        return Gson().fromJson(jsonStr,type);
    }
}

