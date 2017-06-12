package com.wocalage.ssb.net;

import com.wocalage.ssb.callback.SSBCallBack;

/**
 * Created by jiaojian on 2017-6-12.
 * the net interface to invoke
 */

public interface INetManager {
    void login(SSBCallBack<?> callCack, KVParam... params);
    void fetchTotalList(SSBCallBack<?> callCack, KVParam... params);
    void fetchWeekList(SSBCallBack<?> callCack, KVParam... params);
    void fetchUserInfo(SSBCallBack<?> callCack, KVParam... params);
}
