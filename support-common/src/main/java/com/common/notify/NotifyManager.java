package com.common.notify;

import android.content.Context;

import com.common.R;
import com.common.bean.base.NotifyBean;
import com.common.download.entity.DownloadEntry;
import com.constants.level.DownloadStatusLevel;

/**
 *
 */
public class NotifyManager extends BaseNotifications {
    //Notification管理
    private static volatile NotifyManager instance = null;


    private NotifyManager(Context context) {
        super(context);
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
     * 平常通知
     *
     * @param bean
     */
    public void usuallyNotify(NotifyBean bean) {
        mBuilder.setContentTitle(bean.getTitle())
                .setContentText(bean.getContent())
                .setTicker(bean.getTitle())//通知首次出现在通知栏，带上升动画效果的.
                .setSmallIcon(bean.getIconId());
        mNotificationManager.notify(bean.getNotifyId(), mBuilder.build());
    }

    /**
     * 显示带进度条通知栏
     */
    public void progressNotify(DownloadEntry bean) {
        mBuilder.setContentTitle(bean.getName())
                .setContentText(bean.getName())
                .setTicker(bean.getName())//通知首次出现在通知栏，带上升动画效果的.
                .setSmallIcon(R.mipmap.ic_launcher);
        if (bean.getStatus() == DownloadStatusLevel.IDLE.value()) {
            //不确定进度的
            mBuilder.setProgress(0, 0, true);
        } else {
            //确定进度的
            mBuilder.setProgress(bean.getTotalLength(), bean.getCurrentLength(), false); // 这个方法是显示进度条  设置为true就是不确定的那种进度条效果
        }
        mNotificationManager.notify(1000, mBuilder.build());
    }
}
