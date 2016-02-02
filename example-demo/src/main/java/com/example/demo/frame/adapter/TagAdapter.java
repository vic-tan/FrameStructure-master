package com.example.demo.frame.adapter;

import android.content.Context;

import com.example.demo.R;
import com.common.adapter.base.CommonAdapter;
import com.common.adapter.base.ViewHolder;
import com.example.demo.frame.bean.TagBean;
import com.common.view.textview.expandable.ExpandableTextView;

import java.util.List;

/**
 * 所有图片
 *
 * @author tanlifei
 * @ClassName: XYQImageGridViewAdapter
 * @Description: 用一句话描述该文件做什么
 * @date 2015年3月4日 下午2:38:43
 */
public class TagAdapter extends CommonAdapter<TagBean> {

    public TagAdapter(Context context, List list) {
        super(context, list, R.layout.test_list_tag_item);
    }

    @Override
    public void convert(final ViewHolder holder,  final TagBean bean) {
        ((ExpandableTextView)holder.getView(R.id.expand_text_view)).setText(bean.getName());
    }


}
