package com.common.dialog.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.common.R;
import com.common.dialog.Utils;
import com.common.utils.CornerUtils;
import com.common.dialog.internal.BaseAlertDialog;
import com.common.utils.ResUtils;


/**
 * Dialog like Material Design Dialog
 */
public class MaterialDialog extends BaseAlertDialog<MaterialDialog> {

    public MaterialDialog(Context context) {
        super(context);

        /** default value*/
        mTitleTextColor = Color.parseColor("#DE000000");
        mTitleTextSize = ResUtils.getDimens(R.dimen.common_dialog_title_size);
        mContentTextColor = Color.parseColor("#8a000000");
        mContentTextSize = ResUtils.getDimens(R.dimen.common_dialog_content_size);
        mLeftBtnTextColor = Color.parseColor("#383838");
        mRightBtnTextColor = Color.parseColor("#468ED0");
        mMiddleBtnTextColor = Color.parseColor("#00796B");
        /** default value*/
    }

    @Override
    public View onCreateView() {

        /** title */
        mTvTitle.setGravity(Gravity.CENTER_VERTICAL);
        mTvTitle.setPadding(Utils.dp2px(20), Utils.dp2px(20), Utils.dp2px(20), 0);
        mTvTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        mLlContainer.addView(mTvTitle);

        /** content */
        mTvContent.setPadding(Utils.dp2px(20), Utils.dp2px(20), Utils.dp2px(20), Utils.dp2px(20));
        mTvContent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        mLlContainer.addView(mTvContent);

        /**btns*/
        mLlBtns.setGravity(Gravity.RIGHT);
        mLlBtns.addView(mTvBtnLeft);
        mLlBtns.addView(mTvBtnMiddle);
        mLlBtns.addView(mTvBtnRight);

        mTvBtnLeft.setPadding(Utils.dp2px(15), Utils.dp2px(7), Utils.dp2px(15), Utils.dp2px(7));
        mTvBtnRight.setPadding(Utils.dp2px(15), Utils.dp2px(7), Utils.dp2px(15), Utils.dp2px(7));
        mTvBtnMiddle.setPadding(Utils.dp2px(15), Utils.dp2px(7), Utils.dp2px(15), Utils.dp2px(7));
        mLlBtns.setPadding(Utils.dp2px(20), 0, Utils.dp2px(15), Utils.dp2px(15));

        mLlContainer.addView(mLlBtns);

        return mLlContainer;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void setUiBeforShow() {
        super.setUiBeforShow();
        /**set background color and corner radius */
        float radius = mCornerRadius;
        mLlContainer.setBackground(CornerUtils.cornerDrawable(mBgColor, radius));
        mTvBtnLeft.setBackground(CornerUtils.btnSelector(radius, mBgColor, mBtnPressColor, -2));
        mTvBtnRight.setBackground(CornerUtils.btnSelector(radius, mBgColor, mBtnPressColor, -2));
        mTvBtnMiddle.setBackground(CornerUtils.btnSelector(radius, mBgColor, mBtnPressColor, -2));
    }
}
