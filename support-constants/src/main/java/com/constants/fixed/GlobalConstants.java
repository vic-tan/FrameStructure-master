package com.constants.fixed;


import android.os.Environment;

/**
 * 程序里全局引用常量，不确定所属于其它常量
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public class GlobalConstants {

    /**
     * 针对全局未捕获异常，保存到本志文件路径
     */
    public static final String CRASH_PATH = Environment.getExternalStorageDirectory().toString() + "/framestructure/crash/";

    /**
     * 针对全局图片缓存路径
     */
    public static final String IMAGES_CACHE_PATH = Environment.getExternalStorageDirectory().toString() + "/framestructure/images/cache/";

    /**
     * 文件下载保存路径
     */
    public static final String DOWNLOAD_PATH = Environment.getExternalStorageDirectory().toString() + "/framestructure/download/";

    /**
     * 数据库名字
     **/
    public static final String DB_NAME = "framestucture";

    //region Description 下载常量

    public static final String KEY_DOWNLOAD_ENTRY = "key_download_entry";
    public static final String KEY_DOWNLOAD_ACTION = "key_download_action";
    public static final int KEY_DOWNLOAD_ACTION_ADD = 0;
    public static final int KEY_DOWNLOAD_ACTION_PAUSE = 1;
    public static final int KEY_DOWNLOAD_ACTION_RESUME = 2;
    public static final int KEY_DOWNLOAD_ACTION_CANCEL = 3;
    public static final int KEY_DOWNLOAD_ACTION_PAUSE_ALL = 4;
    public static final int KEY_DOWNLOAD_ACTION_RECOVER_ALL = 5;
    public static final int CONNECT_TIME = 10 * 1000;
    public static final int READ_TIME = 10 * 1000;
    //endregion

}


