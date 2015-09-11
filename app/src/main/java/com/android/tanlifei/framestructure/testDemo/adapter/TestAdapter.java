package com.android.tanlifei.framestructure.testDemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.adpater.base.ListAdapter;
import com.android.tanlifei.framestructure.common.utils.InflaterUtils;
import com.android.tanlifei.framestructure.testDemo.bean.TestBean;

import java.util.List;

/**
 * 所有图片
 *
 * @author tanlifei
 * @ClassName: XYQImageGridViewAdapter
 * @Description: 用一句话描述该文件做什么
 * @date 2015年3月4日 下午2:38:43
 */
public class TestAdapter extends ListAdapter {

    public TestAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = super.getView(position, convertView, parent);
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.desc = (TextView) convertView.findViewById(R.id.tv_desc);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.name.setText(getBean(position).getName());
        holder.desc.setText(getBean(position).getDesc());
        return convertView;
    }

    private TestBean getBean(int position) {
        return (TestBean) list.get(position);
    }

    @Override
    public View inflate() {
        return InflaterUtils.inflater(context,
                R.layout.test_list_item);
    }

    static class Holder {
        private TextView name;
        private TextView desc;
    }

}
