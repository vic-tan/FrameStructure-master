package com.common.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 一个布局的adapter 基类
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public abstract class CommonAdapter<T> extends BaseAdapter implements AbsListView.OnScrollListener {

    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected boolean isScrolling = false;//当前listview是否属于滚动状态
    private int layoutId;
    private AbsListView.OnScrollListener listener;

    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.layoutId = layoutId;
    }

    /**
     * 需要监听滚动时
     *
     * @param context
     * @param view
     * @param datas
     * @param layoutId
     */
    public CommonAdapter(AbsListView view, Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.layoutId = layoutId;
        view.setOnScrollListener(this);
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent,
                layoutId, position);
        convert(holder, getItem(position), isScrolling);
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T bean, boolean isScrolling);

    public boolean isScrolling() {
        return isScrolling;
    }


    /**
     * 外部可扩展的OnScrollListener
     *
     * @param l
     */
    public void addOnScrollListener(AbsListView.OnScrollListener l) {
        this.listener = l;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // 设置是否滚动的状态
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            isScrolling = false;
            this.notifyDataSetChanged();
        } else {
            isScrolling = true;
        }
        if (listener != null) {
            listener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (listener != null) {
            listener.onScroll(view, firstVisibleItem, visibleItemCount,
                    totalItemCount);
        }
    }

}
