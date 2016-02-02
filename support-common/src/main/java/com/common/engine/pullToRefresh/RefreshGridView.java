package com.common.engine.pullToRefresh;

import android.content.Context;
import android.view.View;
import android.widget.GridView;

import com.common.R;
import com.common.engine.interf.IPullToRefreshCallBack;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;


/**
 * 上拉下拉刷新的 listView 共用业务处理
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #RefreshGridView(Context, IPullToRefreshCallBack, View)}  当 GridView 为上拉下拉时调用的构造方法</li>
 * <li>{@link #RefreshGridView(Context, IPullToRefreshCallBack, View, PullToRefreshBase.Mode)} 当 GridView 为上拉下拉自定义是调用的构造方法</li>
 * <li>{@link #init(PullToRefreshBase.Mode)} 初始化数据</li>
 * <li>{@link #getmPullRefreshGridView()} 获取当前 GridView</li>
 * </ul>
 * <ul>
 * <strong>子类要实现或者实现父类的的方法</strong>
 * <li>{@link #requestFinish()} 请求完成后的操作</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 *
 */
public class RefreshGridView extends BasePullToRefresh {

    private PullToRefreshGridView mPullRefreshGridView;//上拉下拉刷新的 listVie

    /**
     *  当 GridView 为上拉下拉时调用的构造方法
     * @param context 上下文
     * @param refreshCallBack 操作完成后的回调
     * @param baseView 布局
     */
    public RefreshGridView(Context context, IPullToRefreshCallBack refreshCallBack, View baseView) {
        baseInit(context, baseView, refreshCallBack);
        init(PullToRefreshBase.Mode.BOTH);
        startRequest();
    }

    /**
     *  当 GridView 为上拉下拉自定义是调用的构造方法
     * @param context 上下文
     * @param refreshCallBack 操作完成后的回调
     * @param baseView 布局
     * @param mode 上拉下拉方式
     */
    public RefreshGridView(Context context, IPullToRefreshCallBack refreshCallBack, View baseView, PullToRefreshBase.Mode mode) {
        baseInit(context, baseView, refreshCallBack);
        init(mode);
        startRequest();
    }



    /**
     * 初始化数据
     * @param setMode 上拉下拉方式
     */
    private void init(PullToRefreshBase.Mode setMode) {
        mPullRefreshGridView = (PullToRefreshGridView) baseView.findViewById(R.id.gv_pull_to_refresh);
        if(setMode == PullToRefreshBase.Mode.DISABLED){
            mode = PullToRefreshBase.Mode.DISABLED;
        }
        mPullRefreshGridView.setMode(setMode);
        mPullRefreshGridView.setAdapter(refreshCallBack.getAdapter());
        mPullRefreshGridView.setEmptyView(loadingPrompt.getPromptLayout());
        mPullRefreshGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                pullDownToRefresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                pullUpToRefresh();
            }
        });
    }



    @Override
    public void requestFinish() {
        mPullRefreshGridView.onRefreshComplete();
    }

    /**
     * 获取当前 GridView
     * @return
     */
    public PullToRefreshGridView getmPullRefreshGridView() {
        return mPullRefreshGridView;
    }
}
