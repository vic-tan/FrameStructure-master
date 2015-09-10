package com.android.tanlifei.framestructure.ui.acitivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.adpater.TagAdapter;
import com.android.tanlifei.framestructure.adpater.base.ListAdapter;
import com.android.tanlifei.framestructure.bean.TagBean;
import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.common.constants.UrlConstants;
import com.android.tanlifei.framestructure.common.constants.enumConstants.RequestStatus;
import com.android.tanlifei.framestructure.common.http.RequestTask;
import com.android.tanlifei.framestructure.common.http.base.BaseRequestParams;
import com.android.tanlifei.framestructure.common.utils.InflaterUtils;
import com.android.tanlifei.framestructure.common.utils.Logger;
import com.android.tanlifei.framestructure.engine.interf.IPullToRefreshCallBack;
import com.android.tanlifei.framestructure.engine.pullToRefresh.BasePullToRefresh;
import com.android.tanlifei.framestructure.engine.pullToRefresh.RefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity implements IPullToRefreshCallBack{

    public static final String TAG = "MainActivity";
    private TagAdapter adapter;
    private RefreshListView listView;
    private List list;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = InflaterUtils.inflater(this,R.layout.activity_main);
        setContentView(view);
        list = new ArrayList();
        adapter = new TagAdapter(this,list);
        listView = new RefreshListView(this,this,view, PullToRefreshBase.Mode.BOTH);
       // RequestTask.post(UrlConstants.TEST_TWO_LIST, null, handler);
    }


    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (RequestStatus.RequestStatus(msg.what)) {
                case SUCCESS:
                    Logger.i(TAG, msg.obj.toString());

                    break;
                case FAILURE:
                    Logger.i(TAG,"Logger000");
                    break;
            }

            return false;
        }
    });


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
    public void customParseJson(String json, PullToRefreshBase.Mode mode) {

    }

    @Override
    public ListAdapter getAdapter() {
        return adapter;
    }

    @Override
    public List<?> getList() {
        return list;
    }
}
