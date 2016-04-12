package com.example.demo.frame.table;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.demo.R;
import com.common.adapter.base.CommonAdapter;
import com.constants.fixed.UrlConstants;
import com.common.utils.InflaterUtils;
import com.common.engine.pullToRefresh.RefreshGridView;
import com.common.ui.interfaceabs.PullToRefreshCallBackAbs;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GridViewActivity extends PullToRefreshCallBackAbs implements AdapterView.OnItemClickListener {


    public static final String TAG = "TestMainActivity";
    private TagAdapter adapter;
    private RefreshGridView refreshGridView;
    private List list;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = InflaterUtils.inflater(this, R.layout.test_activity_grive_view);
        setContentView(view);
        list = new ArrayList();
        adapter = new TagAdapter(this, list);
        refreshGridView = new RefreshGridView(this, this, view, PullToRefreshBase.Mode.BOTH);
        refreshGridView.getmPullRefreshGridView().setOnItemClickListener(this);
    }

    @Override
    protected String setActionBarTitle() {
        return "网格";
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

    }
}
