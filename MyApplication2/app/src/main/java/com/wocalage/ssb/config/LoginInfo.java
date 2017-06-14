package com.wocalage.ssb.config;

import android.content.Context;
import android.text.TextUtils;

import com.wocalage.ssb.net.entity.UserInfo;
import com.wocalage.ssb.util.SPUtils;

/**
 * Created by jiaojian on 2017/5/22.
 * save login information
 */

public class LoginInfo {

    private static Context mContext;
    private boolean mIsLogin;
    private UserInfo mInfo;
    private static LoginInfo mLoginInfo = new LoginInfo();

    private LoginInfo() {
    }

    public static LoginInfo getInstance(Context context) {
        mContext = context;
        return mLoginInfo;
    }

    public boolean isLogined() {
        if (getMyInfo() != null) {
            return true;
        } else {
            return false;
        }
    }

    public UserInfo getMyInfo() {
        if (mInfo != null) {
            return mInfo;
        } else {
            String uid = (String) SPUtils.get(mContext, Config.LOGIN_UID, "");
            if (TextUtils.isEmpty(uid)) {
                return null;
            } else {
                UserInfo info = new UserInfo();
                info.uid = uid;
                info.name = (String) SPUtils.get(mContext, Config.LOGIN_NAME, "");
                info.head = (String) SPUtils.get(mContext, Config.LOGIN_HEAD, "");
                info.des = (String) SPUtils.get(mContext, Config.LOGIN_DES, "");
                mInfo = info;
                return mInfo;
            }
        }
    }

    public void setMyInfo(UserInfo info) {
        if (info == null) {
            return;
        }
        SPUtils.put(mContext, Config.LOGIN_UID, info.uid);
        SPUtils.put(mContext, Config.LOGIN_NAME, info.name);
        SPUtils.put(mContext, Config.LOGIN_HEAD, info.head);
        SPUtils.put(mContext, Config.LOGIN_DES, info.des);
        mInfo = info;
    }
}
