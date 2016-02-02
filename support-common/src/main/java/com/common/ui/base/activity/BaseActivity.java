package com.common.ui.base.activity;


import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.common.R;
import com.common.utils.StartActUtils;
import com.common.view.injection.SelectorImageButton;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by tanlifei on 15/12/17.
 */
public abstract class BaseActivity extends AutoLayoutActivity {

    protected View customNav;
    protected SelectorImageButton actionBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        super.onCreate(savedInstanceState);
        initActionBar();
    }

    protected void initActionBar() {
        customNav = customNav();
        ((TextView) customNav.findViewById(R.id.tv_title)).setText(setActionBarTitle());
        actionBack = (SelectorImageButton) customNav.findViewById(R.id.action_back);
        getSupportActionBar().setCustomView(customNav);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        actionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBack();
            }
        });

    }


    /**
     * 自定义actionBar 布局，子类可以覆盖此方法来自定义布局
     * 注意：返回名字id 要一样，标题id要一样
     *
     * @return
     */
    protected View customNav() {
        return LayoutInflater.from(this).inflate(R.layout.common_custom_actionbar_view, null);
    }

    /**
     * 返回操作 子类可以覆盖此方法做特殊业务
     */
    protected void actionBack() {
        StartActUtils.finish(BaseActivity.this);
    }

    /**
     * 设置标题
     *
     * @return
     */
    protected String setActionBarTitle() {
        return "请在子类设置标题";
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        StartActUtils.finish(this);
    }


    /**
     * 获取actionbar的像素高度，默认使用android官方兼容包做actionbar兼容
     *
     * @return
     */
    protected int getActionBarHeight() {
        int actionBarHeight = getSupportActionBar().getHeight();
        if (actionBarHeight != 0) {
            return actionBarHeight;
        }

        final TypedValue tv = new TypedValue();
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        if (getTheme()
                .resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(
                    tv.data, getResources().getDisplayMetrics());
        }
       /* } else {
            if (getTheme().resolveAttribute(com.actionbarsherlock.R.attr.actionBarSize,
                    tv, true)) {
                //使用actionbarsherlock的actionbar做兼容的情况
                actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
                        getResources().getDisplayMetrics());

            }
        }*/
        return actionBarHeight;
    }

}
