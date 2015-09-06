package com.android.tanlifei.framestructure.common.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * 图片加载缓存配置工具类
 * <ul>
 * <strong> 初始化配置 </strong>
 * <li>{@link #initConfigImageLoader(Context) ImageLoader初始化配置}</li>
 * </ul>
 * <ul>
 * <strong> 显示图片的方法 </strong>
 * <li>{@link #setConfigDisplay(Integer, Integer, Integer)} 有默认加载图片图片显示设置</li>
 * </ul>
 * <p/>
 * 清单文件需要添加权限
 * <uses-permission android:name="android.permission.INTERNET" />
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 *
 * @author tanlifei
 * @date 2015-01-26 下午3:30:25
 */


public class ImageConfigUtils {

    /**
     * ImageLoader初始化配置
     *
     * @param context
     * @return
     */
    public static ImageLoaderConfiguration initConfigImageLoader(Context context) {
        return new ImageLoaderConfiguration.Builder(context).threadPriority(3)//线程池内加载的数量
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .memoryCache(new LruMemoryCache(1024 * 1024 * 2))//缓存的文件数量
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .diskCacheFileCount(500)//缓存的文件数量
                .imageDownloader(new BaseImageDownloader
                        (context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .diskCacheSize(1024 * 1024 * 50).build();
    }


    /**
     * 有默认加载图片图片显示设置
     *
     * @param loading 正在加载时显示的图片
     * @param empty   加载前默认图片
     * @param fail    加载错误时显示的图片
     * @return
     */
    public static DisplayImageOptions setConfigDisplay(Integer loading,
                                                       Integer empty, Integer fail) {
        return new DisplayImageOptions.Builder().showImageOnLoading(loading)
                .showImageForEmptyUri(empty).showImageOnFail(fail)
                .cacheInMemory(true).cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new SimpleBitmapDisplayer()).build();

    }

    /**
     * 有默认加载圆角图片显示设置
     *
     * @param onProgress   正在加载时显示的图片
     * @param onEmpty      加载前默认图片
     * @param onFailure    加载错误时显示的图片
     * @param circleRadius 圆形图片的半径
     * @return
     */
    public static DisplayImageOptions setConfigDisplay(Integer onProgress, Integer onEmpty,
                                                       Integer onFailure, Integer circleRadius) {
        return new DisplayImageOptions.Builder().showImageOnLoading(onProgress) // 设置图片Uri加载中的时候显示的图片
                .showImageForEmptyUri(onEmpty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(onFailure) // 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true) // 是否緩存都內存中
                .cacheOnDisk(true) // 是否緩存到sd卡上
                .bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的解码类型
                .displayer(new RoundedBitmapDisplayer(circleRadius)) // 设置圆角图片
                .build();

    }
}
