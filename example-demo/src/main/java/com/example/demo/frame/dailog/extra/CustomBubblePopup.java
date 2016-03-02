package com.example.demo.frame.dailog.extra;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.dialog.base.popup.BaseBubblePopup;
import com.common.utils.ToastUtils;
import com.example.demo.R;


public class CustomBubblePopup extends BaseBubblePopup<CustomBubblePopup> {

    private ImageView mIvBubble;
    private TextView mTvBubble;

    public CustomBubblePopup(Context context) {
        super(context);
    }

    @Override
    public View onCreateBubbleView() {
        View inflate = View.inflate(mContext, R.layout.popup_bubble_image, null);
        mTvBubble = (TextView) inflate.findViewById(R.id.tv_bubble);
        mIvBubble = (ImageView) inflate.findViewById(R.id.iv_bubble);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        super.setUiBeforShow();

        mTvBubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext, "mTvBubble--->");
            }
        });
        mIvBubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext, "mIvBubble--->");
            }
        });
    }
}
