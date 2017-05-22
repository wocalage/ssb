package com.wocalage.ssb.util;

import android.util.Log;

/**
 * Created by jiaojian on 2017-5-20.
 *
 */

public class LogUtil {

    private static boolean isDebug = true;
    private static final String TAG = "ssb";

    //通用应用tag
    public static void d(String msg)
    {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg)
    {
        if (isDebug)
            Log.e(TAG, msg);
    }

    //类名为tag
    public static void d(Object object, String msg)
    {
        if (isDebug)
            Log.i(object.getClass().getSimpleName(), msg);
    }

    public static void e(Object object, String msg)
    {
        if (isDebug)
            Log.i(object.getClass().getSimpleName(), msg);
    }
}
