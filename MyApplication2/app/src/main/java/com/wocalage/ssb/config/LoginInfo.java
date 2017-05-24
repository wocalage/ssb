package com.wocalage.ssb.config;

/**
 * Created by jiaojian on 2017/5/22.
 * save login information
 */

public class LoginInfo {

    private boolean mIsLogin = true;
    private static LoginInfo mLoginInfo = new LoginInfo();

    private LoginInfo() {
    }

    public static LoginInfo getInstance() {
        return mLoginInfo;
    }

    public boolean isLogined() {
        return mIsLogin;
    }

    public void setLogin(boolean isLogin){
        mIsLogin = isLogin;
    }
}
