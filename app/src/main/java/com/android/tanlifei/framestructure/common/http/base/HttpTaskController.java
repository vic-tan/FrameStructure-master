package com.android.tanlifei.framestructure.common.http.base;

import com.android.tanlifei.framestructure.common.http.HttpTask;
import com.android.tanlifei.framestructure.engine.interf.IHttpTaskCallBack;


/**
 * 请求接口弹出正加载提示Dialog框和进放界面在加载数据时提示框加载的基类
 * (PromptHttpTask类)和（LoadingHttpTask类）的基类
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #get(ReqBean, IHttpTaskCallBack)} get 请求 以普通形式提交参数</li>
 * <li>{@link #get(ReqBean, int, IHttpTaskCallBack)} get 请求 以普通形式提交参数</li>
 * <li>{@link #getByJsonParams(ReqBean, IHttpTaskCallBack)} get 请求 以json格式提交参数</li>
 * <li>{@link #getByJsonParams(ReqBean, int, IHttpTaskCallBack)} get 请求 以json格式提交参数</li>
 * <li>{@link #post(ReqBean, IHttpTaskCallBack)}  post 请求，以普通形式提交参数</li>
 * <li>{@link #post(ReqBean, int, IHttpTaskCallBack)}  post 请求，以普通形式提交参数</li>
 * <li>{@link #postByJsonParams(ReqBean, IHttpTaskCallBack)} post 请求，以json格式提交参数</li>
 * <li>{@link #postByJsonParams(ReqBean, int, IHttpTaskCallBack)} post 请求，以json格式提交参数</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public abstract class HttpTaskController extends BaseHttpTask {

    protected IHttpTaskCallBack taskCallBack;

    /**
     * get 请求 以普通形式提交参数
     *
     * @param callBackTag  多个请求任务区分标识
     * @param taskCallBack 回调
     * @param params       请求参数
     */
    public void get(ReqBean params, int callBackTag, IHttpTaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
        HttpTask.get(params, callBackTag, setCallBack());
    }

    /**
     * get 请求 以普通形式提交参数
     *
     * @param taskCallBack 回调
     * @param params       请求参数
     */
    public void get(ReqBean params, IHttpTaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
        HttpTask.get(params, setCallBack());
    }

    /**
     * get 请求 以json格式提交参数
     *
     * @param callBackTag  多个请求任务区分标识
     * @param taskCallBack 回调
     * @param params       请求参数
     */
    public void getByJsonParams(ReqBean params, int callBackTag, IHttpTaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
        HttpTask.getByJsonParams(params, callBackTag, setCallBack());
    }

    /**
     * get 请求 以json格式提交参数
     *
     * @param taskCallBack 回调
     * @param params       请求参数
     */
    public void getByJsonParams(ReqBean params, IHttpTaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
        HttpTask.getByJsonParams(params, setCallBack());
    }

    /**
     * post 请求，以普通形式提交参数
     *
     * @param callBackTag  多个请求任务区分标识
     * @param taskCallBack 回调
     * @param params       请求参数
     */
    public void post(ReqBean params, int callBackTag, IHttpTaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
        HttpTask.post(params, callBackTag, setCallBack());
    }

    /**
     * post 请求，以普通形式提交参数
     *
     * @param taskCallBack 回调
     * @param params       请求参数
     */
    public void post(ReqBean params, IHttpTaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
        HttpTask.post(params, setCallBack());
    }


    /**
     * post 请求，以json格式提交参数
     *
     * @param callBackTag  多个请求任务区分标识
     * @param taskCallBack 回调
     * @param params       请求参数
     */
    public void postByJsonParams(ReqBean params, int callBackTag, IHttpTaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
        HttpTask.getByJsonParams(params, callBackTag, setCallBack());
    }

    /**
     * post 请求，以json格式提交参数
     *
     * @param taskCallBack 回调
     * @param params       请求参数
     */
    public void postByJsonParams(ReqBean params, IHttpTaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
        HttpTask.getByJsonParams(params, setCallBack());
    }

    public abstract IHttpTaskCallBack setCallBack();

}
