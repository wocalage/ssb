package com.wocalage.ssb.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiaojian on 2017/6/12.
 */

public class SignUtils {
    public static final String KEY_SIGN = "sign";

    public static final String KEY_PRIVATE = "key";

    public static final String KEY_TIMESTAMP = "timestamp";

    public static final String KEY_VERSION = "version";

    public static String getSignMD5(Map<String, Object> param) {
        Collection<String> keySet = param.keySet();
        List<String> list = new ArrayList<>(keySet);

        //对key键值按字典升序排序
        Collections.sort(list);
        String paramStr = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(KEY_SIGN)) continue;
            paramStr += list.get(i) + "=" + param.get(list.get(i)) + "&";
        }
        if (!TextUtils.isEmpty(paramStr))
            paramStr = paramStr.substring(0, paramStr.length() - 1);
        return MD5Utils.MD5(paramStr);
    }

    public static Map<String, Object> getParameters() {
        Map<String, Object> params = new HashMap<>();
        params.put(KEY_PRIVATE, "alsfoxShop_plat");
        params.put(KEY_VERSION,"1.01");
        params.put(KEY_TIMESTAMP, System.currentTimeMillis());
        return params;
    }
}
