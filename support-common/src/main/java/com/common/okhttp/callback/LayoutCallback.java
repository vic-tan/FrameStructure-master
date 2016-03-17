package com.common.okhttp.callback;

import android.app.Activity;
import android.view.View;

import com.common.bean.base.BaseBean;
import com.common.engine.interf.IRefreshRequestCallBack;
import com.common.prompt.LoadingLayout;
import com.common.utils.JsonUtils;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by tanlifei on 15/12/14.
 */
public abstract class LayoutCallback extends Callback {

    private LoadingLayout promptView;//提示框


    public abstract Activity getActivity();

    public abstract View getLayoutView();

    public abstract IRefreshRequestCallBack refreshRequestCallBack();

    @Override
    public void onAfter() {
        super.onAfter();
        promptView.getPromptLayout().setVisibility(View.GONE);
    }

    @Override
    public Object parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        BaseBean jsonBean = JsonUtils.parseToObjectBean(replaceId(new String(string)), BaseBean.class);
        return jsonBean.data;

    }

    @Override
    public void onBefore(Request request) {
        super.onBefore(request);
        if (promptView == null) {
            promptView = new LoadingLayout(getActivity(), refreshRequestCallBack(), getLayoutView());
        }
        promptView.displayProgressLayout();
    }

    @Override
    public void onError(Call call, Exception e) {
        promptView.displayserviceErrorLayout();
    }




}
