package com.common.bean.paramsBean;

import com.common.engine.interf.IHttpTaskCallBack;

import java.util.HashMap;
import java.util.Map;


/**
 * 请求接口弹出正加载提示Dialog框（LoadingHttpTask类）构造方法传递的参数
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public class LoadingHttpTaskBean {
    private final Map<String, Object> params;//请求接口前携带的参数
    private final int taskTag;//请求接口任务标识
    private final IHttpTaskCallBack callBack;//请求接口完成要回调

    public LoadingHttpTaskBean(IHttpTaskCallBack callBack, Map params, int taskTag) {
        this.callBack = callBack;
        if (null == params) {
            this.params = new HashMap<>();
        } else {
            this.params = params;
        }
        this.taskTag = taskTag;
    }

    public IHttpTaskCallBack getLoadingDialogCallBack() {
        return callBack;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public int getTaskTag() {
        return taskTag;
    }
}
