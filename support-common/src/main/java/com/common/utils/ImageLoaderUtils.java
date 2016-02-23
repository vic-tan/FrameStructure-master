package com.common.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.constants.fixed.GlobalConstants;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

/**
 * 图片加载缓存配置工具类
 * <ul>
 * <li>{@link #initConfigImageLoader(Context) ImageLoader初始化配置}</li>
 * <li>{@link #displayConfigDisplay()} 加载图片设置（加载中，加载失败，加载路径为null 都没有默认图片时）</li>
 * <li>{@link #displayConfigDisplay(Integer)} 加载图片设置（加载中，加载失败，加载路径为null 都为同一张默认图片时）</li>
 * <li>{@link #displayConfigDisplay(Integer, Integer, Integer)} 加载图片设置（加载中，加载失败，加载路径为null 为各不同默认图片时）</li>
 * <li>{@link #setCommonBuilder()} 共用的默认加载图片Builder设置</li>
 * 清单文件需要添加权限
 * <uses-permission android:name="android.permission.INTERNET" />
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 *
 * @author tanlifei
 * @date 2015-01-26 下午3:30:25
 */


public class ImageLoaderUtils {

    public static final int FADE_IN_TIME = 300;//渐出时间
    public static final int THREAD_NUM = 3;//线程池内加载的数量
    public static final int LRU_MEMORY_CACHE = 50 * 1024 * 1024;//内存缓存50MB
    public static final int LRU_DISK_CACHE_NUM = 500;//Sdcard缓存内存的文件数量
    public static final int DISK_CACHE_SIZE = 50 * 1024 * 1024;//硬盘缓存50MB
    public static final int CONNECT_TIME_OUT = 5000;//连接时间(5s)
    public static final int READ_TIME_OUT = 30000;//超时时间(30s)
    public static ImageLoaderConfiguration imageLoaderConfiguration;

    /**
     * ImageLoader初始化配置
     *
     * @param context
     * @return
     */
    public static ImageLoaderConfiguration initConfigImageLoader(Context context) {
        return imageLoaderConfiguration != null ? imageLoaderConfiguration : createConfigImageLoader(context);
    }


    public static ImageLoaderConfiguration createConfigImageLoader(Context context) {
        imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).threadPoolSize(THREAD_NUM)//线程池内加载的数量
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .memoryCache(new LruMemoryCache((int) (Runtime.getRuntime().maxMemory() / 5)))//缓存的文件
                .diskCache(new UnlimitedDiskCache(new File(GlobalConstants.IMAGES_CACHE_PATH)))//图片缓存本地地址
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                        //.diskCacheFileCount(LRU_DISK_CACHE_NUM)//缓存的文件数量
                .memoryCacheSize(LRU_MEMORY_CACHE)
                .imageDownloader(new BaseImageDownloader
                        (context, CONNECT_TIME_OUT, READ_TIME_OUT)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .defaultDisplayImageOptions(setCommonBuilder().build())
                .diskCacheSize(DISK_CACHE_SIZE).writeDebugLogs().build();
        return imageLoaderConfiguration;
    }

    /**
     * 加载图片设置（加载中，加载失败，加载路径为null 都没有默认图片时）
     *
     * @return
     */
    public static DisplayImageOptions displayConfigDisplay() {
        return setCommonBuilder().displayer(new FadeInBitmapDisplayer(FADE_IN_TIME)).build();

    }


    /**
     * 加载图片设置（加载中，加载失败，加载路径为null 都为同一张默认图片时）
     *
     * @param defaultImage
     * @return
     */
    public static DisplayImageOptions displayConfigDisplay(Integer defaultImage) {
        return setCommonBuilder().showImageOnLoading(defaultImage)
                .showImageForEmptyUri(defaultImage).showImageOnFail(defaultImage).displayer(new FadeInBitmapDisplayer(FADE_IN_TIME)).build();

    }


    /**
     * 加载图片设置（加载中，加载失败，加载路径为null 为各不同默认图片时）
     *
     * @param loading 正在加载时显示的图片
     * @param empty   加载前默认图片
     * @param fail    加载错误时显示的图片
     * @return
     */
    public static DisplayImageOptions displayConfigDisplay(Integer loading,
                                                           Integer empty, Integer fail) {
        return setCommonBuilder().showImageOnLoading(loading)
                .showImageForEmptyUri(empty).showImageOnFail(fail).displayer(new FadeInBitmapDisplayer(FADE_IN_TIME)).build();

    }


    /**
     * 共用的默认加载图片Builder设置
     * 避免OOM
     * 1、使用.displayer(new RoundedBitmapDisplayer(20)) //他会创建新的ARGB_8888格式的Bitmap对象；
     * 2、使用.imageScaleType(ImageScaleType.IN_SAMPLE_INT) 或 imageScaleType(ImageScaleType.EXACTLY)
     * 3、默认是ARGB_8888，使用RGB_565会比使用ARGB_8888少消耗2倍的内
     *
     * @return
     */
    public static DisplayImageOptions.Builder setCommonBuilder() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
                .considerExifParams(false)// 打印控制台信息信息
                .imageScaleType(ImageScaleType.EXACTLY)// 设置图片以如何的编码方式
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型
                //.delayBeforeLoading(100)// 设置图片下载前的延迟
                .resetViewBeforeLoading(true);// 设置图片在下载前是否重置，复位;


    }

}
