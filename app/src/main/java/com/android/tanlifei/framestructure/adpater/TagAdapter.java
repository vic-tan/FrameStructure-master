package com.android.tanlifei.framestructure.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.adpater.base.ListAdapter;
import com.android.tanlifei.framestructure.bean.TagBean;
import com.android.tanlifei.framestructure.common.utils.InflaterUtils;

import java.util.List;

/**
 * 所有图片
 *
 * @author tanlifei
 * @ClassName: XYQImageGridViewAdapter
 * @Description: 用一句话描述该文件做什么
 * @date 2015年3月4日 下午2:38:43
 */
public class TagAdapter extends ListAdapter {

    public TagAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = super.getView(position, convertView, parent);
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.name.setText(getBean(position).getName());
        return convertView;
    }

    private TagBean getBean(int position) {
        return (TagBean) list.get(position);
    }

    @Override
    public View inflate() {
        return InflaterUtils.inflater(context,
                R.layout.list_tag_item);
    }

    static class Holder {
        private TextView name;
    }

}
