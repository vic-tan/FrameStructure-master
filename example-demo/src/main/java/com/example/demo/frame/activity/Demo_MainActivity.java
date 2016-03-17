package com.example.demo.frame.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.AdapterView;

import com.common.adapter.base.CommonAdapter;
import com.common.adapter.base.ViewHolder;
import com.common.dialog.listener.OnBtnClickL;
import com.common.dialog.widget.MaterialDialog;
import com.common.download.autoupdate.AutoUpdateService;
import com.common.okhttp.json.BaseJson;
import com.common.ui.base.activity.BaseActivity;
import com.common.utils.JsonUtils;
import com.common.utils.Logger;
import com.common.utils.ResUtils;
import com.common.utils.ScreenUtils;
import com.common.utils.StartActUtils;
import com.common.utils.ViewFindUtils;
import com.common.view.textview.expandable.ExpandableTextView;
import com.example.demo.R;
import com.example.demo.frame.bean.TestBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;


public class Demo_MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    public static final String TAG = "Demo_MainActivity";
    PullToRefreshListView listView;
    private List list;
    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AutoUpdateService.MyBinder binder = (AutoUpdateService.MyBinder) service;
            AutoUpdateService bindService = binder.getService();
            bindService.checkAppUpdate();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_main);
        init();
        bindService(new Intent(this, AutoUpdateService.class), conn, BIND_AUTO_CREATE);
    }

    void init() {
        listView = ViewFindUtils.find(this, R.id.lv_pull_to_refresh);
        listView.setMode(PullToRefreshBase.Mode.DISABLED);
        list = new ArrayList();
        list.addAll(JsonUtils.parseToObjectList(JsonUtils.parseToObjectBean(ResUtils.getFileFromRaw(R.raw.test_list_main_json), BaseJson.class).getData().toString(), TestBean.class));
        listView.setAdapter(new CommonAdapter<TestBean>(listView.getRefreshableView(), this, list, R.layout.test_list_item) {
            @Override
            public void convert(ViewHolder holder, TestBean bean, boolean isScrolling) {
                ((ExpandableTextView) holder.getView(R.id.expand_text_view)).setText(bean.getDesc());
                holder.setText(R.id.tv_name, bean.getName());
            }
        });
        listView.setOnItemClickListener(this);
        Logger.i(TAG, ScreenUtils.getScreenWidth(this) + ":" + ScreenUtils.getScreenHeight(this));
        Logger.i(TAG, ResUtils.getFileFromRaw(R.raw.test_list_main_json));
    }


    @Override
    protected String setActionBarTitle() {
        return "主界面";
    }

    /**
     * 返回操作 子类可以覆盖此方法做特殊业务
     */
    protected void actionBack() {
        exitApp();
    }

    @Override
    public void onBackPressed() {
        exitApp();
    }

    /**
     * 退出App
     */
    private void exitApp() {
        final MaterialDialog dialog = new MaterialDialog(mContext);
        dialog.content(
                "是否确定退出程序?")//
                .btnText("取消", "确定")//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                        StartActUtils.finish(mContext);
                    }
                }
        );
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        StartActUtils.startForAbsolutePath(Demo_MainActivity.this, ((TestBean) list.get(position - 1)).getActivityPath());
    }
}
