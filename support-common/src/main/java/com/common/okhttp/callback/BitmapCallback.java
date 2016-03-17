package com.common.okhttp.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.common.R;
import com.common.ui.base.main.BaseApplication;
import com.common.utils.NetUtils;
import com.common.utils.ResUtils;
import com.common.utils.ToastUtils;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by tanlifei on 15/12/14.
 */
public abstract class BitmapCallback extends Callback<Bitmap>
{
    @Override
    public void onError(Call call, Exception e) {
        if(!NetUtils.isConnected(BaseApplication.appContext)) {
            ToastUtils.show(ResUtils.getStr(R.string.common_prompt_network));
        }
    }

    @Override
    public Bitmap parseNetworkResponse(Response response) throws Exception
    {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }

}
