package com.example.demo.frame.fragmentnavigator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo.R;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by aspsine on 16/4/1.
 */
public class TabLayout extends AutoLinearLayout {

    private OnTabItemClickListener mOnTabItemClickListener;

    public interface OnTabItemClickListener {
        void onTabItemClick(int position, View view);
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        inflate(context, R.layout.fragmentnavigator_layout_tab, this);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnTabItemClickListener.onTabItemClick(finalI, v);
                }
            });
        }
    }

    public void setOnTabItemClickListener(OnTabItemClickListener listener) {
        this.mOnTabItemClickListener = listener;
    }

    public void select(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (i == position) {
                selectChild(child, true);
            } else {
                selectChild(child, false);
            }
        }
    }

    private void selectChild(View child, boolean select) {
        if (child instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) child;
            group.setSelected(select);
            for (int i = 0; i < group.getChildCount(); i++) {
                selectChild(group.getChildAt(i), select);
            }
        } else {
            child.setSelected(select);
        }
    }

}
