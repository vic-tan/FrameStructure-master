package com.common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.common.bean.paramsBean.NotifyParams;
import com.common.download.entity.DownloadEntry;
import com.constants.level.DownloadStatusLevel;

import java.util.ArrayList;

@SuppressLint("NewApi")
public class NotifyUtils {

    private static final int FLAG = Notification.FLAG_INSISTENT;
    int requestCode = (int) SystemClock.uptimeMillis();
    private int NOTIFICATION_ID;
    private NotificationManager nm;
    private Notification notification;
    private NotificationCompat.Builder cBuilder;
    private Notification.Builder nBuilder;
    private Context mContext;


    public NotifyUtils(Context context, int ID) {
        this.NOTIFICATION_ID = ID;
        mContext = context;
        // 获取系统服务来初始化对象
        nm = (NotificationManager) mContext
                .getSystemService(Activity.NOTIFICATION_SERVICE);
        cBuilder = new NotificationCompat.Builder(mContext);
    }

    /**
     * 设置在顶部通知栏中的各种信息
     */
    private void setCompatBuilder(NotifyParams params) {
//        // 如果当前Activity启动在前台，则不开启新的Activity。
//        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        // 当设置下面PendingIntent.FLAG_UPDATE_CURRENT这个参数的时候，常常使得点击通知栏没效果，你需要给notification设置一个独一无二的requestCode
//        // 将Intent封装进PendingIntent中，点击通知的消息后，就会启动对应的程序
//        PendingIntent pIntent = PendingIntent.getActivity(mContext,
//                requestCode, intent, FLAG);

        cBuilder.setContentIntent(params.getPendingIntent());// 该通知要启动的Intent
        cBuilder.setSmallIcon(params.getSmallIcon());// 设置顶部状态栏的小图标
        cBuilder.setTicker(params.getTicker());// 在顶部状态栏中的提示信息
        cBuilder.setContentTitle(params.getTitle());// 设置通知中心的标题
        cBuilder.setContentText(params.getContent());// 设置通知中心中的内容
        cBuilder.setWhen(System.currentTimeMillis());
        /*
         * 将AutoCancel设为true后，当你点击通知栏的notification后，它会自动被取消消失,
		 * 不设置的话点击消息后也不清除，但可以滑动删除
		 */
        cBuilder.setAutoCancel(true);
        // 将Ongoing设为true 那么notification将不能滑动删除
        // notifyBuilder.setOngoing(true);
        /*
         * 从Android4.1开始，可以通过以下方法，设置notification的优先级，
		 * 优先级越高的，通知排的越靠前，优先级低的，不会在手机最顶部的状态栏显示图标
		 */
        cBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        /*
         * Notification.DEFAULT_ALL：铃声、闪光、震动均系统默认。
		 * Notification.DEFAULT_SOUND：系统默认铃声。
		 * Notification.DEFAULT_VIBRATE：系统默认震动。
		 * Notification.DEFAULT_LIGHTS：系统默认闪光。
		 * notifyBuilder.setDefaults(Notification.DEFAULT_ALL);
		 */
        int defaults = 0;

        if (params.isSound()) {
            defaults |= Notification.DEFAULT_SOUND;
        }
        if (params.isVibrate()) {
            defaults |= Notification.DEFAULT_VIBRATE;
        }
        if (params.isLights()) {
            defaults |= Notification.DEFAULT_LIGHTS;
        }

        cBuilder.setDefaults(defaults);
    }

    /**
     * 设置builder的信息，在用大文本时会用到这个
     */
    private void setBuilder(NotifyParams params) {
        nBuilder = new Notification.Builder(mContext);
        // 如果当前Activity启动在前台，则不开启新的Activity。
       /* intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(mContext,
                requestCode, intent, FLAG);*/
        nBuilder.setContentIntent(params.getPendingIntent());

        nBuilder.setSmallIcon(params.getSmallIcon());


        nBuilder.setTicker(params.getTicker());
        nBuilder.setWhen(System.currentTimeMillis());
        nBuilder.setPriority(Notification.PRIORITY_MAX);

        int defaults = 0;

        if (params.isSound()) {
            defaults |= Notification.DEFAULT_SOUND;
        }
        if (params.isVibrate()) {
            defaults |= Notification.DEFAULT_VIBRATE;
        }
        if (params.isLights()) {
            defaults |= Notification.DEFAULT_LIGHTS;
        }

        nBuilder.setDefaults(defaults);
    }

    /**
     * 普通的通知
     * <p/>
     * 1. 侧滑即消失，下拉通知菜单则在通知菜单显示
     */
    public void notifyNormalSingline(NotifyParams params) {
        setCompatBuilder(params);
        sent();
    }

