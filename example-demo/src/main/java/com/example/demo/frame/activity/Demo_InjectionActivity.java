package com.example.demo.frame.activity;

import android.os.Bundle;
import android.widget.Checkable;

import com.example.demo.R;
import com.common.ui.base.activity.BaseActivity;


public class Demo_InjectionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_injection);

        Checkable view = (Checkable) findViewById(R.id.view);
        view.setChecked(true);
    }

    @Override
    protected String setActionBarTitle() {
        return "selector 选择器";
    }
}
