package com.example.demo.frame.activity;

import android.os.Bundle;
import android.view.View;

import com.common.engine.interf.IHttpTaskCallBack;
import com.common.engine.interf.IRefreshRequestCallBack;
import com.common.http.base.BaseHttpParams;
import com.common.http.base.RequestBean;
import com.common.http.task.HttpTask;
import com.common.http.task.LayoutHttpTask;
import com.common.http.task.LoadingHttpTask;
import com.common.ui.base.activity.BaseActivity;
import com.common.utils.InflaterUtils;
import com.common.utils.StartActUtils;
import com.common.utils.ToastUtils;
import com.constants.fixed.JsonConstants;
import com.constants.fixed.UrlConstants;
import com.constants.level.TaskLevel;
import com.constants.level.TaskRequestLevel;
import com.example.demo.R;

import java.util.HashMap;
import java.util.Map;


public class Demo_HttpTaskActivity extends BaseActivity implements View.OnClickListener, IHttpTaskCallBack, IRefreshRequestCallBack {


    /**
     * | Header 1 | Header 2 |
     * | val1     | val2     |
     */
    public static final String TAG = "HttpTaskActivity";

    private View view;
    private LoadingHttpTask loadingHttpTask;
    private LayoutHttpTask layoutHttpTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = InflaterUtils.inflater(this, R.layout.test_activity_http_task);
        setContentView(view);
        loadingHttpTask = new LoadingHttpTask(this);
        layoutHttpTask = new LayoutHttpTask(this, this, view);
        setOnClickListener();
    }

    @Override
    protected String setActionBarTitle() {
        return "公用请求";
    }


    @Override
    public void taskCallBack(RequestBean requestBean) {
        switch (requestBean.getTaskLevel()) {
            case TASK_ONE://接口一请求回调
                switch (requestBean.getRequestLevel()) {//接口一回调方法状态
                    case SUCCESS:
                        break;
                }
            case TASK_TWO://接口二请求回调
                switch (requestBean.getRequestLevel()) {//接口二回调方法状态
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
        int i = v.getId();
        if (i == R.id.btn_1_1) {//步骤
            //1、调用 HttpTask相应的方法;
            //2、Handler 处理请求成功后的业务

            HttpTask.post(new RequestBean(this, BaseHttpParams.baseParams(UrlConstants.TEST_LIST)), new IHttpTaskCallBack() {
                @Override
                public void taskCallBack(RequestBean requestBean) {
                    switch (requestBean.getRequestLevel()) {
                        case START:
                            ToastUtils.show(Demo_HttpTaskActivity.this, "PROGRESS");
                            break;
                        case SUCCESS:
                            if (null != requestBean.getBaseJson()) {
                                ToastUtils.show(Demo_HttpTaskActivity.this, requestBean.getBaseJson().getData());
                            }
                            break;
                        case FAILURE:
                            ToastUtils.show(Demo_HttpTaskActivity.this, "INTERRUPT");
                            break;
                        case FINISH:
                            ToastUtils.show(Demo_HttpTaskActivity.this, "FINISH");
                            break;
                        case TIMEOUT_ERROR:
                            ToastUtils.show(Demo_HttpTaskActivity.this, "TIMEOUT_ERROR");
                            break;
                        case SERVICE_ERROR:
                            ToastUtils.show(Demo_HttpTaskActivity.this, "SERVICE_ERROR");
                            break;
                        case NETWORK_ERROR:
                            ToastUtils.show(Demo_HttpTaskActivity.this, "BaseApplication.getContext()");
                            break;

                    }
                }
            });

        } else if (i == R.id.btn_1_2) {//步骤
            //1、创建LoadingHttpTask();
            //2、实现 ILoadingResultTaskCallBack,resultTask
            //3、调用 loadingHttpTask.post()请求方法
            //4、resultTask 处理请求成功后的业务

            Map map = new HashMap();
            map.put("test", "加载框请求 ");
            map.put(JsonConstants.JSON_TASK_LEVEL, TaskLevel.TASK_ONE);
            loadingHttpTask.post(new RequestBean(this, BaseHttpParams.baseParams(UrlConstants.TEST_LIST), map), this);

        } else if (i == R.id.btn_1_3) {//步骤
            //1、创建LoadingHttpTask();
            //2、实现 ILoadingResultTaskCallBack,IRefreshRequestCallBack,类
            //3、调用 layoutHttpTask.post()请求方法
            //4、resultTask 处理请求成功后的业务
            //5、onRefreshRequest 处理请求失败重新请求业务

            testPrompt();

        } else if (i == R.id.btn_2_1) {
            StartActUtils.start(this, Demo_ListViewActivity.class);

        }
    }

    private void testPrompt() {
        Map map2 = new HashMap();
        map2.put("test", "提示框请求 ");
        map2.put(JsonConstants.JSON_TASK_LEVEL, TaskLevel.TASK_TWO);
        layoutHttpTask.post(new RequestBean(this, BaseHttpParams.baseParams(UrlConstants.TEST_LIST), map2), this);
    }

    @Override
    public void onRefreshRequest(TaskRequestLevel level) {
        ToastUtils.show(this, "重试中。。。");
        testPrompt();
    }


}
