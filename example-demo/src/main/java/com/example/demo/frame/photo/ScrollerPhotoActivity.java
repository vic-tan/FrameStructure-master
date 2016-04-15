package com.example.demo.frame.photo;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.common.adapter.base.CommonAdapter;
import com.common.adapter.base.ViewHolder;
import com.common.ui.base.activity.BaseActivity;
import com.example.demo.R;
import com.fans.loader.FanImageLoader;
import com.fans.loader.core.listener.AbsListPauseOnScrollListener;

import java.util.ArrayList;
import java.util.List;

public class ScrollerPhotoActivity extends BaseActivity {

    List<String> list;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        lv = new ListView(this);
        lv.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        setContentView(lv);
        list = new ArrayList<String>();
        list.add("http://c.hiphotos.baidu.com/image/pic/item/10dfa9ec8a136327408ff2f2958fa0ec09fac794.jpg");
        list.add("http://e.hiphotos.baidu.com/image/pic/item/96dda144ad345982b658b7e90ff431adcaef84fe.jpg");
        list.add("http://h.hiphotos.baidu.com/image/pic/item/574e9258d109b3dedb168a8ec8bf6c81810a4cae.jpg");
        list.add("http://e.hiphotos.baidu.com/image/pic/item/e61190ef76c6a7ef726b5320fffaaf51f3de6663.jpg");
        list.add("http://img5.duitang.com/uploads/item/201407/30/20140730152939_zPFKy.thumb.700_0.jpeg");
        list.add("http://a.hiphotos.baidu.com/image/pic/item/bf096b63f6246b60fc49d17ce9f81a4c510fa277.jpg");
        list.add("http://a.hiphotos.baidu.com/image/pic/item/8435e5dde71190ef1ef587d5cc1b9d16fdfa60a9.jpg");
        list.add("http://g.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd9c38927a23c01213fb80e9120.jpg");
        list.add("http://f.hiphotos.baidu.com/image/pic/item/7acb0a46f21fbe09def7781469600c338744ad01.jpg");
        list.add("http://g.hiphotos.baidu.com/image/pic/item/f31fbe096b63f624cd2991e98344ebf81b4ca3e0.jpg");
        list.add("http://c.hiphotos.baidu.com/image/pic/item/10dfa9ec8a136327408ff2f2958fa0ec09fac794.jpg");
        list.add("http://f.hiphotos.baidu.com/image/pic/item/42a98226cffc1e17af3e61e44e90f603728de966.jpg");
        list.add("http://a.hiphotos.baidu.com/image/pic/item/7a899e510fb30f249a52d40dca95d143ac4b03f7.jpg");
        list.add("http://a.hiphotos.baidu.com/image/pic/item/b7003af33a87e9504d4a226d12385343fbf2b456.jpg");
        list.add("http://c.hiphotos.baidu.com/image/pic/item/63d0f703918fa0ec421ab20c249759ee3c6ddbd6.jpg");
        list.add("http://img5.duitang.com/uploads/item/201407/30/20140730152939_zPFKy.thumb.700_0.jpeg");
        list.add("http://img2.3lian.com/2014/f5/158/d/86.jpg");
        list.add("http://img2.3lian.com/2014/f5/158/d/87.jpg");
        list.add("http://ww2.sinaimg.cn/mw600/c6c1d258jw1e5r59ttpkcj20gu0gsmyh.jpg");
        list.add("http://a.hiphotos.baidu.com/image/pic/item/7e3e6709c93d70cfbbdea204fadcd100baa12b96.jpg");
        list.add("http://f.hiphotos.baidu.com/image/pic/item/8644ebf81a4c510fa42d1bf66459252dd52aa575.jpg");
        list.add("http://d.hiphotos.baidu.com/image/pic/item/caef76094b36acaffd0002e37ed98d1000e99cc9.jpg");
        super.onCreate(savedInstanceState);
        init();
    }


    void init() {
        lv.setOnScrollListener(new AbsListPauseOnScrollListener(true, true));
        lv.setAdapter(new CommonAdapter<String>(this, list, R.layout.test_scroller_list_item) {
            @Override
            public void convert(ViewHolder holder, String str) {
                if (0 == holder.getPosition()) {
                    FanImageLoader.create(str)
                            .setShowSize(holder.getView(R.id.tv_img).getWidth(), holder.getView(R.id.tv_img).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_DEFAULT)
                            .setDefaultRes(R.mipmap.ic_launcher)
                            .setFailRes(R.mipmap.ic_launcher)
                            .setEmptyRes(R.mipmap.ic_launcher)
                            .into(holder.getView(R.id.tv_img));
                } else if (1 == holder.getPosition()) {
                    FanImageLoader.create(str)
                            .setShowSize(holder.getView(R.id.tv_img).getWidth(), holder.getView(R.id.tv_img).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_FADE_IN)
                            .setFadeInTime(1000)
                            .into(holder.getView(R.id.tv_img));

                } else if (2 == holder.getPosition()) {
                    FanImageLoader.create(str)
                            .setShowSize(holder.getView(R.id.tv_img).getWidth(), holder.getView(R.id.tv_img).getWidth())
                            .setRoundRadius(36)
                            .setDisplayType(FanImageLoader.DISPLAY_ROUND)
                            .into(holder.getView(R.id.tv_img));

                } else if (3 == holder.getPosition()) {
                    FanImageLoader.create(str)
                            .setShowSize(holder.getView(R.id.tv_img).getWidth(), holder.getView(R.id.tv_img).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_ROUND_FADE_IN)
                            .setRoundRadius(36)
                            .setFadeInTime(1000)
                            .into(holder.getView(R.id.tv_img));

                } else if (4 == holder.getPosition()) {
                    FanImageLoader.create(str)
                            .setShowSize(holder.getView(R.id.tv_img).getWidth(), holder.getView(R.id.tv_img).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_ROUND_VIGNETTE)
                            .setRoundRadius(36)
                            .into(holder.getView(R.id.tv_img));

                } else if (5 == holder.getPosition()) {
                    FanImageLoader.create(str)
                            .setShowSize(holder.getView(R.id.tv_img).getWidth(), holder.getView(R.id.tv_img).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_ROUND_VIGNETTE_FADE_IN)
                            .setRoundRadius(36)
                            .setFadeInTime(1000)
                            .into(holder.getView(R.id.tv_img));

                } else if (6 == holder.getPosition()) {
                    FanImageLoader.create(str)
                            .setShowSize(holder.getView(R.id.tv_img).getWidth(), holder.getView(R.id.tv_img).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_CIRCLE)
                            .into(holder.getView(R.id.tv_img));

                } else if (7 == holder.getPosition()) {
                    FanImageLoader.create(str)
                            .setShowSize(holder.getView(R.id.tv_img).getWidth(), holder.getView(R.id.tv_img).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_CIRCLE_FADE_IN)
                            .setFadeInTime(1000)
                            .into(holder.getView(R.id.tv_img));

                } else if (8 == holder.getPosition()) {
                    FanImageLoader.create(str)
                            .setShowSize(holder.getView(R.id.tv_img).getWidth(), holder.getView(R.id.tv_img).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_CIRCLE_RING)
                            .setDefaultRes(R.mipmap.ic_launcher)
                            .setFailRes(R.mipmap.ic_launcher)
                            .setEmptyRes(R.mipmap.ic_launcher)
                            .setStrokeWidth(5.f)
                            .setRingColor(0xff00ff00)
                            .setRingPadding(5.f)
                            .into(holder.getView(R.id.tv_img));

                } else if (9 == holder.getPosition()) {
                    FanImageLoader.create(str)
                            .setShowSize(holder.getView(R.id.tv_img).getWidth(), holder.getView(R.id.tv_img).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_BLUR)
                            .setDefaultRes(R.mipmap.ic_launcher)
                            .setFailRes(R.mipmap.ic_launcher)
                            .setEmptyRes(R.mipmap.ic_launcher)
                            .setBlurDepth(10)
                            .into(holder.getView(R.id.tv_img));

                } else if (10 == holder.getPosition()) {
                    FanImageLoader.create(str)
                            .setShowSize(holder.getView(R.id.tv_img).getWidth(), holder.getView(R.id.tv_img).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_BLUR_FADE_IN)
                            .setDefaultRes(R.mipmap.ic_launcher)
                            .setFailRes(R.mipmap.ic_launcher)
                            .setEmptyRes(R.mipmap.ic_launcher)
                            .setBlurDepth(10)
                            .setFadeInTime(1000)
                            .into(holder.getView(R.id.tv_img));

                } else if (11 == holder.getPosition()) {
                    FanImageLoader.create(str)
                            .setShowSize(holder.getView(R.id.tv_img).getWidth(), holder.getView(R.id.tv_img).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_ROUND_BLUR)
                            .setDefaultRes(R.mipmap.ic_launcher)
                            .setFailRes(R.mipmap.ic_launcher)
                            .setEmptyRes(R.mipmap.ic_launcher)
                            .setRoundRadius(6)
                            .setBlurDepth(10)
                            .into(holder.getView(R.id.tv_img));

                } else if (12 == holder.getPosition()) {
                    FanImageLoader.create(str)
                            .setShowSize(holder.getView(R.id.tv_img).getWidth(), holder.getView(R.id.tv_img).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_ROUND_BLUR_VIGNETTE)
                            .setDefaultRes(R.mipmap.ic_launcher)
                            .setFailRes(R.mipmap.ic_launcher)
                            .setEmptyRes(R.mipmap.ic_launcher)
                            .setRoundRadius(36)
                            .setBlurDepth(10)
                            .into(holder.getView(R.id.tv_img));

                } else if (13 == holder.getPosition()) {
                    FanImageLoader.create(str)
                            .setShowSize(holder.getView(R.id.tv_img).getWidth(), holder.getView(R.id.tv_img).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_CIRCLE_BLUR)
                            .setDefaultRes(R.mipmap.ic_launcher)
                            .setFailRes(R.mipmap.ic_launcher)
                            .setEmptyRes(R.mipmap.ic_launcher)
                            .setBlurDepth(10)
                            .into(holder.getView(R.id.tv_img));

                }else{
                    FanImageLoader.create(str)
                            .setShowSize(holder.getView(R.id.tv_img).getWidth(), holder.getView(R.id.tv_img).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_CIRCLE_BLUR)
                            .setDefaultRes(R.mipmap.ic_launcher)
                            .setFailRes(R.mipmap.ic_launcher)
                            .setEmptyRes(R.mipmap.ic_launcher)
                            .setBlurDepth(10)
                            .into(holder.getView(R.id.tv_img));
                }
            }
        });
    }

    @Override
    protected String setActionBarTitle() {
        return "SimplePhotoView";
    }


}
