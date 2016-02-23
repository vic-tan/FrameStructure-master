package com.common.ui.base.main;


import android.app.Application;
import android.content.Context;

import com.common.db.DaoMaster;
import com.common.exception.CrashHandler;
import com.common.utils.FileUtils;
import com.common.utils.ImageLoaderUtils;
import com.constants.fixed.GlobalConstants;
import com.constants.fixed.OnOffConstants;
import com.constants.level.OnOffLevel;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 全局Application
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public class BaseApplication extends Application {

    public static ImageLoader imageLoader;
    public static Context appContext;
    public static DaoMaster daoMaster;
    public static DaoMaster.DevOpenHelper helper;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        setCrashHandler();//打开全局未捕获异常
        initImageLoader();//初始化图片加载缓存 ImageLoader基本配置
        inittDatabase();//greenDAO创建数据表
        initCreateFolders();//创建文件夹
    }


    /**
     * 初始化图片加载缓存 ImageLoader基本配置
     */
    private void initImageLoader() {
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderUtils.initConfigImageLoader(getApplicationContext()));
    }

    /**
     * 设置是否开启全局未捕获异常
     */
    private void setCrashHandler() {
        if (OnOffConstants.UNCAUGHT_EX_LEVEL == OnOffLevel.OFF) {//不写入
            return;
        }
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());// 注册crashHandler
    }

    //greenDAO创建数据表
    private void inittDatabase() {
        helper = new DaoMaster.DevOpenHelper(this, GlobalConstants.DB_NAME, null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
    }

    /**
     * 创建文件夹
     */
    private void initCreateFolders(){
        FileUtils.makeFolders(GlobalConstants.CRASH_PATH);//针对全局未捕获异常，保存到本志文件路径
        FileUtils.makeFolders(GlobalConstants.DOWNLOAD_PATH);//文件下载保存路径
        FileUtils.makeFolders(GlobalConstants.IMAGES_CACHE_PATH);//针对全局图片缓存路径
    }

}
