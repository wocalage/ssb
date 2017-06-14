package com.wocalage.ssb.manager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by jiaojian on 2017/6/14.
 */

public class ApplicationManager {
    private static ApplicationManager mIntance;
    private Context mContext;
    private PackageInfo mPackageInfo;
    private PackageManager mPackageManager;

    public static ApplicationManager getIntance(){
        if (mIntance == null){
            synchronized (ApplicationManager.class){
                if (mIntance == null){
                    mIntance = new ApplicationManager();
                }
            }
        }
        return mIntance;
    }

    public Context getContext(){
        return mContext;
    }

    public void setContext(Context context){
        mContext = context;
    }

    public PackageInfo getPackageInfo(){
        if ( mPackageInfo == null){
            try {
                mPackageInfo = getPackageManager().getPackageInfo(mContext.getPackageName(),0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                mPackageInfo = null;
            }
        }
        return mPackageInfo;
    }

    public PackageManager getPackageManager(){
        if (mPackageManager == null){
            mPackageManager = mContext.getPackageManager();
        }
        return mPackageManager;
    }

    public String getVersion(){
        String version;
        if (getPackageInfo() != null){
            version= getPackageInfo().versionName;
        }else{
            version = "ErrorVersion";
        }
        return version;
    }
}
