package com.android.tanlifei.framestructure.common.view.prompt;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.common.utils.PhoneUtils;


/**
 * 自定弹出正在加载对话框
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #LoadingDialog(Context)} 创建默认大小的正在加载对话框</li>
 * <li>{@link #LoadingDialog(Context, int, int, int, int)} 创建自定义宽高正在加载对话框 </li>
 * <li>{@link #init(Context, int, int)} 初始化</li>
 * <li>{@link #onTouchEvent(MotionEvent)} 触摸事件传递控制</li>
 * <li>{@link #setMsgText(CharSequence)} 设置加载提示文字</li>
 * <p/>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class LoadingDialog extends Dialog {
    private static int default_width = 160; // 默认宽度
    private static int default_height = 120;// 默认高度

    private TextView msg;

    /**
     * 创建默认大小的正在加载对话框
     *
     * @param context
     */
    public LoadingDialog(Context context) {
        this(context, default_width, default_height, R.layout.common_dialog_loading,
                R.style.loading_dialog);
    }

    /**
     * 创建自定义宽高正在加载对话框
     *
     * @param context
     * @param width
     * @param height
     * @param layout
     * @param style
     */
    public LoadingDialog(Context context, int width, int height, int layout,
                         int style) {
        super(context, style);
        setContentView(layout);
        msg = (TextView) findViewById(R.id.tv_msg);
        init(context, width, height);
    }

    /**
     * 初始化
     *
     * @param context
     * @param width
     * @param height
     */
    private void init(Context context, int width, int height) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (width * PhoneUtils.getDensity(context));
        params.height = (int) (height * PhoneUtils.getDensity(context));
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);
    }

    /**
     * 触摸事件传递控制
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }


    /**
     * 设置加载提示文字
     *
     * @param hint
     */
    public void setMsgText(CharSequence hint) {
        msg.setText(hint);
    }


}