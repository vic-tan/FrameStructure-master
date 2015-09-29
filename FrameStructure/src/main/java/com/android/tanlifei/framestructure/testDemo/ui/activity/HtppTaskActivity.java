package com.android.tanlifei.framestructure.testDemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.common.constants.JsonConstants;
import com.android.tanlifei.framestructure.common.constants.UrlConstants;
import com.android.tanlifei.framestructure.common.constants.enumConstants.RequestStatusLevel;
import com.android.tanlifei.framestructure.common.constants.enumConstants.TaskLevel;
import com.android.tanlifei.framestructure.common.http.HttpTask;
import com.android.tanlifei.framestructure.common.http.DialogHttpTask;
import com.android.tanlifei.framestructure.common.http.LayoutHttpTask;
import com.android.tanlifei.framestructure.common.http.base.BaseHttpParams;
import com.android.tanlifei.framestructure.common.http.base.RequestBean;
import com.android.tanlifei.framestructure.common.utils.InflaterUtils;
import com.android.tanlifei.framestructure.common.utils.Logger;
import com.android.tanlifei.framestructure.common.utils.StartActUtils;
import com.android.tanlifei.framestructure.common.utils.ToastUtils;
import com.android.tanlifei.framestructure.engine.interf.IHttpTaskCallBack;
import com.android.tanlifei.framestructure.engine.interf.ILayoutReStartCallBack;

import java.util.HashMap;
import java.util.Map;


public class HtppTaskActivity extends Activity implements View.OnClickListener, IHttpTaskCallBack, ILayoutReStartCallBack {

    public static final String TAG = "HtppTaskActivity";

    private View view;
    private DialogHttpTask dialogHttpTask;
    private LayoutHttpTask layoutHttpTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = InflaterUtils.inflater(this, R.layout.test_activity_http_task);
        setContentView(view);
        dialogHttpTask = new DialogHttpTask(this);
        layoutHttpTask = new LayoutHttpTask(this, this, view);
        setOnClickListener();
    }


    @Override
    public void httpTaskCallBack(RequestBean requestBean) {
        switch (requestBean.getTaskLevel()) {
            case TASK_ONE://接口一请求回调
               switch (requestBean.getRequestStatusLevel()){//接口一回调方法状态
                   case SUCCESS:
                       break;
               }
            case TASK_TWO://接口二请求回调
                switch (requestBean.getRequestStatusLevel()){//接口二回调方法状态
                    case SUCCESS:
                        break;
                }
        }
    }

    private void setOnClickListener() {
        findViewById(R.id.btn_1_1).setOnClickListener(this);
        findViewById(R.id.btn_1_2).setOnClickListener(this);
        findViewById(R.id.btn_1_3).setOnClickListener(this);
        findViewById(R.id.btn_2_1).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1_1:
                //步骤
                //1、调用 HttpTask相应的方法;
                //2、Handler 处理请求成功后的业务

                HttpTask.post(new RequestBean(this, BaseHttpParams.baseParams(UrlConstants.TEST_LIST)), new IHttpTaskCallBack() {
                    @Override
                    public void httpTaskCallBack(RequestBean requestBean) {
                        switch (requestBean.getRequestStatusLevel()) {
                            case START:
                                ToastUtils.show(HtppTaskActivity.this, "START");
                                break;
                            case SUCCESS:
                                if (null != requestBean.getBaseJson()) {
                                    ToastUtils.show(HtppTaskActivity.this, requestBean.getBaseJson().getData());
                                }
                                break;
                            case FAILURE:
                                ToastUtils.show(HtppTaskActivity.this, "FAILURE");
                                break;
                            case FINISH:
                                ToastUtils.show(HtppTaskActivity.this, "FINISH");
                                break;
                            case TIMEOUT_ERROR:
                                ToastUtils.show(HtppTaskActivity.this, "TIMEOUT_ERROR");
                                break;
                            case SERVICE_ERROR:
                                ToastUtils.show(HtppTaskActivity.this, "SERVICE_ERROR");
                                break;
                            case NETWORK_ERROR:
                                ToastUtils.show(HtppTaskActivity.this, "NETWORK_ERROR");
                                break;

                        }
                    }
                });
                break;
            case R.id.btn_1_2://加载框请求
                //步骤
                //1、创建LoadingHttpTask();
                //2、实现 ILoadingResultTaskCallBack,resultTask
                //3、调用 dialogHttpTask.post()请求方法
                //4、resultTask 处理请求成功后的业务

                Map map = new HashMap();
                map.put("test", "加载框请求 ");
                map.put(JsonConstants.JSON_TASK_LEVEL, TaskLevel.TASK_ONE);
                dialogHttpTask.post(new RequestBean(this, BaseHttpParams.baseParams(UrlConstants.TEST_LIST), map), this);
                break;

            case R.id.btn_1_3://提示框请求

                //步骤
                //1、创建LoadingHttpTask();
                //2、实现 ILoadingResultTaskCallBack,ILayoutReStartCallBack,类
                //3、调用 layoutHttpTask.post()请求方法
                //4、resultTask 处理请求成功后的业务
                //5、onRefresh 处理请求失败重新请求业务

                testPrompt();
                break;

            case R.id.btn_2_1:
                StartActUtils.start(this, ListViewActivity.class);
                break;
        }
    }

    private void testPrompt() {
        Map map2 = new HashMap();
        map2.put("test", "提示框请求 ");
        map2.put(JsonConstants.JSON_TASK_LEVEL, TaskLevel.TASK_TWO);
        layoutHttpTask.post(new RequestBean(this, BaseHttpParams.baseParams(UrlConstants.TEST_LIST), map2), this);
    }

    @Override
    public void onRefresh(RequestStatusLevel level) {
        ToastUtils.show(this, "重试中。。。");
        testPrompt();
    }


}
