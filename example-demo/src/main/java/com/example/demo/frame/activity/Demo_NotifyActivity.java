/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.demo.frame.activity;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RemoteViews;

import com.common.bean.base.NotifyBean;
import com.common.bean.paramsBean.NotifyParams;
import com.common.utils.InflaterUtils;
import com.common.utils.NotifyUtils;
import com.common.utils.ToastUtils;
import com.example.demo.R;
import com.example.demo.frame.baseAdapter.adapter.MyAdapter;
import com.example.demo.frame.table.GridViewActivity;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;


public class Demo_NotifyActivity extends AutoLayoutActivity {


    private static final int FLAG = Notification.FLAG_INSISTENT;
    private static int NOTIFICATION_ID = 13565400;
    private Context mContext;
    private View mView;
    private NotifyUtils currentNotify;
    private ArrayList<NotifyBean> mDataList;
    private ListView mListView;
    private PhotoView mScaleImage;
    private int requestCode = (int) SystemClock.uptimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mView = InflaterUtils.inflater(this,R.layout.test_activity_notify);
        setContentView(mView);
        initDatas();
        initListView();
    }

/*  @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }*/



    private void initListView() {
        mListView = (ListView) mView.findViewById(R.id.listview);
        mScaleImage = (PhotoView) mView.findViewById(R.id.scaleImage);
        MyAdapter adapter = new MyAdapter(mContext, mDataList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        notify_normal_singLine();
                        mScaleImage.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.image1));
                        break;
                    case 1:
                        notify_normal_moreLine();
                        mScaleImage.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.image2));
                        break;
                    case 2:
                        notify_mailbox();
                        mScaleImage.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.image3));
                        break;
                    case 3:
                        notify_bigPic();
                        mScaleImage.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.image4));
                        break;
                    case 4:
                        notify_customview();
                        mScaleImage.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.image5));
                        break;
                    case 5:
                        notify_buttom();
                        mScaleImage.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.image6));
                        break;
                    case 6:
                        notify_progress();
                        mScaleImage.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.image7));
                        break;
                    case 7:
                        notify_headUp();
                        mScaleImage.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.image8));
                        break;
                    case 8:
                        if (currentNotify != null) {
                            currentNotify.clear();
                        }
                        break;
                }
            }
        });
    }

    private void initDatas() {
        mDataList = new ArrayList<>();
        NotifyBean notifybean1 = new NotifyBean();
        notifybean1.setImageId(R.mipmap.tb_bigicon);
        notifybean1.setTitleId(R.string.title1);
        notifybean1.setTypeId(R.string.type1);
        mDataList.add(notifybean1);
        NotifyBean notifybean2 = new NotifyBean();
        notifybean2.setImageId(R.mipmap.netease_bigicon);
        notifybean2.setTitleId(R.string.title2);
        notifybean2.setTypeId(R.string.type2);
        mDataList.add(notifybean2);
        NotifyBean notifybean3 = new NotifyBean();
        notifybean3.setImageId(R.mipmap.weixin);
        notifybean3.setTitleId(R.string.title3);
        notifybean3.setTypeId(R.string.type3);
        mDataList.add(notifybean3);
        NotifyBean notifybean4 = new NotifyBean();
        notifybean4.setImageId(R.mipmap.xc_smaillicon);
        notifybean4.setTitleId(R.string.title4);
        notifybean4.setTypeId(R.string.type4);
        mDataList.add(notifybean4);
        NotifyBean notifybean5 = new NotifyBean();
        notifybean5.setImageId(R.mipmap.yybao_smaillicon);
        notifybean5.setTitleId(R.string.title5);
        notifybean5.setTypeId(R.string.type5);
        mDataList.add(notifybean5);
        NotifyBean notifybean6 = new NotifyBean();
        notifybean6.setImageId(R.mipmap.android_bigicon);
        notifybean6.setTitleId(R.string.title6);
        notifybean6.setTypeId(R.string.type6);
        mDataList.add(notifybean6);
        NotifyBean notifybean7 = new NotifyBean();
        notifybean7.setImageId(R.mipmap.android_bigicon);
        notifybean7.setTitleId(R.string.title7);
        notifybean7.setTypeId(R.string.type7);
        mDataList.add(notifybean7);
        NotifyBean notifybean8 = new NotifyBean();
        notifybean8.setImageId(R.mipmap.hl_smallicon);
        notifybean8.setTitleId(R.string.title8);
        notifybean8.setTypeId(R.string.type8);
        mDataList.add(notifybean8);
        NotifyBean notifybean9 = new NotifyBean();
        notifybean9.setImageId(R.mipmap.myicon);
        notifybean9.setTitleId(R.string.title9);
        notifybean9.setTypeId(R.string.title9);
        mDataList.add(notifybean9);

    }

    /**
     * 高仿淘宝
     */
    private void notify_normal_singLine() {
        //设置想要展示的数据内容
        Intent intent = new Intent(mContext, GridViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(mContext,
                requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        int smallIcon = R.mipmap.tb_bigicon;
        String ticker = "您有一条新通知";
        String title = "双十一大优惠！！！";
        String content = "仿真皮肤充气娃娃，女朋友带回家！";

        //实例化工具类，并且调用接口
        NotifyUtils notify1 = new NotifyUtils(mContext, 1);
        NotifyParams params = new NotifyParams();
        params.setPendingIntent(pIntent);
        params.setSmallIcon(smallIcon);
        params.setTicker(ticker);
        params.setTitle(title);
        params.setContent(content);
        params.setLights(false);
        notify1.notifyNormalSingline(params);
        currentNotify = notify1;
    }

    /**
     * 高仿网易新闻
     */
    private void notify_normal_moreLine() {
        //设置想要展示的数据内容
        Intent intent = new Intent(mContext, GridViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(mContext,
                requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        int smallIcon = R.mipmap.netease_bigicon;
        String ticker = "您有一条新通知";
        String title = "朱立伦请辞国民党主席 副主席黄敏惠暂代党主席";
        String content = "据台湾“中央社”报道，国民党主席朱立伦今天(18日)向中常会报告，为败选请辞党主席一职，他感谢各位中常委的指教包容，也宣布未来党务工作由副主席黄敏惠暂代，完成未来所有补选工作。";
        //实例化工具类，并且调用接口
        NotifyUtils notify2 = new NotifyUtils(mContext, 2);
        NotifyParams params = new NotifyParams();
        params.setPendingIntent(pIntent);
        params.setSmallIcon(smallIcon);
        params.setTicker(ticker);
        params.setTitle(title);
        params.setContent(content);
        params.setLights(false);
        notify2.notifyNormailMoreline(params);
        currentNotify = notify2;
    }

    /**
     * 收件箱样式
     */
    private void notify_mailbox() {
        //设置想要展示的数据内容
        Intent intent = new Intent(mContext, GridViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(mContext,
                requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        int largeIcon = R.mipmap.fbb_largeicon;
        int smallIcon = R.mipmap.wx_smallicon;
        String ticker = "您有一条新通知";
        String title = "冰冰";
        ArrayList<String> messageList = new ArrayList<String>();
        messageList.add("文明,今晚有空吗？");
        messageList.add("晚上跟我一起去玩吧?");
        messageList.add("怎么不回复我？？我生气了！！");
        messageList.add("我真生气了！！！！！你听见了吗!");
        messageList.add("文明，别不理我！！！");
        String content = "[" + messageList.size() + "条]" + title + ": " + messageList.get(0);
        //实例化工具类，并且调用接口
        NotifyUtils notify3 = new NotifyUtils(mContext, 3);
        NotifyParams params = new NotifyParams();
        params.setPendingIntent(pIntent);
        params.setSmallIcon(smallIcon);
        params.setTicker(ticker);
        params.setTitle(title);
        params.setContent(content);
        params.setLights(false);
        notify3.notifyMailbox(params, messageList);
        currentNotify = notify3;
    }

    /**
     * 高仿系统截图通知
     */
    private void notify_bigPic() {
        //设置想要展示的数据内容
        Intent intent = new Intent(mContext, GridViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(mContext,
                requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        int smallIcon = R.mipmap.xc_smaillicon;
        int largePic = R.mipmap.screenshot;
        String ticker = "您有一条新通知";
        String title = "已经抓取屏幕截图";
        String content = "触摸可查看您的屏幕截图";
        //实例化工具类，并且调用接口
        NotifyUtils notify4 = new NotifyUtils(mContext, 4);
        NotifyParams params = new NotifyParams();
        params.setPendingIntent(pIntent);
        params.setSmallIcon(smallIcon);
        params.setTicker(ticker);
        params.setTitle(title);
        params.setContent(content);
        params.setLights(false);
        params.setLargeIcon(largePic);
        notify4.notifyBigPic(params);
        currentNotify = notify4;
    }


    /**
     * 高仿应用宝
     */
    private void notify_customview() {
        //设置想要展示的数据内容
        Intent intent = new Intent(mContext, GridViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(mContext,
                requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        String ticker = "您有一条新通知";

        //设置自定义布局中按钮的跳转界面
        Intent btnIntent = new Intent(mContext, GridViewActivity.class);
        btnIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //如果是启动activity，那么就用PendingIntent.getActivity，如果是启动服务，那么是getService
        PendingIntent Pintent = PendingIntent.getActivity(mContext,
                (int) SystemClock.uptimeMillis(), btnIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 自定义布局
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),
                R.layout.yyb_notification);
        remoteViews.setImageViewResource(R.id.image, R.mipmap.yybao_bigicon);
        remoteViews.setTextViewText(R.id.title, "垃圾安装包太多");
        remoteViews.setTextViewText(R.id.text, "3个无用安装包，清理释放的空间");
        remoteViews.setOnClickPendingIntent(R.id.button, Pintent);//定义按钮点击后的动作
        int smallIcon = R.mipmap.yybao_smaillicon;
        //实例化工具类，并且调用接口
        NotifyUtils notify5 = new NotifyUtils(mContext, 5);
        NotifyParams params = new NotifyParams();
        params.setPendingIntent(pIntent);
        params.setSmallIcon(smallIcon);
        params.setTicker(ticker);
        params.setLights(false);
        notify5.notifyCustomview(remoteViews, params);
        currentNotify = notify5;
    }

    /**
     * 高仿Android更新提醒样式
     */
    private void notify_buttom() {
        //设置想要展示的数据内容
        String ticker = "您有一条新通知";
        int smallIcon = R.mipmap.android_bigicon;
        int lefticon = R.mipmap.android_leftbutton;
        String lefttext = "以后再说";
        Intent leftIntent = new Intent();
        leftIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent leftPendIntent = PendingIntent.getActivity(mContext,
                requestCode, leftIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        int righticon = R.mipmap.android_rightbutton;
        String righttext = "安装";
        Intent rightIntent = new Intent(mContext, GridViewActivity.class);
        rightIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent rightPendIntent = PendingIntent.getActivity(mContext,
                requestCode, rightIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //实例化工具类，并且调用接口
        NotifyUtils notify6 = new NotifyUtils(mContext, 6);
        NotifyParams params = new NotifyParams();
        params.setSmallIcon(smallIcon);
        params.setLargeIcon(lefticon);
        params.setLeftText(lefttext);
        params.setLeftPendingIntent(leftPendIntent);

        params.setRightBtnIcon(righticon);
        params.setRightText(righttext);
        params.setRightPendingIntent(rightPendIntent);

        params.setTicker(ticker);
        params.setTitle("系统更新已下载完毕");
        params.setContent("Android 6.0.1");
        params.setLights(false);
        notify6.notifyButton(params);
        currentNotify = notify6;
    }


    /**
     * 高仿Android系统下载样式
     */
    private void notify_progress() {
        //设置想要展示的数据内容
        Intent intent = new Intent(mContext, GridViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent rightPendIntent = PendingIntent.getActivity(mContext,
                requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        int smallIcon = R.mipmap.android_bigicon;
        String ticker = "您有一条新通知";
        //实例化工具类，并且调用接口
        NotifyUtils notify7 = new NotifyUtils(mContext, 7);

        NotifyParams params = new NotifyParams();
        params.setSmallIcon(smallIcon);
        params.setPendingIntent(rightPendIntent);

        params.setTicker(ticker);
        params.setTitle("Android 6.0.1 下载");
        params.setContent("正在下载中");
        params.setVibrate(false);
        params.setLights(false);
        ToastUtils.show("请看代码");
        //notify7.notifyProgress(params);
        currentNotify = notify7;
    }

    /**
     * Android 5。0 新特性：悬浮式通知
     */
    private void notify_headUp() {
        //设置想要展示的数据内容
        int smallIcon = R.mipmap.hl_smallicon;
        int largeIcon = R.mipmap.fbb_largeicon;
        String ticker = "您有一条新通知";
        String title = "范冰冰";
        String content = "文明，今晚在希尔顿酒店2016号房哈";
        Intent intent = new Intent(mContext, GridViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext,
                requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        int lefticon = R.mipmap.hl_message;
        String lefttext = "回复";
        Intent leftIntent = new Intent();
        leftIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent leftPendingIntent = PendingIntent.getActivity(mContext,
                requestCode, leftIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        int righticon = R.mipmap.hl_call;
        String righttext = "拨打";
        Intent rightIntent = new Intent(mContext, GridViewActivity.class);
        rightIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent rightPendingIntent = PendingIntent.getActivity(mContext,
                requestCode, rightIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //实例化工具类，并且调用接口
        NotifyUtils notify8 = new NotifyUtils(mContext, 8);
        NotifyParams params = new NotifyParams();
        params.setSmallIcon(smallIcon);
        params.setLargeIcon(lefticon);
        params.setLeftText(lefttext);
        params.setLeftPendingIntent(leftPendingIntent);

        params.setRightBtnIcon(righticon);
        params.setRightText(righttext);
        params.setRightPendingIntent(rightPendingIntent);

        params.setPendingIntent(pendingIntent);

        params.setTicker(ticker);
        params.setTitle(title);
        params.setContent(content);
        params.setLargeIcon(largeIcon);
        params.setLights(false);
        notify8.notifyHeadUp(params);
        currentNotify = notify8;
    }

}
