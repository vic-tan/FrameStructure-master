package com.common.okhttp.callback;

import com.common.R;
import com.common.bean.base.BaseBean;
import com.common.ui.base.main.BaseApplication;
import com.common.utils.JsonUtils;
import com.common.utils.NetUtils;
import com.common.utils.ResUtils;
import com.common.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by tanlifei on 15/12/14.
 */
public abstract class StringCallback extends Callback<String>
{

    @Override
    public void onError(Call call, Exception e) {
        if(!NetUtils.isConnected(BaseApplication.appContext)) {
            ToastUtils.show(ResUtils.getStr(R.string.common_prompt_network));
        }
    }

    @Override
    public String parseNetworkResponse(Response response) throws IOException
    {
        BaseBean jsonBean = JsonUtils.parseToObjectBean(replaceId(new String(response.body().string())), BaseBean.class);
        return jsonBean.getData().toString();
    }

}
