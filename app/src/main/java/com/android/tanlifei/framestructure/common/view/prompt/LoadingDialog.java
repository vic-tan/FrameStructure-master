package com.android.tanlifei.framestructure.common.view.prompt;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.tanlifei.framestructure.R;

/**
 * 自定加载对话框
 *
 * @author tanlifei
 * @date 2015年2月11日 上午11:17:15
 */
public  class LoadingDialog extends Dialog {
    private static int default_width = 160; // 默认宽度
    private static int default_height = 120;// 默认高度

    private TextView msg;
    //private DialogDismissImpl dismissImpl;

    public LoadingDialog(Context context) {
        this(context, default_width, default_height, R.layout.common_dialog_loading,
                R.style.loading_dialog);
    }

    public LoadingDialog(Context context, int width, int height, int layout,
                         int style) {
        super(context, style);
        setContentView(layout);
        msg = (TextView) findViewById(R.id.tv_msg);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        float density = getDensity(context);
        params.width = (int) (width * density);
        params.height = (int) (height * density);
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    private float getDensity(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.density;
    }

    public void setMsgText(CharSequence hint) {
        msg.setText(hint);
    }


        /*public void setDismissImpl(DialogDismissImpl dismissImpl) {
            this.dismissImpl = dismissImpl;
        }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if ((keyCode == KeyEvent.KEYCODE_BACK)) {
                if (isShowing()) {
                    if (dismissImpl != null) {
                        dismissImpl.dismissControll();
                        dismissImpl = null;
                    }
                    dismiss();
                }
            }
            return true;
        }

        public interface DialogDismissImpl {
            void dismissControll();
        }*/

}