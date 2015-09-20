package com.android.tanlifei.framestructure.common.view.prompt;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.common.utils.InflaterUtils;


/**
 * 加载数据时数据错误,或为空时view
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #BaseDialog(Context, int)}  创建对话框全屏宽高</li>
 * <li>{@link #BaseDialog(Context, int,int)}  创建可以区分多个对话框标识全屏宽高</li>
 * <li>{@link #BaseDialog(View, int, int)} 创建对话框自定义宽高 </li>
 * <li>{@link #init()} 初始化</li>
 * <li>{@link #getContentView()} 返回contentView</li>
 * <li>{@link #getTitle()} 返回标题控件</li>
 * <li>{@link #getContent()}返回内容控件</li>
 * <li>{@link #getLeftBtn()} 返回左边按钮控件</li>
 * <li>{@link #getRightBtn()} 返回右边按钮控件</li>
 * <li>{@link #setContentLines(int)} 设置内容行数</li>
 * <li>{@link #liftBtnClickListener(View,int)} 左按钮事件监听</li>
 * <li>{@link #rightBtnClickListener(View,int)} 右按钮事件监听</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public abstract class BaseDialog extends PopupWindow {

    protected View contentView;
    protected TextView title, content; //标题，内容
    protected TextView leftBtn, rightBtn; //左按钮，右按钮
    protected int callBackTag = -1;

    /**
     * 创建对话框全屏宽高
     *
     * @param context 上下文
     * @param layoutId 布局编号
     */
    public BaseDialog(Context context, int layoutId) {
        this(InflaterUtils.inflater(context, layoutId), LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    /**
     * 创建可以区分多个对话框标识全屏宽高
     *
     * @param context 上下文
     * @param layoutId 布局编号
     * @param callBackTag 多个弹出框时区分标识
     */
    public BaseDialog(Context context, int layoutId, int callBackTag) {
        this(InflaterUtils.inflater(context, layoutId), LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.callBackTag = callBackTag;
    }

    /**
     * 创建对话框自定义宽高
     *
     * @param contentView
     * @param width
     * @param height
     */
    public BaseDialog(View contentView, int width, int height) {
        super(contentView, width, height, true);
        this.contentView = contentView;
        init();
    }

    /**
     * 初始化
     */
    protected void init() {
        super.showAtLocation(contentView, Gravity.CENTER, 0, 0);
        setAnimationStyle(android.R.style.Animation_Dialog);
        setOutsideTouchable(true);
        setTouchable(true);
        setFocusable(true);
        //setBackgroundDrawable(ResUtils.getDrawable(R.drawable.common_prompt_dialog_window_bg));
        //setBackgroundDrawable(contentView.getResources().getDrawable(R.drawable.common_prompt_dialog_window_bg));
        title = (TextView) contentView.findViewById(R.id.tv_title);
        content = (TextView) contentView.findViewById(R.id.tv_content);
        leftBtn = (TextView) contentView.findViewById(R.id.tv_left);
        rightBtn = (TextView) contentView.findViewById(R.id.tv_right);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                liftBtnClickListener(v,callBackTag);
            }
        });
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                rightBtnClickListener(v,callBackTag);
            }
        });

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
     * 返回标题控件
     *
     * @return TextView
     */
    public TextView getTitle() {
        return title;
    }

    /**
     * 返回内容控件
     *
     * @return TextView
     */
    public TextView getContent() {
        return content;
    }

    /**
     * 返回左边按钮控件
     *
     * @return TextView
     */
    public TextView getLeftBtn() {
        return leftBtn;
    }

    /**
     * 返回右边按钮控件
     *
     * @return TextView
     */
    public TextView getRightBtn() {
        return rightBtn;
    }


    /**
     * 设置内容行数
     *
     * @param lines 行数
     */
    public void setContentLines(int lines) {
        if (content != null) {
            content.setLines(lines);
        }
    }


    /**
     * 左按钮事件监听
     * @param v
     * @param callBackTag 多个弹出框时区分标识
     */
    public abstract  void liftBtnClickListener(View v,int callBackTag);

    /**
     * 右按钮事件监听
     * @param v
     * @param callBackTag 多个弹出框时区分标识
     */
    public abstract  void rightBtnClickListener(View v,int callBackTag);
}
