package com.example.demo.frame.table;

import android.os.Bundle;
import android.view.View;

import com.common.ui.base.activity.BaseActivity;
import com.common.utils.StartActUtils;
import com.example.demo.R;

/**
 * Created by tanlifei on 16/4/12.
 */
public class TableMianAtivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_activity_main);

    }

    /**单个下载**/
    public void A(View v){
        StartActUtils.start(this,ListViewActivity.class);
    }

    /**列表并点击进入详情下载**/
    public void B(View v){
        StartActUtils.start(this,GridViewActivity.class);
    }



}