    /**
     * 进行多项设置的通知(在小米上似乎不能设置大图标，系统默认大图标为应用图标)
     */
    public void notifyMailbox(NotifyParams params, ArrayList<String> messageList) {

        setCompatBuilder(params);

        // 将Ongoing设为true 那么notification将不能滑动删除
        //cBuilder.setOngoing(true);

        /**
         // 删除时
         Intent deleteIntent = new Intent(mContext, DeleteService.class);
         int deleteCode = (int) SystemClock.uptimeMillis();
         // 删除时开启一个服务
         PendingIntent deletePendingIntent = PendingIntent.getService(mContext,
         deleteCode, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
         cBuilder.setDeleteIntent(deletePendingIntent);

         **/

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), params.getLargeIcon());
        cBuilder.setLargeIcon(bitmap);

        cBuilder.setDefaults(Notification.DEFAULT_ALL);// 设置使用默认的声音
        //cBuilder.setVibrate(new long[]{0, 100, 200, 300});// 设置自定义的振动
        cBuilder.setAutoCancel(true);
        // builder.setSound(Uri.parse("file:///sdcard/click.mp3"));

        // 设置通知样式为收件箱样式,在通知中心中两指往外拉动，就能出线更多内容，但是很少见
        //cBuilder.setNumber(messageList.size());
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        for (String msg : messageList) {
            inboxStyle.addLine(msg);
        }
        inboxStyle.setSummaryText("[" + messageList.size() + "条]" + params.getTitle());
        cBuilder.setStyle(inboxStyle);
        sent();
    }

    /**
     * 自定义视图的通知
     */
    public void notifyCustomview(RemoteViews remoteViews, NotifyParams params) {

        setCompatBuilder(params);

        notification = cBuilder.build();
        notification.contentView = remoteViews;
        // 发送该通知
        nm.notify(NOTIFICATION_ID, notification);
    }

    /**
     * 可以容纳多行提示文本的通知信息 (因为在高版本的系统中才支持，所以要进行判断)
     */
    public void notifyNormailMoreline(NotifyParams params) {

        final int sdk = Build.VERSION.SDK_INT;
        if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
            notifyNormalSingline(params);
            Toast.makeText(mContext, "您的手机低于Android 4.1.2，不支持多行通知显示！！", Toast.LENGTH_SHORT).show();
        } else {
            //setBuilder(params.getPendingIntent(), params.getSmallIcon(), params.getTicker(), true, true, false);
            setBuilder(params);
            nBuilder.setContentTitle(params.getTitle());
            nBuilder.setContentText(params.getContent());
            nBuilder.setPriority(Notification.PRIORITY_HIGH);
            notification = new Notification.BigTextStyle(nBuilder).bigText(params.getContent()).build();
            // 发送该通知
            nm.notify(NOTIFICATION_ID, notification);
        }
    }

    /**
     * 有进度条的通知，可以设置为模糊进度或者精确进度
     */
    public void notifyProgress(NotifyParams params, DownloadEntry entry) {
        setCompatBuilder(params);
        if(entry.getStatus()== DownloadStatusLevel.DONE.value()){
            cBuilder.setContentText("下载完成").setProgress(0, 0, false);
            sent();
        }else{
            cBuilder.setProgress(entry.getTotalLength(), entry.getCurrentLength(), false);
            sent();
        }
    }

    /**
     * 有进度条的通知，可以设置为模糊进度或者精确进度
     */
    public void notifyDownloadProgress(NotifyParams params,DownloadEntry entry) {
        cBuilder.setSmallIcon(params.getSmallIcon());// 设置顶部状态栏的小图标
        cBuilder.setTicker(params.getTicker());// 在顶部状态栏中的提示信息
        cBuilder.setContentTitle(params.getTitle());// 设置通知中心的标题
        cBuilder.setContentText(params.getContent());// 设置通知中心中的内容
        cBuilder.setWhen(System.currentTimeMillis());
        cBuilder.setAutoCancel(true);
        cBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        int defaults = 0;
        if (params.isSound()) {
            defaults |= Notification.DEFAULT_SOUND;
        }
        if (params.isVibrate()) {
            defaults |= Notification.DEFAULT_VIBRATE;
        }
        if (params.isLights()) {
            defaults |= Notification.DEFAULT_LIGHTS;
        }

        cBuilder.setDefaults(defaults);
        if(entry.getStatus()== DownloadStatusLevel.DONE.value()){
            cBuilder.setContentText("下载完成").setProgress(0, 0, false);
            sent();
        }else{
            cBuilder.setProgress(entry.getTotalLength(), entry.getCurrentLength(), false);
            sent();
        }
    }

    /**
     * 容纳大图片的通知
     */
    public void notifyBigPic(NotifyParams params) {
        //setCompatBuilder(pendingIntent, smallIcon, ticker, title, null, sound, vibrate, lights);
        setCompatBuilder(params);
        NotificationCompat.BigPictureStyle picStyle = new NotificationCompat.BigPictureStyle();
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        options.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),
                params.getBigPic(), options);
        picStyle.bigPicture(bitmap);
        picStyle.bigLargeIcon(bitmap);
        cBuilder.setContentText(params.getContent());
        cBuilder.setStyle(picStyle);
        sent();
    }

    /**
     * 里面有两个按钮的通知
     */
    public void notifyButton(NotifyParams params) {

        requestCode = (int) SystemClock.uptimeMillis();
        //setCompatBuilder(rightPendIntent, smallIcon, ticker, title, content, sound, vibrate, lights);
        setCompatBuilder(params);
        cBuilder.addAction(params.getLeftBtnIcon(),
                params.getLeftText(), params.getLeftPendingIntent());
        cBuilder.addAction(params.getRightBtnIcon(),
                params.getRightText(), params.getRightPendingIntent());
        sent();
    }

    public void notifyHeadUp(NotifyParams params) {

        setCompatBuilder(params);
        cBuilder.setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), params.getLargeIcon()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cBuilder.addAction(params.getLeftBtnIcon(),
                    params.getLeftText(), params.getLeftPendingIntent());
            cBuilder.addAction(params.getRightBtnIcon(),
                    params.getRightText(), params.getRightPendingIntent());
        } else {
            Toast.makeText(mContext, "版本低于Andriod5.0，无法体验HeadUp样式通知", Toast.LENGTH_SHORT).show();
        }
        sent();
    }


    /**
     * 发送通知
     */
    private void sent() {
        notification = cBuilder.build();
        // 发送该通知
        nm.notify(NOTIFICATION_ID, notification);
    }

    /**
     * 根据id清除通知
     */
    public void clear() {
        // 取消通知
        nm.cancelAll();
    }
}
