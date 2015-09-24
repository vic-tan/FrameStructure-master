package com.android.tanlifei.framestructure.testDemo.ui.activity;

import android.app.Activity;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.common.utils.JsonUtils;
import com.android.tanlifei.framestructure.common.utils.ResUtils;
import com.android.tanlifei.framestructure.common.utils.StartActUtils;
import com.android.tanlifei.framestructure.testDemo.adapter.TestAdapter;
import com.android.tanlifei.framestructure.testDemo.bean.TestBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@EActivity(R.layout.test_activity_main)
public class   TestMainActivity extends Activity{


    public static final String TAG = "TestMainActivity";
    private TestAdapter adapter;

    @ViewById(R.id.lv_pull_to_refresh)
    PullToRefreshListView listView;
    private List list;


    @AfterViews
    void init(){
        listView.setMode(PullToRefreshBase.Mode.DISABLED);
        list = new ArrayList();
        list.addAll(JsonUtils.parseToObjectList(JsonUtils.parseToObjectBean(ResUtils.getFileFromRaw(R.raw.test_list_main_json), BaseJson.class).getData(), TestBean.class));
        adapter = new TestAdapter(this, list);
        listView.setAdapter(adapter);
        //Logger.i(TAG,PhoneUtils.getScreenWidth(this)+":"+PhoneUtils.getScreenHeight(this));
    }


    @ItemClick(R.id.lv_pull_to_refresh)
    void listItemClicked(TestBean itemBean) {
        StartActUtils.startForAbsolutePath(this, itemBean.getActivityPath());
    }

}
