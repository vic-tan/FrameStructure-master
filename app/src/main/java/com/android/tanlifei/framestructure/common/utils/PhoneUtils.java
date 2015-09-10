package com.android.tanlifei.framestructure.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by tanlifei on 15/9/8.
 */
public class PhoneUtils {

    /**
     * 连接网络是否成功
     * @param ctx 上下文对象
     * @return
     */
    public static boolean isNetworkOk(Context ctx) {
        if (null == ctx) {
            Logger.e(" Context is null ");
            return false;
        }
        ConnectivityManager mConnMgr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnMgr == null) {
            Logger.e(" ConnectivityManager is null ");
            return false;
        }
        NetworkInfo aActiveInfo = mConnMgr.getActiveNetworkInfo(); // 获取活动网络连接信息
        return aActiveInfo == null ? false : true;
    }
}
