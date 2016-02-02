package com.example.demo.frame.activity;

import android.os.Bundle;
import android.view.View;

import com.example.demo.R;
import com.common.utils.StartActUtils;
import com.common.ui.base.activity.BaseActivity;

public class DemoBaseAdpterActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_base_adapter);
    }

    public void A(View v){
        StartActUtils.start(this,DemoSingleItemTypeActivity.class);
    }

    public void B(View v){
        StartActUtils.start(this,DemoMutliItemTypeActivity.class);
    }


    @Override
    protected String setActionBarTitle() {
        return "注解";
    }
}
