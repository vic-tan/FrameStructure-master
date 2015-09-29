package com.android.tanlifei.framestructure.common.http;

import android.content.Context;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.common.http.base.RequestBean;
import com.android.tanlifei.framestructure.common.http.base.TaskController;
import com.android.tanlifei.framestructure.common.utils.ResUtils;
import com.android.tanlifei.framestructure.common.utils.ToastUtils;
import com.android.tanlifei.framestructure.engine.interf.IHttpTaskCallBack;
import com.bigkoo.svprogresshud.SVProgressHUD;

/**
 * 请求接口弹出正加载提示Dialog框
 * （比如进入详情，进去界面前就先请求接口，并且有Dialog加载框，请求过程完成后在进入详情）
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #DialogHttpTask(Context)} 默认弹出正在加载提示框</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class DialogHttpTask extends TaskController {


    private Context context;


    /**
     * 默认弹出正在加载提示框
     *
     * @param context
     */
    public DialogHttpTask(Context context) {
        this.context = context;
    }


    /**
     * 显示正在加载框
     */
    private void dismiss() {
        if (SVProgressHUD.isShowing(context)) {
            SVProgressHUD.dismiss(context);
            return;
        }
    }

    /**
     * 显示正在加载框
     */
    private void show() {
        if (!SVProgressHUD.isShowing(context)) {
            SVProgressHUD.showWithStatus(context, ResUtils.getStr(R.string.common_prompt_loading), SVProgressHUD.SVProgressHUDMaskType.None);
            return;
        }
    }

    @Override
    public IHttpTaskCallBack setCallBack() {
        return new IHttpTaskCallBack() {
            @Override
            public void taskHandler(RequestBean requestBean) {
                switch (requestBean.getRequestStatusLevel()) {
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
                    case TIMEOUT_ERROR:
                        dismiss();
                        ToastUtils.show(context, ResUtils.getStr(R.string.common_prompt_timeout_error));
                        break;
                    case SUCCESS:
                        taskCallBack.taskHandler(requestBean);
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
