package com.wocalage.ssb.util;

import android.util.Log;

/**
 * Created by jiaojian on 2017-5-20.
 *
 */

public class LogUtil {

    private static boolean isDebug = true;
    private static final String TAG = "ssb";

    public static void i(String msg)
    {
        if (isDebug)
            Log.i(TAG, msg);
    }

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

    public static void v(String msg)
    {
        if (isDebug)
            Log.v(TAG, msg);
    }

    public static void i(Object tag, String msg)
    {
        if (isDebug)
            Log.i(tag.getClass().getSimpleName(), msg);
    }

    public static void d(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }
}
