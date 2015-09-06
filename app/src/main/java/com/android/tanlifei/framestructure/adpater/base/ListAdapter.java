package com.android.tanlifei.framestructure.adpater.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.tanlifei.framestructure.common.utils.ListUtils;

import java.util.List;


/**
 * BaseAdpter 基本实现
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public abstract class ListAdapter extends BaseAdapter {

    protected List<?> list;
    protected Context context;

    public ListAdapter(Context context, List<?> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return ListUtils.isEmpty(list) ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return ListUtils.isEmpty(list) ? 0 : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ListUtils.isEmpty(list) ? 0 : position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View localView = inflate();
        return localView;
    }

    public abstract View inflate();

}
