package com.common.bean.paramsBean;

import android.app.PendingIntent;

/**
 * Created by tanlifei on 16/3/23.
 */
public class NotifyParams {


    private String title;//设置通知中心的标题
    private String content;//设置通知中心中的内容
    private boolean sound = true;//是否有声音
    private boolean vibrate = true;//是否振动
    private boolean lights = true;//是否闪光
    private int smallIcon;//设置顶部状态栏的小图标
    private String ticker;//在顶部状态栏中的提示信息
    private int bigPic;
    private int largeIcon;
    private int leftBtnIcon;
    private String leftText;
    private PendingIntent leftPendingIntent;
    private int rightBtnIcon;
    private String rightText;
    private PendingIntent rightPendingIntent;
    private PendingIntent pendingIntent;//该通知要启动的Intent


    public PendingIntent getRightPendingIntent() {
        return rightPendingIntent;
    }

    public void setRightPendingIntent(PendingIntent rightPendingIntent) {
        this.rightPendingIntent = rightPendingIntent;
    }

    public int getLeftBtnIcon() {
        return leftBtnIcon;
    }

    public void setLeftBtnIcon(int leftBtnIcon) {
        this.leftBtnIcon = leftBtnIcon;
    }

    public PendingIntent getLeftPendingIntent() {
        return leftPendingIntent;
    }

    public void setLeftPendingIntent(PendingIntent leftPendingIntent) {
        this.leftPendingIntent = leftPendingIntent;
    }

    public String getLeftText() {
        return leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
    }

    public int getRightBtnIcon() {
        return rightBtnIcon;
    }

    public void setRightBtnIcon(int rightBtnIcon) {
        this.rightBtnIcon = rightBtnIcon;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public int getBigPic() {
        return bigPic;
    }

    public void setBigPic(int bigPic) {
        this.bigPic = bigPic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLargeIcon() {
        return largeIcon;
    }

    public void setLargeIcon(int largeIcon) {
        this.largeIcon = largeIcon;
    }

    public boolean isLights() {
        return lights;
    }

    public void setLights(boolean lights) {
        this.lights = lights;
    }

    public PendingIntent getPendingIntent() {
        return pendingIntent;
    }

    public void setPendingIntent(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }

    public int getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(int smallIcon) {
        this.smallIcon = smallIcon;
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }
}
