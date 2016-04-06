package com.common.ui.base.activity;

import android.os.Bundle;

import com.common.bean.paramsBean.PhotoParams;

import java.util.ArrayList;
import java.util.List;

public class BaseSimplePhotoViewActivity extends BasePhotoViewActivity {

    List<PhotoParams> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list = new ArrayList<PhotoParams>();
        list.add((PhotoParams)getIntent().getParcelableExtra("bean"));
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
