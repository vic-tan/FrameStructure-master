package com.common.view.prompt;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.R;
import com.common.engine.interf.IRefreshRequestCallBack;
import com.common.utils.AnimationUtils;
import com.common.utils.InflaterUtils;
import com.common.utils.ResUtils;


/**
 * 加载数据时数据错误,或为空时view
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #LoadingLayout(Context, IRefreshRequestCallBack)} 本构造方法主要是父类不包含layout.common_prompt 布局的时候调用该构造方法</li>
 * <li>{@link #LoadingLayout(Context, IRefreshRequestCallBack, View)}本构造方法主要是父类包含layout.common_prompt 所以父类xml,必须通过incodue 引入layout.common_prompt 布局的时候调用该构造方法 </li>
 * <li>{@link #create(IRefreshRequestCallBack)} 创建提示布局</li>
 * <li>{@link #initView()} 初化布局控件</li>
 * <li>{@link #getPromptLayout()} 获取加载提示布局</li>
 * <li>{@link #setViewBackgroundColor(int)} 设置加载提示布局的背景色</li>
 * <li>{@link #setViewBackgroundDrawable(int)} 设置加载提示布局的背景图片</li>
 * <li>{@link #displayLayout(int)} 显示加载提示布局</li>
 * <li>{@link #displayProgressLayout()} 显示默认文字正在加载提示</li>
 * <li>{@link #displayProgressLayout(int)} 显示自定义文字正在加载提示</li>
 * <li>{@link #displayNetworkErrorLayout()} 网络错误提示</li>
 * <li>{@link #displayserviceErrorLayout()} 服务器错误提示</li>
 * <li>{@link #displayTimeoutErrorLayout()} 超时错误提示</li>
 * <li>{@link #displayEmptyLayout(int)} 自定义提示文字的空数据提示</li>
 * <li>{@link #display(int, int)} 自定义设置提示内容，图片，及提示状态</li>
 * <li>{@link #setPromptLogo(int)} 设置提示图片</li>
 * <li>{@link #setPromptContent(int)} 设置提示语</li>
 * <li>{@link #setProgressLayout(int)} 显示正在加载提示布局</li>
 * <li>{@link #setErrorLayout(int)} 显示正在错误布局提示布局</li>
 * <li>{@link #setRefresh(IRefreshRequestCallBack)} 点击重新请求</li>
 * <p/>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class LoadingLayout {

    private View view;//加载提示布局
    private ImageView load;//加载圏
    private ImageView logo;//提示图片
    private TextView prompt;//提示文字
    private LinearLayout promptLayout;//加载提示布局
    private boolean isIncodue = false;//layout.common_prompt 是子布局
    private Context context;


    /**
     * 本构造方法主要是父类不包含layout.common_prompt 布局的时候调用该构造方法
     *
     * @param context  上下文
     * @param backCall 回调接口类
     */
    public LoadingLayout(Context context,IRefreshRequestCallBack backCall) {
        super();
        this.context = context;
        view = InflaterUtils.inflater(context, R.layout.common_loading_prompt);
        create(backCall);
        isIncodue = false;
    }


    /**
     * 本构造方法主要是父类包含layout.common_prompt
     * 所以父类xml,必须通过incodue 引入layout.common_prompt 布局的时候调用该构造方法
     *
     * @param context  上下文
     * @param backCall 回调接口类
     * @param view     父类布局
     */
    public LoadingLayout(Context context, IRefreshRequestCallBack backCall, View view) {
        super();
        this.view = view;
        isIncodue = true;
        this.context = context;
        promptLayout = (LinearLayout) view.findViewById(R.id.layout_root);
        create(backCall);
    }


    /**
     * 创建提示布局
     *
     * @param backCall
     */
    private void create(IRefreshRequestCallBack backCall) {
        initView();
        setRefresh(backCall);
    }

    /**
     * 初化布局控件
     */
    private void initView() {
        displayLayout(View.VISIBLE);
        load = (ImageView) view.findViewById(R.id.pb_loading);
        logo = (ImageView) view.findViewById(R.id.iv_logo);
        prompt = (TextView) view.findViewById(R.id.tv_hint);
    }


    /**
     * 获取加载提示布局
     *
     * @return
     */
    public View getPromptLayout() {
        if (isIncodue) {
            return promptLayout == null ? promptLayout = (LinearLayout) view.findViewById(R.id.layout_root) : promptLayout;
        } else {
            return view;
        }
    }


    /**
     * 设置加载提示布局的背景色
     *
     * @param colorId
     */
    public void setViewBackgroundColor(int colorId) {
        if (null != view) {
            view.setBackgroundColor(colorId);
        }
    }

    /**
     * 设置加载提示布局的背景图片
     *
     * @param resId
     */
    public void setViewBackgroundDrawable(int resId) {
        if (null != view) {
            view.setBackgroundDrawable(ResUtils.getDrawable(resId));
        }
    }


    /**
     * 显示加载提示布局
     *
     * @param dispaly 显示隐藏值
     */
    public void displayLayout(int dispaly) {
        if (promptLayout != null) {
            promptLayout.setVisibility(dispaly);
        }
    }


    /**
     * 显示默认文字正在加载提示
     *
     * @return
     */
    public View displayProgressLayout() {
        return setProgressLayout(R.string.common_loading);
    }

    /**
     * 显示自定义文字正在加载提示
     *
     * @param strId
     * @return
     */
    public View displayProgressLayout(int strId) {
        return setProgressLayout(strId);
    }


    /**
     * 网络错误提示
     *
     * @return
     */
    public View displayNetworkErrorLayout() {
        return setErrorLayout(R.string.common_loading_network);
    }

    /**
     * 服务器错误提示
     *
     * @return
     */
    public View displayserviceErrorLayout() {
        return setErrorLayout( R.string.common_loading_serivce);
    }

    /**
     * 超时错误提示
     *
     * @return
     */
    public View displayTimeoutErrorLayout() {
        return setErrorLayout( R.string.common_loading_timeout_error);
    }


    /**
     * 默认空数据提示
     *
     * @return
     */
    public View displayEmptyLayout() {
        return setErrorLayout(R.string.common_loading_empty);
    }

    /**
     * 自定义提示文字的空数据提示
     *
     * @return
     */
    public View displayEmptyLayout(int strId) {
        return setErrorLayout(strId);
    }


    /**
     * 自定义设置提示内容，图片，及提示状态
     *
     * @param resId  提示文字
     * @param strId  提示内容
     * @return
     */
    public View display(int resId, int strId) {
        setPromptLogo(resId);
        return setErrorLayout(strId);
    }


    /**
     * 设置提示图片
     *
     * @param resId
     */
    public void setPromptLogo(int resId) {//设置logo
        logo.setImageResource(resId);
    }


    /**
     * 设置提示语
     *
     * @param resId
     */
    public void setPromptContent(int resId) {//设置提示语
        prompt.setText(ResUtils.getStr(resId));
    }

    /**
     * 显示正在加载提示布局
     *
     * @param strId
     * @return
     */
    private View setProgressLayout(int strId) {
        AnimationUtils.show(load, R.mipmap.common_svstatus_loading);
        displayLayout(View.VISIBLE);
        load.setVisibility(View.VISIBLE);
        logo.setVisibility(View.GONE);
        view.setClickable(false);
        setPromptContent(strId);
        return view;
    }

    /**
     * 显示正在错误布局提示布局
     *
     * @param strId
     * @return
     */
    private View setErrorLayout( int strId) {
        displayLayout(View.VISIBLE);
        setPromptContent(strId);
        view.setClickable(true);
        load.setAnimation(null);
        load.setVisibility(View.GONE);
        logo.setVisibility(View.VISIBLE);
        return view;
    }


    /**
     * 点击重新请求
     *
     * @param backCall 当前布局显示类型
     */
    public void setRefresh(final IRefreshRequestCallBack backCall) {//设置点击事件
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                backCall.onRefreshRequest();
            }
        });
        if (null != promptLayout) {
            promptLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    backCall.onRefreshRequest();
                }
            });
        }
    }


}
