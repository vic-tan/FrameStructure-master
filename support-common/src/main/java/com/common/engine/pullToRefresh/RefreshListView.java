package com.common.engine.pullToRefresh;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.common.R;
import com.common.engine.interf.IPullToRefreshCallBack;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


/**
 * 上拉下拉刷新的 listView 共用业务处理
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #RefreshListView(Context, IPullToRefreshCallBack, View)}  当 listView 为上拉下拉时调用的构造方法</li>
 * <li>{@link #RefreshListView(Context, IPullToRefreshCallBack, View, PullToRefreshBase.Mode)} 当 listView 为上拉下拉自定义是调用的构造方法</li>
 * <li>{@link #init(PullToRefreshBase.Mode)} 初始化数据</li>
 * <li>{@link #getmPullRefreshListView()} 获取当前 ListView</li>
 * </ul>
 * <ul>
 * <strong>子类要实现或者实现父类的的方法</strong>
 * <li>{@link #requestFinish()} 请求完成后的操作</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public class RefreshListView extends BasePullToRefresh {

    private PullToRefreshListView mPullRefreshListView;//上拉下拉刷新的 listVie


    /**
     * 当 listView 为上拉下拉时调用的构造方法
     *
     * @param context         上下文
     * @param refreshCallBack 操作完成后的回调
     * @param baseView        布局
     */
    public RefreshListView(Context context, IPullToRefreshCallBack refreshCallBack, View baseView) {
        baseInit(context, baseView, refreshCallBack);
        init(PullToRefreshBase.Mode.BOTH);
        startRequest();
    }

    /**
     * 当 listView 为上拉下拉自定义是调用的构造方法
     *
     * @param context         上下文
     * @param refreshCallBack 操作完成后的回调
     * @param baseView        布局
     * @param mode            上拉下拉方式
     */
    public RefreshListView(Context context, IPullToRefreshCallBack refreshCallBack, View baseView, PullToRefreshBase.Mode mode) {
        baseInit(context, baseView, refreshCallBack);
        init(mode);
        startRequest();
    }


    /**
     * 初始化数据
     *
     * @param setMode 上拉下拉方式
     */
    private void init(PullToRefreshBase.Mode setMode) {
        mPullRefreshListView = (PullToRefreshListView) baseView.findViewById(R.id.lv_pull_to_refresh);
        if (setMode == PullToRefreshBase.Mode.DISABLED) {
            mode = PullToRefreshBase.Mode.DISABLED;
        }
        mPullRefreshListView.setMode(setMode);
        mPullRefreshListView.setAdapter(refreshCallBack.getAdapter());
        mPullRefreshListView.setEmptyView(loadingPrompt.getPromptLayout());
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pullDownToRefresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pullUpToRefresh();
            }
        });
    }


    @Override
    public void requestFinish() {
        mPullRefreshListView.onRefreshComplete();
    }


    /**
     * 获取当前 ListView
     * @return
     */
    public PullToRefreshListView getmPullRefreshListView() {
        return mPullRefreshListView;
    }

}
