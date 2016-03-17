package com.common.okhttp.callback;

import android.app.Activity;

import com.common.R;
import com.common.okhttp.json.BaseJson;
import com.common.okhttp.view.BaseLoadingPopup;
import com.common.ui.base.main.BaseApplication;
import com.common.utils.JsonUtils;
import com.common.utils.NetUtils;
import com.common.utils.ResUtils;
import com.common.utils.ToastUtils;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by tanlifei on 15/12/14.
 */
public abstract class LoadingCallback extends Callback {

    private BaseLoadingPopup loadingPopup;

    /**
     * 显示正在加载框
     */
    public void dismiss() {
        if (null!=loadingPopup && loadingPopup.isShowing()) {
            loadingPopup.dismiss();
            return;
        }
    }

    /**
     * 显示正在加载框
     */
    public void show() {
        loadingPopup = new BaseLoadingPopup(getContext());
        return;
    }


    public abstract Activity getContext();

    @Override
    public void onAfter() {
        super.onAfter();
        dismiss();
    }

    @Override
    public Object parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        BaseJson jsonBean = JsonUtils.parseToObjectBean(replaceId(new String(string)), BaseJson.class);
        return jsonBean.getData();
    }

    @Override
    public void onBefore(Request request) {
        super.onBefore(request);
        show();
    }

    @Override
    public void onError(Call call, Exception e) {
        dismiss();
        if(!NetUtils.isConnected(BaseApplication.appContext)) {
            ToastUtils.show(ResUtils.getStr(R.string.common_prompt_network));
        }
    }



}
