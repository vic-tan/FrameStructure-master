package com.android.tanlifei.framestructure.ui.main;

import android.os.Bundle;

import com.common.ui.base.activity.BaseActivity;

/**
 * 加载数据界面
 * Created by tanlifei on 16/1/19.
 */
public class LoadingActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
    }
}
