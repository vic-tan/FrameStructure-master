package com.android.tanlifei.framestructure.common.http.base;

import android.os.Handler;

import com.android.tanlifei.framestructure.bean.paramsBean.LoadingHttpTaskBean;
import com.android.tanlifei.framestructure.common.http.HttpTask;

import java.util.Map;


/**
 * 请求接口弹出正加载提示Dialog框和进放界面在加载数据时提示框加载的基类
 * (PromptHttpTask类)和（LoadingHttpTask类）的基类
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #get(String, Map, LoadingHttpTaskBean)} get 请求 以普通形式提交参数</li>
 * <li>{@link #getByJsonParams(String, Map, LoadingHttpTaskBean)} get 请求 以json格式提交参数</li>
 * <li>{@link #post(String, Map, LoadingHttpTaskBean)}  post 请求，以普通形式提交参数</li>
 * <li>{@link #postByJsonParams(String, Map, LoadingHttpTaskBean)} post 请求，以json格式提交参数</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public abstract class HttpTaskController extends BaseHttpTask {

    protected LoadingHttpTaskBean loadingHttpTaskBean;//请求接口成功

    /**
     * get 请求 以普通形式提交参数
     *
     * @param callBackParams 请求前参数设置
     * @param url            请求路径
     * @param params         请求参数
     */
    public void get(String url, Map<String, Object> params, LoadingHttpTaskBean callBackParams) {
        this.loadingHttpTaskBean = callBackParams;
        HttpTask.get(url, params, getHandler());
    }

    /**
     * get 请求 以json格式提交参数
     *
     * @param callBackParams 请求前参数设置
     * @param url            请求路径
     * @param params         请求参数
     */
    public void getByJsonParams(String url, Map<String, Object> params, LoadingHttpTaskBean callBackParams) {
        this.loadingHttpTaskBean = callBackParams;
        HttpTask.getByJsonParams(url, params, getHandler());
    }

    /**
     * post 请求，以普通形式提交参数
     *
     * @param callBackParams 请求前参数设置
     * @param url            请求路径
     * @param params         请求参数
     */
    public void post(String url, Map<String, Object> params, LoadingHttpTaskBean callBackParams) {
        this.loadingHttpTaskBean = callBackParams;
        HttpTask.post(url, params, getHandler());
    }


    /**
     * post 请求，以json格式提交参数
     *
     * @param callBackParams 请求前参数设置
     * @param url            请求路径
     * @param params         请求参数
     */
    public void postByJsonParams(String url, Map<String, Object> params, LoadingHttpTaskBean callBackParams) {
        this.loadingHttpTaskBean = callBackParams;
        HttpTask.getByJsonParams(url, params, getHandler());
    }

    protected abstract Handler getHandler();
}
