package com.wocalage.ssb.net;

import com.wocalage.ssb.config.Config;
import com.wocalage.ssb.manager.ApplicationManager;
import com.wocalage.ssb.util.MD5Utils;

/**
 * Created by jiaojian on 2017/6/14.
 * to add some param about client
 */

public class NetParam {

    private String appKey;
    private String sign;
    private long time;
    private String versionId;

    public NetParam(){
        init();
    }
    private void init(){
        appKey = Config.APP_KEY;
        versionId = ApplicationManager.getIntance().getVersion();
        time = System.currentTimeMillis();
        sign = MD5Utils.MD5(appKey+versionId+time);
    }

    public KVParam[] getAttachKVParam(){
        KVParam[] kvParams = new KVParam[4];
        kvParams[0] = new KVParam("appKey",appKey);
        kvParams[1] = new KVParam("version",versionId);
        kvParams[2] = new KVParam("time",time+"");
        kvParams[3] = new KVParam("sign",sign);
        return kvParams;
    }
}
