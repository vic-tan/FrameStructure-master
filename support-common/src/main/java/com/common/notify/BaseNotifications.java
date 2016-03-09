package com.common.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * 公用通知栏
 */
public class BaseNotifications {

    //Notification管理
    protected NotificationManager mNotificationManager;
    protected NotificationCompat.Builder mBuilder;


    protected BaseNotifications(Context context) {
        mBuilder = new NotificationCompat.Builder(context);
        mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        mBuilder.setContentIntent(getDefalutIntent(context, Notification.FLAG_AUTO_CANCEL))
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE);//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
    }


    /**
     * @获取默认的pendingIntent,为了防止2.3及以下版本报错
     * @flags属性: 在顶部常驻:Notification.FLAG_ONGOING_EVENT
     * 点击去除： Notification.FLAG_AUTO_CANCEL
     */
    protected PendingIntent getDefalutIntent(Context context, int flags) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, new Intent(), flags);
        return pendingIntent;
    }


}
