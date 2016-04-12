package com.example.demo.frame.http;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.common.engine.interf.IRefreshRequestCallBack;
import com.common.okhttp.OkHttpUtils;
import com.common.okhttp.callback.Callback;
import com.common.okhttp.callback.LayoutCallback;
import com.common.okhttp.callback.LoadingCallback;
import com.common.ui.base.activity.BaseActivity;
import com.common.utils.DateFormatUtils;
import com.common.utils.InflaterUtils;
import com.common.utils.Logger;
import com.common.utils.StartActUtils;
import com.common.utils.ToastUtils;
import com.constants.fixed.UrlConstants;
import com.example.demo.R;
import com.example.demo.frame.table.ListViewActivity;

import okhttp3.Call;
import okhttp3.Response;


public class HttpTaskActivity extends BaseActivity implements View.OnClickListener, IRefreshRequestCallBack {


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
        String d = "2016-04-11 23:44:11";
        Logger.e(DateFormatUtils.convertStrFormat(d));
        Logger.e(DateFormatUtils.convertStrFormat(d, DateFormatUtils.DAYS));
        Logger.e(DateFormatUtils.convertStrFormat(d, DateFormatUtils.HOURS));
        Logger.e(DateFormatUtils.convertStrFormat(d, DateFormatUtils.MINUTES));
        Logger.e(DateFormatUtils.convertStrFormat(d, DateFormatUtils.SECONDS));
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
                    super.onError(call,e);
                    ToastUtils.show("onError");
                }

                @Override
                public void onResponse(Object response) {
                    ToastUtils.show( "onResponse");
                }
            });


        } else if (i == R.id.btn_1_2) {//步骤
            OkHttpUtils.post().url(UrlConstants.TEST_LIST).build().execute(new LoadingCallback() {
                @Override
                public Activity getContext() {
                    return HttpTaskActivity.this;
                }

                @Override
                public void onResponse(Object object) {
                    ToastUtils.show( "onResponse" + object);
                    Logger.json("OkHttpUtils", object + "");
                }
            });

        } else if (i == R.id.btn_1_3) {//步骤
            testPrompt();
            String json = "{\"appMachine\":\"Google Nexus 5 - 6.0.0 - API 23 - 1080x1920\",\"appSystem\":\"Android 6.0\",\"client_type\":6,\"company_name\":\"知学云\",\"login_id\":\"zhangzeyan\",\"password\":\"NyWYiW4LjsA\\u003d\"}";
           /* OkHttpUtils.post().url("http://demo.zhixueyun.com/mlds/user/login").addParams("json",json).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e) {
                    ToastUtils.show(HttpTaskActivity.this, "onResponse" + e.toString());
                }

                @Override
                public void onResponse(String response) {
                    ToastUtils.show(HttpTaskActivity.this,  response);
                }
            });*/

        } else if (i == R.id.btn_2_1) {
            StartActUtils.start(this, ListViewActivity.class);

        }
    }

    private void testPrompt() {
        OkHttpUtils.post().url(UrlConstants.TEST_LIST).build().execute(new LayoutCallback() {
            @Override
            public Activity getActivity() {
                return HttpTaskActivity.this;
            }

            @Override
            public View getLayoutView() {
                return view;
            }

            @Override
            public IRefreshRequestCallBack refreshRequestCallBack() {
                return HttpTaskActivity.this;
            }

            @Override
            public void onResponse(Object response) {
                ToastUtils.show("onResponse" + response);
            }
        });
    }

    @Override
    public void onRefreshRequest() {
        ToastUtils.show("重试中。。。");
        testPrompt();
    }


}
