package com.wocalage.ssb.config;

import android.content.Context;
import android.text.TextUtils;

import com.wocalage.ssb.entity.UserInfo;
import com.wocalage.ssb.util.LogUtil;
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
        if (getMyInfo() != null){
            return true;
        }else{
            return false;
        }
    }

    public UserInfo getMyInfo(){
        if (mInfo != null){
            return mInfo;
        }else{
            String uid = (String)SPUtils.get(mContext,Config.LOGIN_UID,"");
            if (TextUtils.isEmpty(uid)){
                return null;
            }else{
                UserInfo info = new UserInfo();
                info.setUid(uid);
                info.setName((String)SPUtils.get(mContext,Config.LOGIN_NAME,""));
                info.setHead((String)SPUtils.get(mContext,Config.LOGIN_HEAD,""));
                info.setDes((String)SPUtils.get(mContext,Config.LOGIN_DES,""));
                mInfo = info;
                return mInfo;
            }
        }
    }

    public void  setMyInfo(UserInfo info){
        if (info == null){
            return;
        }
        SPUtils.put(mContext,Config.LOGIN_UID,info.getUid());
        SPUtils.put(mContext,Config.LOGIN_NAME,info.getName());
        SPUtils.put(mContext,Config.LOGIN_HEAD,info.getHead());
        SPUtils.put(mContext,Config.LOGIN_DES,info.getDes());
        mInfo = info;
    }
}
