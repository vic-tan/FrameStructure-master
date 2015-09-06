/**
 * Copyright 2014 qixin Inc. All rights reserved.
 * - Powered by Team GOF. -
 */

package com.android.tanlifei.framestructure.common.exception;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;

import com.android.tanlifei.framestructure.common.constants.GlobalConstants;
import com.android.tanlifei.framestructure.common.utils.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 全局异常捕获（只针对未捕获异常）
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #getInstance()} 获取CrashHandler实例 ,单例模式</li>
 * <li>{@link #init(Context)} 创建初始化化对象</li>
 * <li>{@link #uncaughtException(Thread, Throwable)} 当有发生未捕获异常时会调用该函数来处理</li>
 * <li>{@link #handleException(Throwable)} 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.</li>
 * <li>{@link #collectDeviceInfo(Context)} 收集设备参数信息</li>
 * <li>{@link #saveCrashInfoToFile(Throwable)} 保存错误信息到文件中</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */

public class CrashHandler implements UncaughtExceptionHandler {

    public static final String TAG = "CrashHandler";
    private static CrashHandler INSTANCE = new CrashHandler();//CrashHandler实例
    private UncaughtExceptionHandler mDefaultHandler;//系统默认的UncaughtException处理类
    private Context mContext;//程序的Context对象
    private Map<String, String> infos = new HashMap<String, String>();//用来存储设备信息和异常信息
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//用于格式化日期,作为日志文件名的一部分


    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 创建初始化化对象
     */
    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();//获取系统默认的UncaughtException处理器
        Thread.setDefaultUncaughtExceptionHandler(this);//设置该CrashHandler为程序的默认处理器
    }

    /**
     * 当有发生未捕获异常时会调用该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);//如果用户没有处理则让系统默认的异常处理器来处理
        } else {
            try {
                Thread.sleep(1000);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            } catch (Exception e) {
                Logger.i("killProcess error!");
            }
        }
    }


    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                //Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        collectDeviceInfo(mContext);//收集设备参数信息
        saveCrashInfoToFile(ex);//保存日志文件
        return true;
    }

    /**
     * 收集设备参数信息
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            Logger.e("an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Logger.d(field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Logger.e("an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfoToFile(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();//此次记录日志，后期可能会根据情况两者取其一。
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".LoggerUtils";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory() + GlobalConstants.CRASH_PATH;
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            Logger.i(sb.toString());//此次记录日志，后期可能会根据情况两者取其一。
            return fileName;
        } catch (Exception e) {
            Logger.e("an error occured while writing file...", e);
        }
        return null;
    }


}
