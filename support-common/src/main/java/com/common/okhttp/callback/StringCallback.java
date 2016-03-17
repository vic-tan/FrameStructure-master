package com.common.okhttp.callback;

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

    }

    @Override
    public String parseNetworkResponse(Response response) throws IOException
    {
        return response.body().string();
    }

}
