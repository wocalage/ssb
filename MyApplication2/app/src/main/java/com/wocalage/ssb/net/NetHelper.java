package com.wocalage.ssb.net;

import android.content.Context;

import com.wocalage.ssb.callback.SSBCallBack;
import com.wocalage.ssb.util.NetUtils;

/**
 * Created by jiaojian on 2017/6/12.
 */

public class NetHelper {

    private static Context mContext;
    private static NetHelper mInstance = null;
    private INetManager mNetManager;

    private NetHelper() {
    }

    public static NetHelper getInstance(Context context) {
        mContext = context;
        if (mInstance == null) {
            synchronized (NetHelper.class) {
                if (mInstance == null) {
                    mInstance = new NetHelper();
                }
            }
        }
        return mInstance;
    }

    private synchronized INetManager Net() {
        if (mNetManager == null) {
            mNetManager = new NetManagerImpl();
        }
        return mNetManager;
    }

    public void login(SSBCallBack<?> callCack, KVParam... params) {
        if (!NetUtils.isConnected(mContext)) {
            return;
        }
        Net().login(callCack, params);
    }

    public void fetchTotalRankList(SSBCallBack<?> callCack, KVParam... params) {
        if (!NetUtils.isConnected(mContext)) {
            return;
        }
        Net().fetchTotalList(callCack, params);
    }

    public void fetchWeekRankList(SSBCallBack<?> callCack, KVParam... params) {
        if (!NetUtils.isConnected(mContext)) {
            return;
        }
        Net().fetchWeekList(callCack, params);
    }

    public void fetchUserInfo(SSBCallBack<?> callCack, KVParam... params) {
        if (!NetUtils.isConnected(mContext)) {
            return;
        }
        Net().fetchUserInfo(callCack, params);
    }

}
