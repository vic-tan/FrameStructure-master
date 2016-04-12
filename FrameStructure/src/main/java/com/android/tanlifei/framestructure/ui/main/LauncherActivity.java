package com.android.tanlifei.framestructure.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.android.tanlifei.framestructure.R;
import com.common.ui.base.activity.BaseActivity;
import com.common.utils.SPUtils;
import com.common.utils.StartActUtils;
import com.example.demo.frame.MainActivity;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

/**
 * 启动界面
 * Created by tanlifei on 16/1/19.
 */
@Fullscreen //全屏
@EActivity(R.layout.main_activity_launcher)
public class LauncherActivity extends BaseActivity {

    public static final String FIRST_LAUNCHER_APP_TAG = "first_Launcher_app_tag";//保存第一次启动app的key
    private static final int HANDLE_WHAT_ZERO = 0;
    private static final int HANDLE_WHAT_ONE = 1;
    private static final int HANDLE_WHAT_TWO = 2;
    private static final int DELAYED = 1000;
    private static final boolean IS_HAS_LOADING_DATA = false;//是否有加载数据页的过程

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLE_WHAT_ZERO://第一次打开应用,进入引导页
                    StartActUtils.start(LauncherActivity.this, GuideActivity_.class);
                    StartActUtils.finish(LauncherActivity.this);
                    break;
                case HANDLE_WHAT_ONE://进入正在加载数据页
                    StartActUtils.start(LauncherActivity.this, LoadingActivity.class);
                    StartActUtils.finish(LauncherActivity.this);
                    break;
                case HANDLE_WHAT_TWO://没有正在加载数据页时,直接进入首页
                    StartActUtils.start(LauncherActivity.this, MainActivity.class);
                    StartActUtils.finish(LauncherActivity.this);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
    }


    @AfterViews
    void init() {
        if (getFirstLauncherAppTag()) {//第一次打开应用
            handler.sendMessageDelayed(handler.obtainMessage(HANDLE_WHAT_ZERO), DELAYED);
        } else {
            if (IS_HAS_LOADING_DATA)//有加载数据页时过程
                handler.sendMessageDelayed(handler.obtainMessage(HANDLE_WHAT_ONE), DELAYED);
            else
                handler.sendMessageDelayed(handler.obtainMessage(HANDLE_WHAT_TWO), DELAYED);
        }
    }


    /**
     * 是否第一次打开应用 *
     */
    private boolean getFirstLauncherAppTag() {
        return SPUtils.getBoolean(FIRST_LAUNCHER_APP_TAG, true);
    }
}
