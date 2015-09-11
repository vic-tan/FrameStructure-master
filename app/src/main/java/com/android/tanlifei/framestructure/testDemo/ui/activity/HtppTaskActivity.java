package com.android.tanlifei.framestructure.testDemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.bean.paramsBean.LoadingHttpTaskBean;
import com.android.tanlifei.framestructure.common.constants.UrlConstants;
import com.android.tanlifei.framestructure.common.constants.enumConstants.HttpTaskStatus;
import com.android.tanlifei.framestructure.common.constants.enumConstants.PromptStatus;
import com.android.tanlifei.framestructure.common.http.HttpTask;
import com.android.tanlifei.framestructure.common.http.LoadingHttpTask;
import com.android.tanlifei.framestructure.common.http.PromptHttpTask;
import com.android.tanlifei.framestructure.common.utils.InflaterUtils;
import com.android.tanlifei.framestructure.common.utils.Logger;
import com.android.tanlifei.framestructure.common.utils.StartActUtils;
import com.android.tanlifei.framestructure.common.utils.ToastUtils;
import com.android.tanlifei.framestructure.engine.interf.ILoadingPromptReStartCallBack;
import com.android.tanlifei.framestructure.engine.interf.ILoadingResultTaskCallBack;

import java.util.HashMap;
import java.util.Map;


public class HtppTaskActivity extends Activity implements View.OnClickListener, ILoadingResultTaskCallBack,ILoadingPromptReStartCallBack {

    public static final String TAG = "HtppTaskActivity";

    private View view;
    private LoadingHttpTask loadingHttpTask;
    private PromptHttpTask promptHttpTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = InflaterUtils.inflater(this,R.layout.test_activity_http_task);
        setContentView(view);
        loadingHttpTask = new LoadingHttpTask(this);
        promptHttpTask = new PromptHttpTask(this,this,view);
        setOnClickListener();
    }

    @Override
    public void resultTask(Map<String, Object> params, BaseJson baseJson, int taskTag) {
        switch (taskTag){
            case 1://加载框请求
                ToastUtils.show(this, baseJson.getData());
                if (params.containsKey("test"))
                    Logger.i(TAG,params.get("test").toString());//拿请求之前保存的的数据
                break;
            case 2://提示框请求
                ToastUtils.show(this, baseJson.getData());
                if (params.containsKey("test"))
                    Logger.i(TAG,params.get("test").toString());//拿请求之前保存的的数据
                break;
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

                HttpTask.post(UrlConstants.TEST_LIST,new HashMap<String, Object>(),new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        switch (HttpTaskStatus.HttpTaskStatus(msg.what)){
                            case START:
                                ToastUtils.show(HtppTaskActivity.this,"START");
                                break;
                            case SUCCESS:
                                ToastUtils.show(HtppTaskActivity.this,((BaseJson)msg.obj).getData().toString());
                                break;
                            case FAILURE:
                                ToastUtils.show(HtppTaskActivity.this,"FAILURE");
                                break;
                            case FINISH:
                                ToastUtils.show(HtppTaskActivity.this,"FINISH");
                                break;
                            case TIMEOUT_ERROR:
                                ToastUtils.show(HtppTaskActivity.this,"TIMEOUT_ERROR");
                                break;
                            case SERVICE_ERROR:
                                ToastUtils.show(HtppTaskActivity.this,"SERVICE_ERROR");
                                break;
                            case NETWORK_ERROR:
                                ToastUtils.show(HtppTaskActivity.this,"NETWORK_ERROR");
                                break;

                        }
                        return false;
                    }
                }));
                break;
            case R.id.btn_1_2://加载框请求
                //步骤
                //1、创建LoadingHttpTask();
                //2、实现 ILoadingResultTaskCallBack,resultTask
                //3、调用 loadingHttpTask.post()请求方法
                //4、resultTask 处理请求成功后的业务

                Map map = new HashMap();
                map.put("test","加载框请求 ");
                loadingHttpTask.post(UrlConstants.TEST_LIST, new HashMap<String, Object>(), new LoadingHttpTaskBean(this, map, 1));
                break;

            case R.id.btn_1_3://提示框请求

                //步骤
                //1、创建LoadingHttpTask();
                //2、实现 ILoadingResultTaskCallBack,ILoadingPromptReStartCallBack,类
                //3、调用 promptHttpTask.post()请求方法
                //4、resultTask 处理请求成功后的业务
                //5、onRefresh 处理请求失败重新请求业务

                testPrompt();
                break;

            case R.id.btn_2_1:
                StartActUtils.start(this,ListViewActivity.class);
                break;
        }
    }

    private void testPrompt() {
        Map map2 = new HashMap();
        map2.put("test","提示框请求 ");
        promptHttpTask.post(UrlConstants.TEST_LIST,new HashMap<String, Object>(),new LoadingHttpTaskBean(this,map2,2));
    }

    @Override
    public void onRefresh(PromptStatus status) {
        ToastUtils.show(this,"重试中。。。");
        testPrompt();
    }
}
