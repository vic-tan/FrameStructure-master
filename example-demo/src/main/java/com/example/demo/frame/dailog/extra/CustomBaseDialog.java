package com.example.demo.frame.dailog.extra;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.common.view.dialog.base.dialog.BaseDialog;
import com.common.utils.CornerUtils;
import com.common.utils.ViewFindUtils;
import com.example.demo.R;


public class CustomBaseDialog extends BaseDialog<CustomBaseDialog> {
    private TextView mTvCancel;
    private TextView mTvExit;

    public CustomBaseDialog(Context context) {
        super(context);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView() {
        widthScale(0.85f);
        //showAnim(new Swing());

        // dismissAnim(this, new ZoomOutExit());
        View inflate = View.inflate(mContext, R.layout.dialog_custom_base, null);
        mTvCancel = ViewFindUtils.find(inflate, R.id.tv_cancel);
        mTvExit = ViewFindUtils.find(inflate, R.id.tv_exit);
        inflate.setBackground(
                CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), 10));

        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
