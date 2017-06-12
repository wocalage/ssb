package com.wocalage.ssb.net;

import com.wocalage.ssb.callback.SSBCallCack;

/**
 * Created by jiaojian on 2017/6/12.
 */

public class NetHelper {
    private static NetHelper mInstance = null;

    private NetHelper(){}

    public static NetHelper getInstance(){
        if (mInstance == null){
            synchronized (NetHelper.class){
                if (mInstance == null){
                    mInstance = new NetHelper();
                }
            }
        }
        return mInstance;
    }

    public void fetchPostRequest(String url, SSBCallCack<?> callCack){

    }

    public void setGetRequest(String url, SSBCallCack<?> callCack){

    }

    private boolean checkNet(){
        // TODO: 2017/6/12 check net
        return false;
    }

}
