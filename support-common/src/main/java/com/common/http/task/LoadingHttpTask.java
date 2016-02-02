package com.common.http.task;

import android.content.Context;

import com.common.R;
import com.common.http.view.BaseLoadingPopup;
import com.common.http.base.RequestBean;
import com.common.http.base.TaskController;
import com.common.utils.ResUtils;
import com.common.utils.ToastUtils;
import com.common.engine.interf.IHttpTaskCallBack;

/**
 * 请求接口弹出正加载提示Dialog框
 * （比如进入详情，进去界面前就先请求接口，并且有Dialog加载框，请求过程完成后在进入详情）
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #LoadingHttpTask(Context)} 默认弹出正在加载提示框</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class LoadingHttpTask extends TaskController {


    private Context context;
    private BaseLoadingPopup loadingPopup;


    /**
     * 默认弹出正在加载提示框
     *
     * @param context
     */
    public LoadingHttpTask(Context context) {
        this.context = context;
    }

    /**
     * 显示正在加载框
     */
    private void dismiss() {
        if (null!=loadingPopup && loadingPopup.isShowing()) {
            loadingPopup.dismiss();
            return;
        }
    }

    /**
     * 显示正在加载框
     */
    private void show() {
        loadingPopup = new BaseLoadingPopup(context);
        return;
    }



    @Override
    public IHttpTaskCallBack setCallBack() {
        return new IHttpTaskCallBack() {
            @Override
            public void taskCallBack(RequestBean requestBean) {
                switch (requestBean.getRequestLevel()) {
                    case NETWORK_ERROR:
                        dismiss();
                        ToastUtils.show(context, ResUtils.getStr(R.string.common_prompt_network));
                        break;
                    case START:
                        show();
                        break;
                    case FAILURE:
                    case SERVICE_ERROR:
                        dismiss();
                        if (null != requestBean.getBaseJson()) {
                            ToastUtils.show(context, requestBean.getBaseJson().getMsg());
                        }
                        break;
                    case EXCEPTION:
                        dismiss();
                        break;
                    case TIMEOUT_ERROR:
                        dismiss();
                        ToastUtils.show(context, ResUtils.getStr(R.string.common_prompt_timeout_error));
                        break;
                    case SUCCESS:
                        taskCallBack.taskCallBack(requestBean);
                        dismiss();
                        break;
                    default:
                        dismiss();
                        break;
                }
            }
        };
    }

}
