package com.common.okhttp.builder;


import com.common.okhttp.OkHttpUtils;
import com.common.okhttp.request.OtherRequest;
import com.common.okhttp.request.RequestCall;

/**
 * Created by tanlifei on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers).build();
    }
}
