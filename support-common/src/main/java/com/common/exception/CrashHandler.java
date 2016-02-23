/**
 * Copyright 2014 qixin Inc. All rights reserved.
 * - Powered by Team GOF. -
 */

package com.common.exception;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

import com.common.utils.Logger;
import com.common.utils.SDCardUtils;
import com.constants.fixed.GlobalConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;


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
 * <li>{@link #sendCrashReportsToServer(Context)} 把错误报告发送给服务器,包含新产生的和以前没发送的.</li>
 * <li>{@link #getCrashReportFiles(Context)} 获取错误报告文件名</li>
 * <li>{@link #postReport(File)} 发送后服务器</li>
 * <li>{@link #sendPreviousReportsToServer()} 在程序启动时候, 可以调用该函数来发送以前没有发送的报告</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */

public class CrashHandler implements UncaughtExceptionHandler {

    public static final String TAG = "CrashHandler";
    /**
     * 错误报告文件的扩展名
     */
    private static final String CRASH_REPORTER_EXTENSION = ".cr";
    private static volatile CrashHandler instance = null;//CrashHandler实例
    private UncaughtExceptionHandler mDefaultHandler;//系统默认的UncaughtException处理类
    private Context mContext;//程序的Context对象
    private Map<String, String> infos = new HashMap<String, String>();//用来存储设备信息和异常信息
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//用于格式化日期,作为日志文件名的一部分

    // private constructor suppresses
    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (instance == null) {
            synchronized (CrashHandler.class) {
                if (instance == null) {
                    instance = new CrashHandler();
                }
            }
        }

        return instance;
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
            // Sleep一会后结束程序
            // 来让线程停止一会是为了显示Toast信息给用户，然后Kill程序
            /*try {
                Thread.sleep(3000);
            } catch (Exception e) {
                Logger.i(e.toString());
            }*/
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
            System.exit(1);
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
        /*new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                ToastUtils.show(mContext, "程序出错啦");
                Looper.loop();
            }
        }.start();*/
        collectDeviceInfo(mContext);//收集设备参数信息
        saveCrashInfoToFile(ex);//保存日志文件
        //sendCrashReportsToServer(mContext);// 发送错误报告到服务器
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
            String fileName = "crash-" + time + "-" + timestamp + CRASH_REPORTER_EXTENSION;
            if (SDCardUtils.isSDCardEnable()) {
                File dir = new File(GlobalConstants.CRASH_PATH);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(GlobalConstants.CRASH_PATH + fileName);
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

    /**
     * 把错误报告发送给服务器,包含新产生的和以前没发送的.
     *
     * @param ctx
     */
    private void sendCrashReportsToServer(Context ctx) {
        String[] crFiles = getCrashReportFiles(ctx);
        if (crFiles != null && crFiles.length > 0) {
            TreeSet<String> sortedFiles = new TreeSet<String>();
            sortedFiles.addAll(Arrays.asList(crFiles));

            for (String fileName : sortedFiles) {
                File cr = new File(ctx.getFilesDir(), fileName);
                postReport(cr);
                cr.delete();// 删除已发送的报告
            }
        }
    }

    /**
     * 获取错误报告文件名
     *
     * @param ctx
     * @return
     */
    private String[] getCrashReportFiles(Context ctx) {
        File filesDir = ctx.getFilesDir();
        // 实现FilenameFilter接口的类实例可用于过滤器文件名
        FilenameFilter filter = new FilenameFilter() {
            // accept(File dir, String name)
            // 测试指定文件是否应该包含在某一文件列表中。
            public boolean accept(File dir, String name) {
                return name.endsWith(CRASH_REPORTER_EXTENSION);
            }
        };
        // list(FilenameFilter filter)
        // 返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中满足指定过滤器的文件和目录
        return filesDir.list(filter);
    }

    /**
     * 发送后服务器
     *
     * @param file
     */
    private void postReport(File file) {
        // TODO 使用HTTP Post 发送错误报告到服务器
        // 这里不再详述,开发者可以根据OPhoneSDN上的其他网络操作
        // 教程来提交错误报告
    }

    /**
     * 在程序启动时候, 可以调用该函数来发送以前没有发送的报告
     */
    public void sendPreviousReportsToServer() {
        sendCrashReportsToServer(mContext);
    }

}
