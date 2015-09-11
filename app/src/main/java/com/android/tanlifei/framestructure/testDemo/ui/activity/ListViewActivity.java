package com.android.tanlifei.framestructure.testDemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.testDemo.adapter.TagAdapter;
import com.android.tanlifei.framestructure.adpater.base.ListAdapter;
import com.android.tanlifei.framestructure.testDemo.bean.TagBean;
import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.common.constants.UrlConstants;
import com.android.tanlifei.framestructure.common.utils.InflaterUtils;
import com.android.tanlifei.framestructure.common.view.prompt.LoadingDialog;
import com.android.tanlifei.framestructure.engine.interf.IPullToRefreshCallBack;
import com.android.tanlifei.framestructure.engine.pullToRefresh.RefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ListViewActivity extends Activity implements IPullToRefreshCallBack, AdapterView.OnItemClickListener {


    public static final String TAG = "TestMainActivity";
    private TagAdapter adapter;
    private RefreshListView refreshListView;
    private List list;
    private View view;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = InflaterUtils.inflater(this, R.layout.test_activity_listview);
        setContentView(view);
        list = new ArrayList();
        adapter = new TagAdapter(this, list);
        refreshListView = new RefreshListView(this, this, view, PullToRefreshBase.Mode.BOTH);
        loadingDialog = new LoadingDialog(this);
        refreshListView.getmPullRefreshListView().setOnItemClickListener(this);
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
