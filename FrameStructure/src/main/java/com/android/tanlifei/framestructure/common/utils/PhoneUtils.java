package com.android.tanlifei.framestructure.common.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

/**
 * Created by tanlifei on 15/9/8.
 */
public class PhoneUtils {

    public static final String TAG = "---------> PhoneUtils ";

    public static final String PHONE_IMEI = "phone_imei";
    public static final String PHONE_WLAN_MAC = "phone_wlan_mac";
    public static final String PHONE_BT_MAC = "phone_bt_mac";
    public static final String PHONE_MODEL = "phone_model";
    public static final String PHONE_VENDOR = "phone_vendor";
    public static final String PHONE_SCREEN_WIDTH = "phone_screen_width";
    public static final String PHONE_SCREEN_HEIGHT = "phone_screen_height";
    public static final String PHONE_DENSITY = "phone_density";

    private static DisplayMetrics displayMetrics = new DisplayMetrics();

    /**
     * 连接网络是否成功
     *
     * @param ctx 上下文对象
     * @return
     */
    public static boolean isNetworkOk(Context ctx) {
        if (null == ctx) {
            Logger.e(TAG, " Context is null ");
            return false;
        }
        ConnectivityManager mConnMgr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnMgr == null) {
            Logger.e(TAG, " ConnectivityManager is null ");
            return false;
        }
        NetworkInfo aActiveInfo = mConnMgr.getActiveNetworkInfo(); // 获取活动网络连接信息
        return aActiveInfo == null ? false : true;
    }

    /**
     * 屏幕的密度
     *
     * @param ctx 上下文对象
     * @return Float 返回类型
     */
    public static Float getDensity(Context ctx) {
        if (ctx == null) {
            Logger.e(TAG, " Context is null ");
            return -1f;
        }
        Float density = (Float) PreferencesUtils.getFloat(PHONE_DENSITY);
        if (-1f == density) {
            ((Activity) ctx).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            density = displayMetrics.density;
            PreferencesUtils.putFloat(PHONE_DENSITY, density);
        }
        Logger.i(TAG, " Float density = " + density);
        return density;
    }

    /**
     *
     * 屏幕宽(px)
     * @param ctx 上下文对象
     * @return Integer 返回类型
     */
    public static Integer getScreenWidth(Context ctx) {
        if (ctx == null) {
            Logger.e(" Context is null ");
            return -1;
        }
        Integer screenWidth = (Integer) PreferencesUtils.getInt(PHONE_SCREEN_WIDTH);
        if (null == screenWidth || -1 == screenWidth) {
            ((Activity) ctx).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            screenWidth = displayMetrics.widthPixels;
            PreferencesUtils.putInt(PHONE_SCREEN_WIDTH, screenWidth);
        }
        Logger.d(" Integer screenWidth = " + screenWidth);
        return screenWidth;
    }

    /**
     * 屏幕高(px)
     * @param ctx 上下文对象
     * @return Integer 返回类型
     */
    public static Integer getScreenHeight(Context ctx) {
        if (ctx == null) {
            Logger.e(TAG," Context is null ");
            return -1;
        }
        Integer screenHeight = (Integer) PreferencesUtils.getInt(PHONE_SCREEN_HEIGHT);
        if (null == screenHeight || -1 == screenHeight) {
            ((Activity) ctx).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            screenHeight = displayMetrics.heightPixels;
            PreferencesUtils.putInt(PHONE_SCREEN_HEIGHT, screenHeight);
        }
        Logger.d(TAG," Integer screenHeight = " + screenHeight);
        return screenHeight;
    }

}
