package com.android.tanlifei.framestructure.testDemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.adpater.base.ListAdapter;
import com.android.tanlifei.framestructure.common.utils.InflaterUtils;
import com.android.tanlifei.framestructure.common.view.textview.ExpandableTextView;
import com.android.tanlifei.framestructure.testDemo.bean.TestBean;
import com.android.tanlifei.framestructure.testDemo.holder.TestHolder;
import com.android.tanlifei.framestructure.testDemo.holder.TestHolder_;

import org.androidannotations.annotations.EView;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

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
        TestHolder holder;
        if (convertView == null) {
            holder = TestHolder_.build(context);
        } else {
            holder = (TestHolder) convertView;
        }
        holder.bind((TestBean)getItem(position));
        return holder;
    }


    @Override
    public View inflate() {
        return null;
    }

}
