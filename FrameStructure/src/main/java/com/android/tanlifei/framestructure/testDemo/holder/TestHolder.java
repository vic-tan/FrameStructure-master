package com.android.tanlifei.framestructure.testDemo.holder;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.common.view.textview.ExpandableTextView;
import com.android.tanlifei.framestructure.testDemo.bean.TestBean;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.test_list_item)
public class TestHolder extends LinearLayout{

    @ViewById(R.id.tv_name)
    TextView name;
    @ViewById(R.id.expand_text_view)
    ExpandableTextView desc;

    public TestHolder(Context context) {
        super(context);
    }

    public void bind(TestBean bean) {
        name.setText(bean.getName());
        desc.setText(bean.getDesc());
    }
}
