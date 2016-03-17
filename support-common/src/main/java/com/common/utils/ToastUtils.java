package com.common.utils;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.common.R;
import com.common.ui.base.main.BaseApplication;

/**
 * @author tanlifei
 * @ClassName: ToastUtils
 * @date 2015-01-26 下午3:30:25
 */
public class ToastUtils {

    public static void show(int resId) {
        show( BaseApplication.appContext.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show( int resId, int duration) {
        show(BaseApplication.appContext.getResources().getText(resId), duration);
    }

    public static void show( CharSequence text) {
        show(text, Toast.LENGTH_SHORT);
    }

    public static void show( CharSequence text, int duration) {
        View layout = InflaterUtils.inflater(BaseApplication.appContext, R.layout.common_toast_layout);
    /*  ImageView image = (ImageView) layout.find(R.id.toast_image);
        image.setImageResource(R.mipmap.ic_launcher);*/
        TextView textV = (TextView) layout.findViewById(R.id.toast_text);
        textV.setText(text);

        Toast toast = new Toast(BaseApplication.appContext);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
    }

    public static void show( int resId, Object... args) {
        show(String.format(BaseApplication.appContext.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void show( String format, Object... args) {
        show(String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show( int resId, int duration, Object... args) {
        show(String.format(BaseApplication.appContext.getResources().getString(resId), args), duration);
    }

    public static void show( String format, int duration, Object... args) {
        show( String.format(format, args), duration);
    }

}