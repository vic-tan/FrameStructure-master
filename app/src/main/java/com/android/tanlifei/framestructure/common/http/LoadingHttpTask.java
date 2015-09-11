package com.android.tanlifei.framestructure.common.http;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.bean.paramsBean.LoadingHttpTaskBean;
import com.android.tanlifei.framestructure.common.constants.enumConstants.HttpTaskStatus;
import com.android.tanlifei.framestructure.common.http.base.BaseHttpTask;
import com.android.tanlifei.framestructure.common.http.base.HttpTaskController;
import com.android.tanlifei.framestructure.common.utils.ResUtils;
import com.android.tanlifei.framestructure.common.utils.ToastUtils;
import com.android.tanlifei.framestructure.common.view.prompt.LoadingDialog;

import java.util.Map;

/**
 * 请求接口弹出正加载提示Dialog框
 * （比如进入详情，进去界面前就先请求接口，并且有Dialog加载框，请求过程完成后在进入详情）
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #LoadingHttpTask(Context)} 默认弹出正在加载提示框</li>
 * <li>{@link #LoadingHttpTask(Context, int, int, int, int)} 自定义弹出正在加载提示框 </li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class LoadingHttpTask extends HttpTaskController {


    private LoadingDialog loadingDialog;//正在加载提示框
    private Context context;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (HttpTaskStatus.HttpTaskStatus(msg.what)) {
                case NETWORK_ERROR:
                    dismiss();
                    ToastUtils.show(context, ResUtils.getStr(R.string.common_prompt_network));
                    break;
                case START:
                    loadingDialog.show();
                    break;
                case FAILURE:
                case SERVICE_ERROR:
                    dismiss();
                    ToastUtils.show(context, ((BaseJson) msg.obj).getMsg());
                    break;
                case TIMEOUT_ERROR:
                    dismiss();
                    ToastUtils.show(context, ResUtils.getStr(R.string.common_prompt_timeout_error));
                    break;
                case SUCCESS:
                    loadingHttpTaskBean.getLoadingDialogCallBack().resultTask(loadingHttpTaskBean.getParams(), (BaseJson) msg.obj, loadingHttpTaskBean.getTaskTag());
                    dismiss();
                    break;
                default:
                    dismiss();
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
    public LoadingHttpTask(Context context) {
        this.context = context;
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context);
        }
    }


    /**
     * 自定义弹出正在加载提示框
     *
     * @param context
     * @param width   提示框的宽
     * @param height  提示框的高
     * @param layout  提示框的布局
     * @param style   样式
     */
    public LoadingHttpTask(Context context, int width, int height, int layout,
                           int style) {
        this.context = context;
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context, width, height, layout, style);
        }
    }



    /**
     * 显示正在加载框
     */
    public void dismiss() {
        if (null == loadingDialog)
            return;
        try {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示正在加载框
     */
    public void show() {
        if (null == loadingDialog)
            return;
        try {
            if (!loadingDialog.isShowing()) {
                loadingDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Handler getHandler() {
        return handler;
    }
}
