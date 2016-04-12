package com.example.demo.frame.photo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.common.bean.paramsBean.PhotoParams;
import com.common.ui.base.activity.BaseActivity;
import com.common.ui.base.main.BaseApplication;
import com.common.utils.ImageLoaderUtils;
import com.common.utils.ViewFindUtils;
import com.common.view.imageview.SquareCenterImageView;
import com.example.demo.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimplePhotoActivity extends BaseActivity {

    List<PhotoParams> list;
    GridView mGridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.test_activity_simple_photo_main);
        mGridView = (GridView) ViewFindUtils.find(this, R.id.multi_photo_grid);
        list = new ArrayList<PhotoParams>();
        list.add(new PhotoParams("http://c.hiphotos.baidu.com/image/pic/item/10dfa9ec8a136327408ff2f2958fa0ec09fac794.jpg", "http://c.hiphotos.baidu.com/image/pic/item/10dfa9ec8a136327408ff2f2958fa0ec09fac794.jpg", 0));
        list.add(new PhotoParams("http://e.hiphotos.baidu.com/image/pic/item/96dda144ad345982b658b7e90ff431adcaef84fe.jpg", "http://e.hiphotos.baidu.com/image/pic/item/96dda144ad345982b658b7e90ff431adcaef84fe.jpg", 0));
        list.add(new PhotoParams("http://h.hiphotos.baidu.com/image/pic/item/574e9258d109b3dedb168a8ec8bf6c81810a4cae.jpg", "http://h.hiphotos.baidu.com/image/pic/item/574e9258d109b3dedb168a8ec8bf6c81810a4cae.jpg", 0));
        list.add(new PhotoParams("http://e.hiphotos.baidu.com/image/pic/item/e61190ef76c6a7ef726b5320fffaaf51f3de6663.jpg", "http://e.hiphotos.baidu.com/image/pic/item/e61190ef76c6a7ef726b5320fffaaf51f3de6663.jpg", 0));
        list.add(new PhotoParams("http://img5.duitang.com/uploads/item/201407/30/20140730152939_zPFKy.thumb.700_0.jpeg", "http://a.hiphotos.baidu.com/image/pic/item/bf096b63f6246b60fc49d17ce9f81a4c510fa277.jpg", 0));
        list.add(new PhotoParams("http://a.hiphotos.baidu.com/image/pic/item/8435e5dde71190ef1ef587d5cc1b9d16fdfa60a9.jpg", "http://a.hiphotos.baidu.com/image/pic/item/8435e5dde71190ef1ef587d5cc1b9d16fdfa60a9.jpg", 0));
        list.add(new PhotoParams("http://a.hiphotos.baidu.com/image/pic/item/8435e5dde71190ef1ef587d5cc1b9d16fdfa60a9.jpg", "http://g.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd9c38927a23c01213fb80e9120.jpg", 0));
        list.add(new PhotoParams("http://f.hiphotos.baidu.com/image/pic/item/7acb0a46f21fbe09def7781469600c338744ad01.jpg", "http://f.hiphotos.baidu.com/image/pic/item/7acb0a46f21fbe09def7781469600c338744ad01.jpg", 0));
        list.add(new PhotoParams("http://g.hiphotos.baidu.com/image/pic/item/f31fbe096b63f624cd2991e98344ebf81b4ca3e0.jpg", "http://c.hiphotos.baidu.com/image/pic/item/10dfa9ec8a136327408ff2f2958fa0ec09fac794.jpg", 0));
        list.add(new PhotoParams("http://f.hiphotos.baidu.com/image/pic/item/42a98226cffc1e17af3e61e44e90f603728de966.jpg", "http://e.hiphotos.baidu.com/image/pic/item/86d6277f9e2f070814a5c7d5ed24b899a801f247.jpg", 0));
        list.add(new PhotoParams("http://g.hiphotos.baidu.com/image/pic/item/91ef76c6a7efce1be3e3f0c4ad51f3deb58f65f7.jpg", "http://a.hiphotos.baidu.com/image/pic/item/7a899e510fb30f249a52d40dca95d143ac4b03f7.jpg", 0));
        list.add(new PhotoParams("http://a.hiphotos.baidu.com/image/pic/item/b7003af33a87e9504d4a226d12385343fbf2b456.jpg", "http://c.hiphotos.baidu.com/image/pic/item/63d0f703918fa0ec421ab20c249759ee3c6ddbd6.jpg", 0));
        list.add(new PhotoParams("http://img5.duitang.com/uploads/item/201407/30/20140730152939_zPFKy.thumb.700_0.jpeg", "http://images.99pet.com/InfoImages/wm600_450/ef48d0d8e8f64172a28b9451fc5a941d.jpg", 0));
        list.add(new PhotoParams("http://img2.3lian.com/2014/f5/158/d/86.jpg", "http://img2.3lian.com/2014/f5/158/d/87.jpg", 0));
        list.add(new PhotoParams("http://images.99pet.com/InfoImages/wm600_450/1d770941f8d44c6e85ba4c0eb736ef69.jpg", "http://ww2.sinaimg.cn/mw600/c6c1d258jw1e5r59ttpkcj20gu0gsmyh.jpg", 0));
        list.add(new PhotoParams("http://images.99pet.com/InfoImages/wm600_450/1d770941f8d44c6e85ba4c0eb736ef69.jpg", "http://ww2.sinaimg.cn/mw600/c6c1d258jw1e5r59ttpkcj20gu0gsmyh.jpg", 0));
        list.add(new PhotoParams("http://a.hiphotos.baidu.com/image/pic/item/7e3e6709c93d70cfbbdea204fadcd100baa12b96.jpg", "http://a.hiphotos.baidu.com/image/pic/item/7e3e6709c93d70cfbbdea204fadcd100baa12b96.jpg", 0));
        list.add(new PhotoParams("http://f.hiphotos.baidu.com/image/pic/item/8644ebf81a4c510fa42d1bf66459252dd52aa575.jpg", "http://f.hiphotos.baidu.com/image/pic/item/8644ebf81a4c510fa42d1bf66459252dd52aa575.jpg", 0));
        list.add(new PhotoParams("http://d.hiphotos.baidu.com/image/pic/item/caef76094b36acaffd0002e37ed98d1000e99cc9.jpg", "http://d.hiphotos.baidu.com/image/pic/item/caef76094b36acaffd0002e37ed98d1000e99cc9.jpg", 0));
        super.onCreate(savedInstanceState);
        init();
    }


    void init() {
        mGridView.setAdapter(new ImagesInnerGridViewAdapter(list));
    }

    @Override
    protected String setActionBarTitle() {
        return "SimplePhotoView";
    }

    private class ImagesInnerGridViewAdapter extends BaseAdapter {

        private List<PhotoParams> datas;

        public ImagesInnerGridViewAdapter(List<PhotoParams> datas) {
            this.datas = datas;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final PhotoParams bean = getBean(position);
            final SquareCenterImageView imageView = new SquareCenterImageView(SimplePhotoActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(SimplePhotoActivity.this, BaseSmoothPhotoViewActivity.class);
                    Intent intent = new Intent(SimplePhotoActivity.this, SmoothPagerPhotoViewActivity.class);
                   // Intent intent = new Intent(SimplePhotoActivity.this, SpaceImageDetailActivity.class);
                    int[] location = new int[2];
                    imageView.getLocationOnScreen(location);
                    bean.setWidth(imageView.getWidth());
                    bean.setHeight(imageView.getHeight());
                    bean.setLocationX(location[0]);
                    bean.setLocationY(location[1]);
                    intent.putExtra("bean", (Parcelable) bean);
                    intent.putExtra("list", (Serializable) list);
                    intent.putExtra("index", position);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }

            });
            BaseApplication.imageLoader.displayImage(datas.get(position).getThumbnail(), imageView, ImageLoaderUtils.displayConfigDisplay(R.mipmap.ic_launcher));
            return imageView;
        }

        public PhotoParams getBean(int pos) {
            return (PhotoParams) datas.get(pos);
        }


    }
}
