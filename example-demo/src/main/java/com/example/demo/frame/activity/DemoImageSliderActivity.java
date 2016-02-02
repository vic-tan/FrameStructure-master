package com.example.demo.frame.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.common.bean.paramsBean.PhotoBean;
import com.common.ui.base.activity.BaseActivity;
import com.common.ui.base.activity.BaseSimplePhotoViewActivity;
import com.common.utils.StartActUtils;
import com.common.utils.ToastUtils;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.demo.R;
import com.example.demo.frame.adapter.TransformerAdapter;

import java.util.HashMap;


public class DemoImageSliderActivity extends BaseActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private SliderLayout mDemoSlider;
    private HashMap<String, String> url_maps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_slider_main);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

        url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://www.fansimg.com/album/uploads2011/11/userid257294time20111115114814.jpg");
        url_maps.put("Big Bang Theory", "http://www.chinadaily.com.cn/hqcj/xfly/201110/a27a51f6dd68062ae8ef615d85bed051.jpg");
        url_maps.put("House of Cards", "http://www.fansimg.com/album/uploads2011/11/userid257294time20111115115201.jpg");
        url_maps.put("Game of Thrones3", "http://img.taopic.com/uploads/allimg/140321/240423-14032110264931.jpg");
        url_maps.put("Game of Thrones2", "http://pic38.nipic.com/20140220/4589934_232711648000_2.jpg");
        url_maps.put("Game of Thrones4", "http://www.photo0086.com/member/3897/pic/2010091521233923397.JPG");
        url_maps.put("Game of Thronese", "http://image.haha.mx/2013/02/14/big/746226_001571959de859f626185f18adc559c3_1360847377.jpg");

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        ListView l = (ListView) findViewById(R.id.transformers);
        l.setAdapter(new TransformerAdapter(this));
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDemoSlider.setPresetTransformer(((TextView) view).getText().toString());
                ToastUtils.show(DemoImageSliderActivity.this, ((TextView) view).getText().toString());
            }
        });


    }

    @Override
    protected String setActionBarTitle() {
        return "轮播广告位";
    }

    @Override
    protected void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDemoSlider.startAutoCycle();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        PhotoBean bean = new PhotoBean("", url_maps.get(slider.getBundle().get("extra")+""), 0);
        StartActUtils.start(DemoImageSliderActivity.this, BaseSimplePhotoViewActivity.class, "bean", bean);
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        //Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
