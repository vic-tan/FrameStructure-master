package com.common.okhttp.callback;

import com.common.bean.base.BaseBean;
import com.common.utils.JsonUtils;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by tanlifei on 15/12/14.
 */
public abstract class StringCallback extends Callback<String>
{

    @Override
    public String parseNetworkResponse(Response response) throws IOException
    {
        BaseBean jsonBean = JsonUtils.parseToObjectBean(replaceId(new String(response.body().string())), BaseBean.class);
        return jsonBean.getData().toString();
    }

}
