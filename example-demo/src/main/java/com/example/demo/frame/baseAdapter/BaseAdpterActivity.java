package com.example.demo.frame.baseAdapter;

import android.os.Bundle;
import android.view.View;

import com.example.demo.R;
import com.common.utils.StartActUtils;
import com.common.ui.base.activity.BaseActivity;

public class BaseAdpterActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_base_adapter);
    }

    public void A(View v){
        StartActUtils.start(this,SingleItemTypeActivity.class);
    }

    public void B(View v){
        StartActUtils.start(this,MutliItemTypeActivity.class);
    }


    @Override
    protected String setActionBarTitle() {
        return "公用adapter";
    }
}
