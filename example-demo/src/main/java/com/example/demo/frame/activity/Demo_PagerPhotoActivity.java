package com.example.demo.frame.activity;

import android.os.Bundle;

import com.common.bean.paramsBean.PhotoBean;
import com.common.ui.base.activity.BasePhotoViewActivity;

import java.util.List;

public class Demo_PagerPhotoActivity extends BasePhotoViewActivity {

    List<PhotoBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list = (List)getIntent().getSerializableExtra("list");
        super.onCreate(savedInstanceState);
    }

    @Override
    public List setPhotoList() {
        return list;
    }

    @Override
    public String setThumbnail(int position) {
        return list.get(position).getThumbnail();
    }

    @Override
    public String setArtwork(int position) {
        return list.get(position).getArtwork();
    }

    @Override
    public int setCurrtentIndex() {
        return 0;
    }

}
