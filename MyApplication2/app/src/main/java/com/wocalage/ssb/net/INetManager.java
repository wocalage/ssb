package com.wocalage.ssb.net;

import com.wocalage.ssb.callback.SSBCallBack;
import com.wocalage.ssb.net.entity.RankListData;
import com.wocalage.ssb.net.entity.UserInfo;

/**
 * Created by jiaojian on 2017-6-12.
 * the net interface to invoke
 */

public interface INetManager {
    void login(SSBCallBack<UserInfo> callBack, KVParam... params);//登录
    void fetchTotalList(SSBCallBack<RankListData> callBack, KVParam... params);//获取总榜
    void fetchWeekList(SSBCallBack<RankListData> callBack, KVParam... params);//获取周榜
    void fetchUserInfo(SSBCallBack<UserInfo> callBack, KVParam... params);//获取某个用户信息
}
