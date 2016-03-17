package com.example.demo.frame.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.demo.R;
import com.common.adapter.base.CommonAdapter;
import com.constants.fixed.UrlConstants;
import com.common.utils.InflaterUtils;
import com.common.utils.ToastUtils;
import com.common.engine.pullToRefresh.RefreshListView;
import com.example.demo.frame.adapter.TagAdapter;
import com.example.demo.frame.bean.TagBean;
import com.common.ui.interfaceabs.PullToRefreshCallBackAbs;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Demo_ListViewActivity extends PullToRefreshCallBackAbs implements AdapterView.OnItemClickListener {


    public static final String TAG = "TestMainActivity";
    private CommonAdapter adapter;
    private RefreshListView refreshListView;
    private List list;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = InflaterUtils.inflater(this, R.layout.test_activity_listview);
        setContentView(view);
        list = new ArrayList();
        adapter = new TagAdapter(this, list);
        refreshListView = new RefreshListView(this, this, view, PullToRefreshBase.Mode.BOTH);
        refreshListView.getmPullRefreshListView().setOnItemClickListener(this);
    }

    @Override
    protected String setActionBarTitle() {
        return "列表";
    }

    @Override
    public String taskUrl() {
        return UrlConstants.TEST_LIST;
    }

    @Override
    public Map<String, Object> taskParams(Map<String, Object> map) {
        return map;
    }

    @Override
    public Class<?> parseClassName() {
        return TagBean.class;
    }


    @Override
    public CommonAdapter getAdapter() {
        return adapter;
    }

    @Override
    public List<?> getList() {
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtils.show( "aa");
    }
}
