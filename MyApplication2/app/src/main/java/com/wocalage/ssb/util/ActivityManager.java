package com.wocalage.ssb.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaojian on 2017/5/18.
 * activity manager for control activity
 */

public class ActivityManager {

    private static List<Activity> mActivitys = new ArrayList<>();

    public static void addActivity(Activity activity){
        mActivitys.add(activity);
    }

    public static boolean removeActivity(Activity activity){
        for (Activity ac: mActivitys) {
            if (ac == activity){
                mActivitys.remove(ac);
                return true;
            }
        }
        return false;
    }

    public static void finishAllActivity(){
        for (Activity activity:mActivitys ) {
            activity.finish();
        }
        mActivitys.clear();
    }

}
