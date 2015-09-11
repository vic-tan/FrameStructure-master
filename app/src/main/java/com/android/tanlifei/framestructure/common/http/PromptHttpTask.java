package com.android.tanlifei.framestructure.common.http;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.bean.paramsBean.LoadingHttpTaskBean;
import com.android.tanlifei.framestructure.common.constants.enumConstants.HttpTaskStatus;
import com.android.tanlifei.framestructure.common.http.base.BaseHttpTask;
import com.android.tanlifei.framestructure.common.http.base.HttpTaskController;
import com.android.tanlifei.framestructure.common.view.prompt.LoadingPrompt;
import com.android.tanlifei.framestructure.engine.interf.ILoadingPromptReStartCallBack;

import java.util.Map;

/**
 * 简单的请求接口，并且有提示布局加载，（比如进入详情，进去界面后在请求接口，请求过程有提示布局）
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #PromptHttpTask(Context, ILoadingPromptReStartCallBack, View)} 默认弹出正在加载提示框</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class PromptHttpTask extends HttpTaskController {

    private LoadingPrompt promptView;//提示框
    private Context context;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (HttpTaskStatus.HttpTaskStatus(msg.what)) {
                case NETWORK_ERROR:
                    promptView.displayNetworkErrorLayout();
                    break;
                case START:
                    promptView.displayProgressLayout();
                    break;
                case FAILURE:
                case SERVICE_ERROR:
                    promptView.displayserviceErrorLayout();
                    break;
                case TIMEOUT_ERROR:
                case CANCEL:
                    promptView.displayTimeoutErrorLayout();
                    break;
                case SUCCESS:
                    loadingHttpTaskBean.getLoadingDialogCallBack().resultTask(loadingHttpTaskBean.getParams(), (BaseJson) msg.obj, loadingHttpTaskBean.getTaskTag());
                    promptView.getPromptLayout().setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
            return true;
        }
    });


    /**
     * 默认弹出正在加载提示框
     *
     * @param context
     */
    public PromptHttpTask(Context context,ILoadingPromptReStartCallBack backCall,View view) {
        this.context = context;
        if (promptView == null) {
            promptView = new LoadingPrompt(context,backCall,view);
        }
    }




    @Override
    protected Handler getHandler() {
        return handler;
    }


}
