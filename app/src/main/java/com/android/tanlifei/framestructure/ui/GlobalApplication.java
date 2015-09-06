package com.android.tanlifei.framestructure.ui;

import android.app.Application;
import android.content.Context;

import com.android.tanlifei.framestructure.common.constants.StatusConstants;
import com.android.tanlifei.framestructure.common.constants.enumConstants.UncaughtExLevel;
import com.android.tanlifei.framestructure.common.exception.CrashHandler;
import com.android.tanlifei.framestructure.common.utils.ImageConfigUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 全局Application
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */

public class GlobalApplication extends Application {

    public static Context appContext;//全局上下文
    public static ImageLoader imageLoader;


    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        setCrashHandler();//打开全局未捕获异常
        initImageLoader();//初始化图片加载缓存 ImageLoader基本配置
    }



    /**
     * 初始化图片加载缓存 ImageLoader基本配置
     */
    private void initImageLoader() {
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageConfigUtils.initConfigImageLoader(getApplicationContext()));
    }

    /**
     * 设置是否开启全局未捕获异常
     */
    private void setCrashHandler() {
        if (StatusConstants.UNCAUGHT_EX_LEVEL == UncaughtExLevel.NONE) {//不写入
            return;
        }
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());// 注册crashHandler
    }

}
