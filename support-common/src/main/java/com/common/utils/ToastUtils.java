package com.common.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.common.R;

/**
 * @author tanlifei
 * @ClassName: ToastUtils
 * @date 2015-01-26 下午3:30:25
 */
public class ToastUtils {

    public static void show(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration) {
        show(context, context.getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, CharSequence text, int duration) {
        View layout = InflaterUtils.inflater(context, R.layout.common_toast_layout);
    /*  ImageView image = (ImageView) layout.find(R.id.toast_image);
        image.setImageResource(R.mipmap.ic_launcher);*/
        TextView textV = (TextView) layout.findViewById(R.id.toast_text);
        textV.setText(text);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
    }

    public static void show(Context context, int resId, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), duration);
    }

    public static void show(Context context, String format, int duration, Object... args) {
        show(context, String.format(format, args), duration);
    }

}