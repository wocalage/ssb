package com.wocalage.ssb.net;

import android.content.Context;

import com.wocalage.ssb.callback.SSBCallBack;
import com.wocalage.ssb.net.entity.RankListData;
import com.wocalage.ssb.net.entity.UserInfo;
import com.wocalage.ssb.util.NetUtils;

/**
 * Created by jiaojian on 2017/6/12.
 * the real netManager for others to invoke
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

    /**
     * 用户登录
     * @param callCack
     * @param params 需要传入用户名和密码，kv形式
     */
    public void login(SSBCallBack<UserInfo> callCack, KVParam... params) {
        if (!NetUtils.isConnected(mContext)) {
            return;
        }
        Net().login(callCack, params);
    }

    /**
     * 获取总榜
     * @param callCack
     * @param params 需要传入过滤信息，kv形式
     */
    public void fetchTotalRankList(SSBCallBack<RankListData> callCack, KVParam... params) {
        if (!NetUtils.isConnected(mContext)) {
            return;
        }
        Net().fetchTotalList(callCack, params);
    }

    /**
     * 获取周榜
     * @param callCack
     * @param params 需要传入过滤信息，kv形式
     */
    public void fetchWeekRankList(SSBCallBack<RankListData> callCack, KVParam... params) {
        if (!NetUtils.isConnected(mContext)) {
            return;
        }
        Net().fetchWeekList(callCack, params);
    }

    /**
     * 获取用户信息
     * @param callCack
     * @param params 需要传入用户id，kv形式
     */
    public void fetchUserInfo(SSBCallBack<UserInfo> callCack, KVParam... params) {
        if (!NetUtils.isConnected(mContext)) {
            return;
        }
        Net().fetchUserInfo(callCack, params);
    }

}
