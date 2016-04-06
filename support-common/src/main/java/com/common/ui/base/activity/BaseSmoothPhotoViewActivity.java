package com.common.ui.base.activity;

import android.os.Bundle;

import com.common.bean.paramsBean.PhotoParams;

import java.util.ArrayList;
import java.util.List;

public class BaseSmoothPhotoViewActivity extends BasePhotoViewActivity {

    List<PhotoParams> list;
    PhotoParams bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list = new ArrayList<PhotoParams>();
        bean = (PhotoParams) getIntent().getParcelableExtra("bean");
        list.add(bean);
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
        return 0;
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
