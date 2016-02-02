package com.common.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Map;
import com.common.R;

/**
 * acitivity 之间的跳转
 * <ul>
 * <strong> 方法 </strong>
 * <li>{@link #startForAbsolutePath(Context, String)}  通过activity绝对路径跳转，不带参数 </li>
 * <li>{@link #startForAbsolutePath(Context, String, Intent)}  通过activity绝对路径跳转，带参数 </li>
 * <li>{@link #start(Context, Intent)} 开始跳转 </li>
 * <li>{@link #start(Context, Class)} 不带参数跳转 </li>
 * <li>{@link #start(Context, Class, Intent)}   带Intent 跳转 </li>
 * <li>{@link #start(Context, Class, Bundle)}   带Bundle的跳转 </li>
 * <li>{@link #start(Context, Class, String, Serializable)}   带一个实体参数跳转 </li>
 * <li>{@link #start(Context, Class, String, Parcelable)}   带一个实体参数跳转 </li>
 * <li>{@link #start(Context, Class, Map)}   带多个参数跳转（map参数）</li>
 * <li>{@link #forResult(Context, Intent, int)}  带结果的回调码开始跳转</li>
 * <li>{@link #finish(Context)}   Activity 退出 </li>
 * <li>{@link #mapToIntent(Context, Class, Map)}  把map参数转化为Intent buddle 参数 </li>
 * <p/>
 * </ul>
 *
 * @author tanlifei
 * @date 2015-01-26 下午3:30:25
 */
public class StartActUtils {

    /**
     * 通过activity绝对路径跳转，不带参数
     *
     * @param context
     * @param activityAbsolutePath activity全路径
     */
    public static void startForAbsolutePath(Context context, String activityAbsolutePath) {
        ComponentName comp = new ComponentName(context.getPackageName(), activityAbsolutePath);
        Intent intent = new Intent();
        intent.setComponent(comp);
        start(context, intent);
    }

    /**
     * 通过activity绝对路径跳转，带参数
     *
     * @param context
     * @param activityAbsolutePath activity全路径
     */
    public static void startForAbsolutePath(Context context, String activityAbsolutePath, Intent intent) {
        ComponentName comp = new ComponentName(context.getPackageName(), activityAbsolutePath);
        intent.setComponent(comp);
        start(context, intent);
    }

    /**
     * 开始跳转
     *
     * @param context
     * @param intent  void 返回类型
     * @Title: start
     * @Description: 用一句话描述该文件做什么
     * @throws:throws
     */
    public static void start(Context context, Intent intent) {
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.activity_open_next, R.anim.activity_close_main);

    }

    /**
     * 不带参数跳转
     *
     * @param context
     * @param clazz
     */
    public static void start(Context context, Class<?> clazz) {
        start(context, new Intent(context, clazz));
    }


    /**
     * 带Intent 跳转
     *
     * @param context
     * @param clazz
     * @param intent
     */
    public static void start(Context context, Class<?> clazz, Intent intent) {
        start(context, intent);
    }


    /**
     * 带Bundle的跳转
     *
     * @param context
     * @param clazz
     * @param bundle
     */
    public static void start(Context context, Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        start(context, intent);
    }

    /**
     * 带一个实体参数跳转
     *
     * @param clazz
     * @param context : context
     * @param value   : Parcelable
     */
    public static void start(Context context, Class<?> clazz, String key, Parcelable value) {
        Intent intent = new Intent(context, clazz);
        Bundle bundle = new Bundle();
        bundle.putParcelable(key, value);
        intent.putExtras(bundle);
        start(context, intent);
    }

    /**
     * 带一个实体参数跳转
     *
     * @param clazz
     * @param context : context
     * @param value   : serializeEntity
     */
    public static void start(Context context, Class<?> clazz, String key, Serializable value) {
        Intent intent = new Intent(context, clazz);
        Bundle bundle = new Bundle();
        bundle.putSerializable(key, value);
        intent.putExtras(bundle);
        start(context, intent);
    }

    /**
     * 带多个参数跳转（map参数）
     *
     * @param context
     * @param clazz
     * @param map
     */
    public static void start(Context context, Class<?> clazz, Map<String, Object> map) {
        start(context, mapToIntent(context, clazz, map));
    }


    /**
     * 带结果的回调码开始跳转
     *
     * @param context
     * @param intent
     * @param requestCode void 返回类型
     * @Title: startActivityForResult
     * @Description: 用一句话描述该文件做什么
     * @throws:throws
     */
    public static void forResult(Context context, Intent intent, int requestCode) {
        ((Activity) context).startActivityForResult(intent, requestCode);
    }


    /**
     * Activity 退出
     *
     * @param context void 返回类型
     * @Title: finishActivity
     * @Description: 用一句话描述该文件做什么
     * @throws:throws
     */
    public static void finish(Context context) {
        ((Activity) context).finish();
        ((Activity) context).overridePendingTransition(R.anim.activity_open_main, R.anim.activity_close_next);
    }


    /**
     * 把map参数转化为Intent buddle 参数
     *
     * @param context
     * @param clazz
     * @param map
     * @return
     */
    public static Intent mapToIntent(Context context, Class<?> clazz, Map<String, Object> map) {
        Intent intent = new Intent(context, clazz);
        Bundle bundle = new Bundle();
        if (map != null && map.size() > 0) {
            for (String key : map.keySet()) {
                if (map.get(key) instanceof String) {
                    bundle.putString(key, (String) map.get(key));
                } else if (map.get(key) instanceof Integer) {
                    bundle.putInt(key, (Integer) map.get(key));
                } else if (map.get(key) instanceof Boolean) {
                    bundle.putBoolean(key, (Boolean) map.get(key));
                } else if (map.get(key) instanceof Double) {
                    bundle.putDouble(key, (Double) map.get(key));
                } else if (map.get(key) instanceof Long) {
                    bundle.putLong(key, (Long) map.get(key));
                } else if (map.get(key) instanceof Float) {
                    bundle.putFloat(key, (Float) map.get(key));
                } else if (map.get(key) instanceof Double) {
                    bundle.putDouble(key, (Double) map.get(key));
                } else if (map.get(key) instanceof Serializable) {
                    bundle.putSerializable(key, (Serializable) map.get(key));
                } else if (map.get(key) instanceof Parcelable) {
                    bundle.putParcelable(key, (Parcelable) map.get(key));
                }
            }
        }
        return intent.putExtras(bundle);
    }

}
