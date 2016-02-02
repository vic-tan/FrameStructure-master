package com.common.ui.base.main;


import com.common.exception.CrashHandler;
import com.common.utils.ImageLoaderUtils;
import com.constants.fixed.OnOffConstants;
import com.constants.level.OnOffLevel;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

/**
 * 全局Application
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public class BaseApplication extends LitePalApplication {
    public static ImageLoader imageLoader;
    @Override
    public void onCreate() {
        super.onCreate();
        setCrashHandler();//打开全局未捕获异常
        initImageLoader();//初始化图片加载缓存 ImageLoader基本配置
        inittDatabase();//litepal创建数据表
    }


    /**
     * 初始化图片加载缓存 ImageLoader基本配置
     */
    private void initImageLoader() {
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderUtils.initConfigImageLoader(getContext()));
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

    //litepal创建数据表
    private void inittDatabase(){
        Connector.getDatabase();//litepal创建数据表
    }

}
