package com.android.tanlifei.framestructure.testDemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.common.exception.CustomException;
import com.android.tanlifei.framestructure.common.utils.JsonUtils;
import com.android.tanlifei.framestructure.common.utils.ResUtils;
import com.android.tanlifei.framestructure.common.utils.StartActUtils;
import com.android.tanlifei.framestructure.testDemo.adapter.TestAdapter;
import com.android.tanlifei.framestructure.testDemo.bean.TestBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;


public class TestMainActivity extends Activity implements  AdapterView.OnItemClickListener {


    public static final String TAG = "TestMainActivity";
    private TestAdapter adapter;
    private PullToRefreshListView listView;
    private List list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_main);

        listView = (PullToRefreshListView)findViewById(R.id.lv_pull_to_refresh);
        listView.setMode(PullToRefreshBase.Mode.DISABLED);
        list = new ArrayList();
        list.addAll(JsonUtils.parseToObjectList(JsonUtils.parseToObjectBean(ResUtils.getFileFromRaw(R.raw.test_list_main_json), BaseJson.class).getData(), TestBean.class));
        adapter = new TestAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
       //Logger.i(TAG,PhoneUtils.getScreenWidth(this)+":"+PhoneUtils.getScreenHeight(this));
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        StartActUtils.startForAbsolutePath(this,((TestBean)list.get(position-1)).getActivityPath());
    }

}
