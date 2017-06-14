package com.wocalage.ssb.net;

import android.content.Context;

import com.wocalage.ssb.callback.SSBCallBack;
import com.wocalage.ssb.net.entity.RankListData;
import com.wocalage.ssb.net.entity.UserInfo;
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

    private INetManager Net() {
        if (mNetManager == null) {
            synchronized (NetHelper.class){
                if (mNetManager == null){
                    mNetManager = new NetManagerImpl();
                }
            }
        }
        return mNetManager;
    }

    public void login(SSBCallBack<UserInfo> callCack, KVParam... params) {
        if (!NetUtils.isConnected(mContext)) {
            return;
        }
        Net().login(callCack, params);
    }

    public void fetchTotalRankList(SSBCallBack<RankListData> callCack, KVParam... params) {
        if (!NetUtils.isConnected(mContext)) {
            return;
        }
        Net().fetchTotalList(callCack, params);
    }

    public void fetchWeekRankList(SSBCallBack<RankListData> callCack, KVParam... params) {
        if (!NetUtils.isConnected(mContext)) {
            return;
        }
        Net().fetchWeekList(callCack, params);
    }

    public void fetchUserInfo(SSBCallBack<UserInfo> callCack, KVParam... params) {
        if (!NetUtils.isConnected(mContext)) {
            return;
        }
        Net().fetchUserInfo(callCack, params);
    }

}
