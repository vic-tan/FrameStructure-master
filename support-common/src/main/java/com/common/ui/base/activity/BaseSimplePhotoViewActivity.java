package com.common.ui.base.activity;

import android.os.Bundle;

import com.common.bean.paramsBean.PhotoBean;

import java.util.ArrayList;
import java.util.List;

public class BaseSimplePhotoViewActivity extends BasePhotoViewActivity {

    List<PhotoBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list = new ArrayList<PhotoBean>();
        list.add((PhotoBean)getIntent().getParcelableExtra("bean"));
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
