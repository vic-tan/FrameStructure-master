package com.android.tanlifei.framestructure.common.utils;

import android.content.Context;
import android.widget.TextView;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.common.view.dialog.LoadingDialog;


/**
 * 弹出或者隐藏加载提示框操作
 * <ul>
 * <strong> 方法 </strong>
 * <li>{@link #createDialog(Context)}  创建加载提示框 </li>
 * <li>{@link #setContent(LoadingDialog, String)}   设置提示内容 </li>
 * <li>{@link #dismiss(LoadingDialog)} 隐藏加载提示框</li>
 * <li>{@link #show(LoadingDialog)} 显示加载提示框 </li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015-01-26 下午3:30:25
 */
public class LoadingDialogUtils {

    /**
     * 创建加载提示框
     *
     * @param context
     * @return
     */
    public static LoadingDialog createDialog(Context context) {
        return new LoadingDialog(context);
    }

    /**
     * 设置提示内容
     *
     * @param dialog
     * @param content
     */
    public static void setContent(LoadingDialog dialog, String content) {
        if (null == dialog)
            return;
        try {
            dialog.setMsgText(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏加载提示框
     *
     * @param dialog
     */
    public static void dismiss(LoadingDialog dialog) {
        if (null == dialog)
            return;
        try {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示加载提示框
     *
     * @param dialog
     */
    public static void show(LoadingDialog dialog) {
        if (null == dialog)
            return;
        try {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
