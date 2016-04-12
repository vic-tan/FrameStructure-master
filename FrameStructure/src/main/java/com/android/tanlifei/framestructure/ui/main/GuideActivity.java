package com.android.tanlifei.framestructure.ui.main;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.tanlifei.framestructure.R;
import com.common.utils.InflaterUtils;
import com.common.utils.SPUtils;
import com.common.utils.StartActUtils;
import com.common.ui.base.activity.BaseActivity;
import com.example.demo.frame.MainActivity;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * 第一次启动引导界面
 * Created by tanlifei on 16/1/19.
 */

@Fullscreen //全屏
@EActivity(R.layout.main_activity_guide)
public class GuideActivity extends BaseActivity {


    @ViewById(R.id.guide_pager)
    ViewPager guidePager;
    @ViewById(R.id.guide_dots_container)
    LinearLayout guideDotsContainer;

    private List<View> guideViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
    }

    @AfterViews
    void init() {
        addGuideViews();
        addIndicatorViews();
        guidePager.setAdapter(new ViewPagerAdapter(guideViews));
        guidePager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < guideDotsContainer.getChildCount(); i++) {
                    guideDotsContainer.getChildAt(i).setSelected(false);
                }
                guideDotsContainer.getChildAt(position).setSelected(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void addGuideViews() {
        guideViews = new ArrayList<View>();
        guideViews.add(InflaterUtils.inflater(this, R.layout.main_activity_guide_item_zero));
        guideViews.add(InflaterUtils.inflater(this, R.layout.main_activity_guide_item_one));
        guideViews.add(InflaterUtils.inflater(this, R.layout.main_activity_guide_item_two));


        View lastView = InflaterUtils.inflater(this, R.layout.main_activity_guide_item_three);
        lastView.findViewById(R.id.imgBtn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActUtils.start(GuideActivity.this, MainActivity.class);
                SPUtils.putBoolean(LauncherActivity.FIRST_LAUNCHER_APP_TAG, false);//设置为已打开过该应用了
                StartActUtils.finish(GuideActivity.this);
            }
        });

        guideViews.add(lastView);
    }

    public void addIndicatorViews() {
        for (int i = 0; i < guideViews.size(); i++) {
            View view = InflaterUtils.inflater(this, R.layout.main_activity_guide_indicator);
            if (i == 0) {
                view.setSelected(true);
            }
            guideDotsContainer.addView(view);
        }
    }

    private class ViewPagerAdapter extends PagerAdapter {
        List<View> views;

        public ViewPagerAdapter(List<View> views) {
            super();
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    }

}
