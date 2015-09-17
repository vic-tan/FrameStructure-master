package com.android.tanlifei.framestructure.testDemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.adpater.base.ListAdapter;
import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.common.constants.UrlConstants;
import com.android.tanlifei.framestructure.common.utils.InflaterUtils;
import com.android.tanlifei.framestructure.engine.interf.IPullToRefreshCallBack;
import com.android.tanlifei.framestructure.engine.pullToRefresh.RefreshGridView;
import com.android.tanlifei.framestructure.testDemo.adapter.TagAdapter;
import com.android.tanlifei.framestructure.testDemo.bean.TagBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GridViewActivity extends Activity implements IPullToRefreshCallBack, AdapterView.OnItemClickListener {


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
    public boolean isCustomParseJson() {
        return false;
    }

    @Override
    public void customParseJson(BaseJson baseJson, PullToRefreshBase.Mode mode) {

    }

    @Override
    public ListAdapter getAdapter() {
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
