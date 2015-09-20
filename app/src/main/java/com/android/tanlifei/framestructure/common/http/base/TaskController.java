package com.android.tanlifei.framestructure.common.http.base;

import com.android.tanlifei.framestructure.common.constants.enumConstants.TaskLevel;
import com.android.tanlifei.framestructure.common.http.HttpTask;
import com.android.tanlifei.framestructure.engine.interf.IHttpTaskCallBack;


/**
 * 请求接口弹出正加载提示Dialog框和进放界面在加载数据时提示框加载的基类
 * (PromptHttpTask类)和（LoadingHttpTask类）的基类
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #get(RequestParamBean, IHttpTaskCallBack)} get 请求 以普通形式提交参数</li>
 * <li>{@link #get(RequestParamBean,  TaskLevel, IHttpTaskCallBack)} get 请求 以普通形式提交参数</li>
 * <li>{@link #getByJsonParams(RequestParamBean, IHttpTaskCallBack)} get 请求 以json格式提交参数</li>
 * <li>{@link #getByJsonParams(RequestParamBean, TaskLevel, IHttpTaskCallBack)} get 请求 以json格式提交参数</li>
 * <li>{@link #post(RequestParamBean, IHttpTaskCallBack)}  post 请求，以普通形式提交参数</li>
 * <li>{@link #post(RequestParamBean, TaskLevel, IHttpTaskCallBack)}  post 请求，以普通形式提交参数</li>
 * <li>{@link #postByJsonParams(RequestParamBean, IHttpTaskCallBack)} post 请求，以json格式提交参数</li>
 * <li>{@link #postByJsonParams(RequestParamBean, TaskLevel, IHttpTaskCallBack)} post 请求，以json格式提交参数</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public abstract class TaskController extends BaseHttpTask {

    protected IHttpTaskCallBack taskCallBack;

    /**
     * get 请求 以普通形式提交参数
     *
     * @param level  多个请求任务区分标识
     * @param taskCallBack 回调
     * @param params       请求参数
     */
    public void get(RequestParamBean params, TaskLevel level, IHttpTaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
        HttpTask.get(params, level, setCallBack());
    }

    /**
     * get 请求 以普通形式提交参数
     *
     * @param taskCallBack 回调
     * @param params       请求参数
     */
    public void get(RequestParamBean params, IHttpTaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
        HttpTask.get(params, setCallBack());
    }

    /**
     * get 请求 以json格式提交参数
     *
     * @param level  多个请求任务区分标识
     * @param taskCallBack 回调
     * @param params       请求参数
     */
    public void getByJsonParams(RequestParamBean params, TaskLevel level, IHttpTaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
        HttpTask.getByJsonParams(params, level, setCallBack());
    }

    /**
     * get 请求 以json格式提交参数
     *
     * @param taskCallBack 回调
     * @param params       请求参数
     */
    public void getByJsonParams(RequestParamBean params, IHttpTaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
        HttpTask.getByJsonParams(params, setCallBack());
    }

    /**
     * post 请求，以普通形式提交参数
     *
     * @param level  多个请求任务区分标识
     * @param taskCallBack 回调
     * @param params       请求参数
     */
    public void post(RequestParamBean params, TaskLevel level, IHttpTaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
        HttpTask.post(params, level, setCallBack());
    }

    /**
     * post 请求，以普通形式提交参数
     *
     * @param taskCallBack 回调
     * @param params       请求参数
     */
    public void post(RequestParamBean params, IHttpTaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
        HttpTask.post(params, setCallBack());
    }


    /**
     * post 请求，以json格式提交参数
     *
     * @param level  多个请求任务区分标识
     * @param taskCallBack 回调
     * @param params       请求参数
     */
    public void postByJsonParams(RequestParamBean params, TaskLevel level, IHttpTaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
        HttpTask.getByJsonParams(params, level, setCallBack());
    }

    /**
     * post 请求，以json格式提交参数
     *
     * @param taskCallBack 回调
     * @param params       请求参数
     */
    public void postByJsonParams(RequestParamBean params, IHttpTaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
        HttpTask.getByJsonParams(params, setCallBack());
    }

    public abstract IHttpTaskCallBack setCallBack();

}
