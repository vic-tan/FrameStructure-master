package com.example.demo.frame.photo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.common.bean.paramsBean.PhotoParams;
import com.common.ui.base.activity.BaseActivity;
import com.common.ui.base.activity.BaseSimplePhotoViewActivity;
import com.common.ui.base.main.BaseApplication;
import com.common.utils.ImageLoaderUtils;
import com.common.utils.StartActUtils;
import com.common.view.imageview.SquareCenterImageView;
import com.example.demo.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class BasePhotoActivity extends BaseActivity implements View.OnClickListener {

    List<PhotoParams> list;
    SquareCenterImageView imgTest;
    PhotoParams bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_base_photo);
        imgTest = (SquareCenterImageView) findViewById(R.id.img_test);
        addList();
        bean = new PhotoParams("http://c.hiphotos.baidu.com/image/pic/item/10dfa9ec8a136327408ff2f2958fa0ec09fac794.jpg", "http://c.hiphotos.baidu.com/image/pic/item/10dfa9ec8a136327408ff2f2958fa0ec09fac794.jpg", 0);
        BaseApplication.imageLoader.displayImage(bean.getThumbnail(), imgTest, ImageLoaderUtils.displayConfigDisplay(R.mipmap.ic_launcher));
        imgTest.setOnClickListener(this);
    }

    public void addList() {
        list = new ArrayList<PhotoParams>();
        list.add(new PhotoParams("http://g.hiphotos.baidu.com/image/pic/item/f31fbe096b63f624cd2991e98344ebf81b4ca3e0.jpg", "http://c.hiphotos.baidu.com/image/pic/item/10dfa9ec8a136327408ff2f2958fa0ec09fac794.jpg", 0));
        list.add(new PhotoParams("http://f.hiphotos.baidu.com/image/pic/item/42a98226cffc1e17af3e61e44e90f603728de966.jpg", "http://e.hiphotos.baidu.com/image/pic/item/86d6277f9e2f070814a5c7d5ed24b899a801f247.jpg", 0));
        list.add(new PhotoParams("http://g.hiphotos.baidu.com/image/pic/item/91ef76c6a7efce1be3e3f0c4ad51f3deb58f65f7.jpg", "http://a.hiphotos.baidu.com/image/pic/item/7a899e510fb30f249a52d40dca95d143ac4b03f7.jpg", 0));
        list.add(new PhotoParams("http://a.hiphotos.baidu.com/image/pic/item/b7003af33a87e9504d4a226d12385343fbf2b456.jpg", "http://c.hiphotos.baidu.com/image/pic/item/63d0f703918fa0ec421ab20c249759ee3c6ddbd6.jpg", 0));
        list.add(new PhotoParams("http://img5.duitang.com/uploads/item/201407/30/20140730152939_zPFKy.thumb.700_0.jpeg", "http://images.99pet.com/InfoImages/wm600_450/ef48d0d8e8f64172a28b9451fc5a941d.jpg", 0));
        list.add(new PhotoParams("http://img2.3lian.com/2014/f5/158/d/86.jpg", "http://img2.3lian.com/2014/f5/158/d/87.jpg", 0));
        list.add(new PhotoParams("http://images.99pet.com/InfoImages/wm600_450/1d770941f8d44c6e85ba4c0eb736ef69.jpg", "http://ww2.sinaimg.cn/mw600/c6c1d258jw1e5r59ttpkcj20gu0gsmyh.jpg", 0));
        list.add(new PhotoParams("http://images.99pet.com/InfoImages/wm600_450/1d770941f8d44c6e85ba4c0eb736ef69.jpg", "http://ww2.sinaimg.cn/mw600/c6c1d258jw1e5r59ttpkcj20gu0gsmyh.jpg", 0));
    }

    public void A(View v) {
        PhotoParams bean = new PhotoParams("http://g.hiphotos.baidu.com/image/pic/item/f31fbe096b63f624cd2991e98344ebf81b4ca3e0.jpg", "http://c.hiphotos.baidu.com/image/pic/item/10dfa9ec8a136327408ff2f2958fa0ec09fac794.jpg", 0);
        StartActUtils.start(this, BaseSimplePhotoViewActivity.class, "bean", bean);
    }


    public void B(View v) {
        StartActUtils.start(this, PagerPhotoActivity.class, "list", (Serializable) list);
    }

    public void C(View v) {
        StartActUtils.start(this, SimplePhotoActivity.class);
    }


    public void D(View v) {
        StartActUtils.start(this, ScrollerPhotoActivity.class);
    }


    @Override
    protected String setActionBarTitle() {
        return "BasePhotoView";
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_test) {
            Intent intent = new Intent(BasePhotoActivity.this, SpaceImageDetailActivity.class);
            int[] location = new int[2];
            imgTest.getLocationOnScreen(location);
            bean.setWidth(imgTest.getWidth());
            bean.setHeight(imgTest.getHeight());
            bean.setLocationX(location[0]);
            bean.setLocationY(location[1]);
            intent.putExtra("bean", (Parcelable) bean);
            startActivity(intent);
            overridePendingTransition(0, 0);

        }
    }
}
