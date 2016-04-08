package com.common.dialog.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

import com.common.R;
import com.common.dialog.internal.BaseAlertDialog;
import com.common.utils.CornerUtils;
import com.common.utils.InflaterUtils;
import com.common.utils.ResUtils;
import com.common.utils.ViewFindUtils;
import com.zhy.autolayout.AutoLinearLayout;


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

        mLlContainer = (AutoLinearLayout) InflaterUtils.inflater(mContext, R.layout.common_dialog_material);
        /** title */
        mTvTitle = ViewFindUtils.find(mLlContainer, R.id.mTvTitle);

        /** content */
        mTvContent = ViewFindUtils.find(mLlContainer, R.id.mTvContent);

        /**btns*/
        mTvBtnLeft = ViewFindUtils.find(mLlContainer, R.id.mTvBtnLeft);
        mTvBtnMiddle =ViewFindUtils.find(mLlContainer, R.id.mTvBtnMiddle);
        mTvBtnRight = ViewFindUtils.find(mLlContainer, R.id.mTvBtnRight);

        return mLlContainer;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void setUiBeforShow() {
        super.setUiBeforShow();
        /**set background color and corner radius */
        mTvBtnLeft.setBackground(CornerUtils.btnSelector(mCornerRadius, mBgColor, mBtnPressColor, -2));
        mTvBtnRight.setBackground(CornerUtils.btnSelector(mCornerRadius, mBgColor, mBtnPressColor, -2));
        mTvBtnMiddle.setBackground(CornerUtils.btnSelector(mCornerRadius, mBgColor, mBtnPressColor, -2));
    }
}
