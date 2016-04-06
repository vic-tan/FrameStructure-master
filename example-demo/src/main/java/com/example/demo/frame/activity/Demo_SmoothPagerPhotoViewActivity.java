package com.example.demo.frame.activity;

import android.os.Bundle;

import com.common.bean.paramsBean.PhotoParams;
import com.common.ui.base.activity.BasePhotoViewActivity;

import java.util.ArrayList;
import java.util.List;

public class Demo_SmoothPagerPhotoViewActivity extends BasePhotoViewActivity {

    List<PhotoParams> list;
    PhotoParams bean;
    int index= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list = (ArrayList<PhotoParams>) getIntent().getSerializableExtra("list");
        index = getIntent().getIntExtra("index",0);
        bean = list.get(index);
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
        return list.get(position).getThumbnail();
    }

    @Override
    public int setCurrtentIndex() {
        return index;
    }

    public int setWidth() {
        return bean.getWidth();
    }

    public int setHeight() {
        return bean.getHeight();
    }

    public int setLocationX() {
        return bean.getLocationX();
    }

    public int setLocationY() {
        return bean.getLocationY();
    }

}
