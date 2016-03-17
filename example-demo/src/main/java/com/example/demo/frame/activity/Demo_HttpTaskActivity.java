package com.example.demo.frame.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.common.engine.interf.IRefreshRequestCallBack;
import com.common.okhttp.OkHttpUtils;
import com.common.okhttp.callback.Callback;
import com.common.okhttp.callback.LayoutCallback;
import com.common.okhttp.callback.LoadingCallback;
import com.common.ui.base.activity.BaseActivity;
import com.common.utils.InflaterUtils;
import com.common.utils.Logger;
import com.common.utils.StartActUtils;
import com.common.utils.ToastUtils;
import com.constants.fixed.UrlConstants;
import com.example.demo.R;

import okhttp3.Call;
import okhttp3.Response;


public class Demo_HttpTaskActivity extends BaseActivity implements View.OnClickListener, IRefreshRequestCallBack {


    /**
     * | Header 1 | Header 2 |
     * | val1     | val2     |
     */
    public static final String TAG = "HttpTaskActivity";

    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = InflaterUtils.inflater(this, R.layout.test_activity_http_task);
        setContentView(view);
        setOnClickListener();
    }

    @Override
    protected String setActionBarTitle() {
        return "公用请求";
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

            OkHttpUtils.post().url(UrlConstants.TEST_LIST).build().execute(new Callback() {

                @Override
                public Object parseNetworkResponse(Response response) throws Exception {
                    return null;
                }

                @Override
                public void onError(Call call, Exception e) {
                    ToastUtils.show(Demo_HttpTaskActivity.this, "onError");
                }

                @Override
                public void onResponse(Object response) {
                    ToastUtils.show(Demo_HttpTaskActivity.this, "onResponse");
                }
            });


        } else if (i == R.id.btn_1_2) {//步骤
            OkHttpUtils.post().url(UrlConstants.TEST_LIST).build().execute(new LoadingCallback() {
                @Override
                public Activity getContext() {
                    return Demo_HttpTaskActivity.this;
                }

                @Override
                public void onResponse(Object object) {
                    ToastUtils.show(Demo_HttpTaskActivity.this, "onResponse" + object);
                    Logger.json("OkHttpUtils", object + "");
                }
            });

        } else if (i == R.id.btn_1_3) {//步骤
            testPrompt();
            String json = "{\"appMachine\":\"Google Nexus 5 - 6.0.0 - API 23 - 1080x1920\",\"appSystem\":\"Android 6.0\",\"client_type\":6,\"company_name\":\"知学云\",\"login_id\":\"zhangzeyan\",\"password\":\"NyWYiW4LjsA\\u003d\"}";
           /* OkHttpUtils.post().url("http://demo.zhixueyun.com/mlds/user/login").addParams("json",json).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e) {
                    ToastUtils.show(Demo_HttpTaskActivity.this, "onResponse" + e.toString());
                }

                @Override
                public void onResponse(String response) {
                    ToastUtils.show(Demo_HttpTaskActivity.this,  response);
                }
            });*/

        } else if (i == R.id.btn_2_1) {
            StartActUtils.start(this, Demo_ListViewActivity.class);

        }
    }

    private void testPrompt() {
        OkHttpUtils.post().url(UrlConstants.TEST_LIST).build().execute(new LayoutCallback() {
            @Override
            public Activity getActivity() {
                return Demo_HttpTaskActivity.this;
            }

            @Override
            public View getLayoutView() {
                return view;
            }

            @Override
            public IRefreshRequestCallBack refreshRequestCallBack() {
                return Demo_HttpTaskActivity.this;
            }

            @Override
            public void onResponse(Object response) {
                ToastUtils.show(Demo_HttpTaskActivity.this, "onResponse" + response);
            }
        });
    }

    @Override
    public void onRefreshRequest() {
        ToastUtils.show(this, "重试中。。。");
        testPrompt();
    }


}
