package com.common.ui.base.main;


import android.app.Application;
import android.content.Context;

import com.common.db.DaoMaster;
import com.common.exception.CrashHandler;
import com.common.okhttp.OkHttpUtils;
import com.common.utils.FileUtils;
import com.common.utils.ImageLoaderUtils;
import com.constants.fixed.GlobalConstants;
import com.constants.fixed.OnOffConstants;
import com.constants.level.OnOffLevel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.concurrent.TimeUnit;

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
    private String CER_12306 = "-----BEGIN CERTIFICATE-----\n" +
            "MIICmjCCAgOgAwIBAgIIbyZr5/jKH6QwDQYJKoZIhvcNAQEFBQAwRzELMAkGA1UEBhMCQ04xKTAn\n" +
            "BgNVBAoTIFNpbm9yYWlsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRTUkNBMB4X\n" +
            "DTA5MDUyNTA2NTYwMFoXDTI5MDUyMDA2NTYwMFowRzELMAkGA1UEBhMCQ04xKTAnBgNVBAoTIFNp\n" +
            "bm9yYWlsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRTUkNBMIGfMA0GCSqGSIb3\n" +
            "DQEBAQUAA4GNADCBiQKBgQDMpbNeb34p0GvLkZ6t72/OOba4mX2K/eZRWFfnuk8e5jKDH+9BgCb2\n" +
            "9bSotqPqTbxXWPxIOz8EjyUO3bfR5pQ8ovNTOlks2rS5BdMhoi4sUjCKi5ELiqtyww/XgY5iFqv6\n" +
            "D4Pw9QvOUcdRVSbPWo1DwMmH75It6pk/rARIFHEjWwIDAQABo4GOMIGLMB8GA1UdIwQYMBaAFHle\n" +
            "tne34lKDQ+3HUYhMY4UsAENYMAwGA1UdEwQFMAMBAf8wLgYDVR0fBCcwJTAjoCGgH4YdaHR0cDov\n" +
            "LzE5Mi4xNjguOS4xNDkvY3JsMS5jcmwwCwYDVR0PBAQDAgH+MB0GA1UdDgQWBBR5XrZ3t+JSg0Pt\n" +
            "x1GITGOFLABDWDANBgkqhkiG9w0BAQUFAAOBgQDGrAm2U/of1LbOnG2bnnQtgcVaBXiVJF8LKPaV\n" +
            "23XQ96HU8xfgSZMJS6U00WHAI7zp0q208RSUft9wDq9ee///VOhzR6Tebg9QfyPSohkBrhXQenvQ\n" +
            "og555S+C3eJAAVeNCTeMS3N/M5hzBRJAoffn3qoYdAO1Q8bTguOi+2849A==\n" +
            "-----END CERTIFICATE-----";

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        setCrashHandler();//打开全局未捕获异常
        initImageLoader();//初始化图片加载缓存 ImageLoader基本配置
        inittDatabase();//greenDAO创建数据表
        initCreateFolders();//创建文件夹
        initOKhttp();//初始化Okhttp
    }


    /**
     * 初始化Okhttp
     */
    private void initOKhttp() {
        //这里可以设置自签名证书
//        OkHttpUtils.getInstance().setCertificates(new InputStream[]{
//                new Buffer()
//                        .writeUtf8(CER_12306)
//                        .inputStream()});
        OkHttpUtils.getInstance().debug("OkHttpUtils",true).setConnectTimeout(100000, TimeUnit.MILLISECONDS);
        //使用https，但是默认信任全部证书
        OkHttpUtils.getInstance().setCertificates();
        //使用这种方式，设置多个OkHttpClient参数
//        OkHttpUtils.getInstance(new OkHttpClient.Builder().build());
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
    private void initCreateFolders() {
        FileUtils.makeFolders(GlobalConstants.CRASH_PATH);//针对全局未捕获异常，保存到本志文件路径
        FileUtils.makeFolders(GlobalConstants.DOWNLOAD_PATH);//文件下载保存路径
        FileUtils.makeFolders(GlobalConstants.IMAGES_CACHE_PATH);//针对全局图片缓存路径
    }

}
