package com.common.okhttp.view;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.common.R;
import com.common.utils.AnimationUtils;
import com.common.utils.InflaterUtils;


/**
 * 加载数据时数据错误,或为空时view
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #BaseLoadingPopup(Context)}  创建对话框全屏宽高</li>
 * <li>{@link #BaseLoadingPopup(View, int, int)} 创建对话框自定义宽高 </li>
 * <li>{@link #init()} 初始化</li>
 * <li>{@link #getContentView()} 返回contentView</li>
 * <li>{@link #getTvMsg()}返回内容控件</li>

 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class BaseLoadingPopup extends PopupWindow {

    protected View contentView;
    private ImageView ivBigLoading;
    private TextView tvMsg;


    /**
     * 创建对话框全屏宽高
     *
     * @param context 上下文
     */
    public BaseLoadingPopup(Context context) {
        this(InflaterUtils.inflater(context, R.layout.common_http_request_loading_popup), LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }


    /**
     * 创建对话框自定义宽高
     *
     * @param contentView
     * @param width
     * @param height
     */
    public BaseLoadingPopup(View contentView, int width, int height) {
        super(contentView, width, height, true);
        this.contentView = contentView;
        init();
    }

    /**
     * 初始化
     */
    protected void init() {
        super.showAtLocation(contentView, Gravity.CENTER, 0, 0);
        setAnimationStyle(R.style.loadPopupAnimation);
        update();
        setOutsideTouchable(true);
        setTouchable(true);
        setFocusable(true);
        ivBigLoading = (ImageView) contentView.findViewById(R.id.ivBigLoading);
        tvMsg = (TextView) contentView.findViewById(R.id.tvMsg);
        AnimationUtils.show(ivBigLoading, R.mipmap.common_svstatus_loading);
    }


    /**
     * 返回contentView
     *
     * @return View
     */
    public View getContentView() {
        return contentView;
    }

    /**
     * 返回内容控件
     *
     * @return TextView
     */
    public TextView getTvMsg() {
        return tvMsg;
    }

    public ImageView getIvBigLoading() {
        return ivBigLoading;
    }


}
