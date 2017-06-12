package com.wocalage.ssb.callback;

/**
 * Created by jiaojian on 2017/5/23.
 */

public interface SSBCallBack<T> {
    public static final int CODE_SUCCESS = 1;
    public static final int CODE_FAILED = 2;
    void callBack(int code,String msg ,T data);
}
