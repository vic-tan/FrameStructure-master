package com.common.http.task;

import android.content.Context;
import android.view.View;

import com.common.http.base.RequestBean;
import com.common.http.base.TaskController;
import com.common.prompt.LoadingLayout;
import com.common.engine.interf.IHttpTaskCallBack;
import com.common.engine.interf.IRefreshRequestCallBack;

/**
 * 简单的请求接口，并且有提示布局加载，（比如进入详情，进去界面后在请求接口，请求过程有提示布局）
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #LayoutHttpTask(Context, IRefreshRequestCallBack, View)} 默认弹出正在加载提示框</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class LayoutHttpTask extends TaskController {

    private LoadingLayout promptView;//提示框
    private Context context;


    /**
     * 默认弹出正在加载提示框
     *
     * @param context
     */
    public LayoutHttpTask(Context context, IRefreshRequestCallBack backCall, View view) {
        this.context = context;
        if (promptView == null) {
            promptView = new LoadingLayout(context, backCall, view);
        }
    }


    @Override
    public IHttpTaskCallBack setCallBack() {
        return new IHttpTaskCallBack() {
            @Override
            public void taskCallBack(RequestBean requestBean) {
                switch (requestBean.getRequestLevel()) {
                    case NETWORK_ERROR:
                        promptView.displayNetworkErrorLayout();
                        break;
                    case START:
                        promptView.displayProgressLayout();
                        break;
                    case EXCEPTION:
                    case FAILURE:
                    case SERVICE_ERROR:
                        promptView.displayserviceErrorLayout();
                        break;
                    case TIMEOUT_ERROR:
                    case CANCEL:
                        promptView.displayTimeoutErrorLayout();
                        break;
                    case SUCCESS:
                        taskCallBack.taskCallBack(requestBean);
                        promptView.getPromptLayout().setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
            }
        };
    }

}
