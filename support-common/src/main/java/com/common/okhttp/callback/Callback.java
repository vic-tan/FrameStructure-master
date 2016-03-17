package com.common.okhttp.callback;

import com.common.R;
import com.common.ui.base.main.BaseApplication;
import com.common.utils.NetUtils;
import com.common.utils.ResUtils;
import com.common.utils.StringUtils;
import com.common.utils.ToastUtils;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public abstract class Callback<T>
{
    /**
     * UI Thread
     *
     * @param request
     */
    public void onBefore(Request request)
    {

    }

    /**
     * UI Thread
     *
     * @param
     */
    public void onAfter()
    {
    }

    /**
     * UI Thread
     *
     * @param progress
     */
    public void inProgress(float progress)
    {

    }
    /**
     * Thread Pool Thread
     *
     * @param response
     */
    public abstract T parseNetworkResponse(Response response) throws Exception;

    public  void onError(Call call, Exception e){
        if(!NetUtils.isConnected(BaseApplication.appContext)) {
            ToastUtils.show(ResUtils.getStr(R.string.common_prompt_network));
        }
    }

    public abstract void onResponse(T response);


    public static Callback CALLBACK_DEFAULT = new Callback()
    {

        @Override
        public Object parseNetworkResponse(Response response) throws Exception
        {
            return null;
        }

        @Override
        public void onError(Call call, Exception e)
        {
            if(!NetUtils.isConnected(BaseApplication.appContext)) {
                ToastUtils.show(ResUtils.getStr(R.string.common_prompt_network));
            }

        }

        @Override
        public void onResponse(Object response)
        {

        }
    };

    /**
     * 把json 中的"id" key  替换成"my_id" key ,这样做是为了跟 litepal 或GreenDao 等关系型数据库库自带的id冲突
     *
     * @param responseBody
     * @return
     */
    protected static String replaceId(String responseBody) {
        if (StringUtils.isEmpty(responseBody)) {
            return responseBody;
        }
        return responseBody.replace("\"id\":", "\"my_id\":");
    }

}