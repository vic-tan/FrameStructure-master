package com.example.demo.frame.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.demo.R;
import com.common.adapter.base.CommonAdapter;
import com.common.adapter.base.ViewHolder;
import com.example.demo.frame.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanlifei on 15/9/4.
 */
public class Demo_SingleItemTypeActivity extends ListActivity {
    private List<Bean> mDatas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatas();

        setListAdapter(new CommonAdapter<Bean>(this, mDatas,
                R.layout.test_item_single_listview) {
            @Override
            public void convert(final ViewHolder holder, final Bean bean,boolean isScrolling) {
                holder.setText(R.id.id_title, bean.getTitle())
                        .setText(R.id.id_desc, bean.getDesc())
                        .setText(R.id.id_time, bean.getTime())
                        .setText(R.id.id_phone, bean.getPhone());

                holder.setOnClickListener(R.id.id_title, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(Demo_SingleItemTypeActivity.this, bean.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }




    private void initDatas() {
        mDatas = new ArrayList<Bean>();

        Bean bean = new Bean("Android新技能Get 1",
                "Android打造万能的ListView和GridView适配器", "2014-12-12", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能Get 2", "Android打造万能的ListView和GridView适配器",
                "2014-12-12", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能Get 3", "Android打造万能的ListView和GridView适配器",
                "2014-12-12", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能Get 4", "Android打造万能的ListView和GridView适配器",
                "2014-12-12", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能Get 5", "Android打造万能的ListView和GridView适配器",
                "2014-12-12", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能Get 6", "Android打造万能的ListView和GridView适配器",
                "2014-12-12", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能Get 7", "Android打造万能的ListView和GridView适配器",
                "2014-12-12", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能Get 8", "Android打造万能的ListView和GridView适配器",
                "2014-12-12", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能Get 9", "Android打造万能的ListView和GridView适配器",
                "2014-12-12", "10086");
        mDatas.add(bean);

    }
}
