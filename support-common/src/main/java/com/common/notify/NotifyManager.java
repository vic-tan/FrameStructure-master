package com.common.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.common.R;
import com.common.bean.base.NotifyBean;
import com.common.download.entity.DownloadEntry;
import com.constants.level.DownloadStatusLevel;

/**
 *
 */
public class NotifyManager {
    //Notification管理
    private static volatile NotifyManager instance = null;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;


    private NotifyManager(Context context) {
        mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentIntent(getDefalutIntent(context, Notification.FLAG_AUTO_CANCEL))
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE);//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
    }

    public static NotifyManager getInstance(Context context) {
        if (instance == null) {
            synchronized (NotifyManager.class) {
                if (instance == null) {
                    instance = new NotifyManager(context);
                }
            }
        }
        return instance;
    }

    /**
     * 清除当前创建的通知栏
     */
    public void clearNotify(int notifyId) {
        mNotificationManager.cancel(notifyId);//删除一个特定的通知ID对应的通知
    }

    /**
     * 清除所有通知栏
     */
    public void clearAllNotify() {
        mNotificationManager.cancelAll();// 删除你发的所有通知
    }

    /**
     * @获取默认的pendingIntent,为了防止2.3及以下版本报错
     * @flags属性: 在顶部常驻:Notification.FLAG_ONGOING_EVENT
     * 点击去除： Notification.FLAG_AUTO_CANCEL
     */
    private PendingIntent getDefalutIntent(Context context, int flags) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, new Intent(), flags);
        return pendingIntent;
    }

    /**
     * 平常通知
     * @param bean
     */
    public void usuallyNotify(NotifyBean bean) {
        mBuilder.setContentTitle(bean.getTitle())
                .setContentText(bean.getContent())
                .setTicker(bean.getTitle())//通知首次出现在通知栏，带上升动画效果的.
                .setSmallIcon(bean.getIconId());
        mNotificationManager.notify(bean.getNotifyId(),mBuilder.build());
    }

    /** 显示带进度条通知栏 */
    public void progressNotify(DownloadEntry bean) {
        mBuilder.setContentTitle(bean.getName())
                .setContentText(bean.getName())
                .setTicker(bean.getName())//通知首次出现在通知栏，带上升动画效果的.
                .setSmallIcon(R.mipmap.ic_launcher);
        if(bean.getStatus()== DownloadStatusLevel.IDLE.value()){
            //不确定进度的
            mBuilder.setProgress(0, 0, true);
        }else{
            //确定进度的
            mBuilder.setProgress(bean.getTotalLength(), bean.getCurrentLength(), false); // 这个方法是显示进度条  设置为true就是不确定的那种进度条效果
        }
        mNotificationManager.notify(1000000, mBuilder.build());
    }
}
