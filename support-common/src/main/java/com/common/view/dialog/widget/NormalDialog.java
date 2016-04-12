package com.common.view.dialog.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.R;
import com.common.view.dialog.internal.BaseAlertDialog;
import com.common.utils.CornerUtils;
import com.common.utils.InflaterUtils;
import com.common.utils.ResUtils;
import com.common.utils.ViewFindUtils;
import com.zhy.autolayout.AutoLinearLayout;


@SuppressWarnings("deprecation")
public class NormalDialog extends BaseAlertDialog<NormalDialog> {

    /**
     * title underline
     */
    private View mVLineTitle;
    /**
     * vertical line between btns
     */
    private View mVLineVertical;
    /**
     * vertical line between btns
     */
    private View mVLineVertical2;
    /**
     * horizontal line above btns
     */
    private View mVLineHorizontal;
    /**
     * title underline color(标题下划线颜色)
     */
    private int mTitleLineColor = ResUtils.getColor(R.color.common_prompt_dialog_title_line_color);
    /**
     * title underline height(标题下划线高度)
     */
    private float mTitleLineHeight = (int) ResUtils.getDimens(R.dimen.common_prompt_dialog_title_btn_split_line_size);

    /**
     * btn divider line color(对话框之间的分割线颜色(水平+垂直))
     */
    private int mDividerColor = ResUtils.getColor(R.color.common_prompt_dialog_content_btn_line_color);




    public NormalDialog(Context context) {
        super(context);
        /** default value*/
        mTitleTextColor = Color.parseColor("#61AEDC");
        mTitleTextSize = ResUtils.getDimens(R.dimen.common_dialog_title_size);
        mContentTextColor = Color.parseColor("#383838");
        mContentTextSize = ResUtils.getDimens(R.dimen.common_dialog_content_size);
        mLeftBtnTextColor = Color.parseColor("#8a000000");
        mRightBtnTextColor = Color.parseColor("#8a000000");
        mMiddleBtnTextColor = Color.parseColor("#8a000000");
        /** default value*/
    }

    @Override
    public View onCreateView() {
        mLlContainer = (AutoLinearLayout) InflaterUtils.inflater(mContext, R.layout.common_dialog_normal);
        /** title */
        mTvTitle = ViewFindUtils.find(mLlContainer, R.id.mTvTitle);
        mVLineTitle = ViewFindUtils.find(mLlContainer, R.id.mVLineTitle);

        /** content */
        mTvContent = ViewFindUtils.find(mLlContainer, R.id.mTvContent);
        mVLineHorizontal = mLlContainer.findViewById(R.id.mVLineHorizontal);
        /**btns*/
        mTvBtnLeft = ViewFindUtils.find(mLlContainer, R.id.mTvBtnLeft);
        mTvBtnMiddle =ViewFindUtils.find(mLlContainer, R.id.mTvBtnMiddle);
        mTvBtnRight = (TextView) mLlContainer.findViewById(R.id.mTvBtnRight);

        mVLineVertical = ViewFindUtils.find(mLlContainer, R.id.mVLineVertical);
        mVLineVertical2 = ViewFindUtils.find(mLlContainer, R.id.mVLineVertical2);


        return mLlContainer;
    }

    @Override
    public void setUiBeforShow() {
        super.setUiBeforShow();
        /** title underline */
        mVLineTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,(int)mTitleLineHeight));
        mVLineTitle.setBackgroundColor(mTitleLineColor);
        mVLineTitle.setVisibility(mIsTitleShow ? View.VISIBLE : View.GONE);

        /** btns */
        mVLineHorizontal.setBackgroundColor(mDividerColor);
        mVLineVertical.setBackgroundColor(mDividerColor);
        mVLineVertical2.setBackgroundColor(mDividerColor);

        if (mBtnNum == 1) {
            mTvBtnLeft.setVisibility(View.GONE);
            mTvBtnRight.setVisibility(View.GONE);
            mVLineVertical.setVisibility(View.GONE);
            mVLineVertical2.setVisibility(View.GONE);
        } else if (mBtnNum == 2) {
            mTvBtnMiddle.setVisibility(View.GONE);
            mVLineVertical.setVisibility(View.GONE);
        }

        /**set background color and corner radius */
        mTvBtnLeft.setBackgroundDrawable(CornerUtils.btnSelector(mCornerRadius, mBgColor, mBtnPressColor, 0));
        mTvBtnRight.setBackgroundDrawable(CornerUtils.btnSelector(mCornerRadius, mBgColor, mBtnPressColor, 1));
        mTvBtnMiddle.setBackgroundDrawable(CornerUtils.btnSelector(mBtnNum == 1 ? mCornerRadius : 0, mBgColor, mBtnPressColor, -1));
    }



    /**
     * set title underline color(设置标题下划线颜色)
     */
    public NormalDialog titleLineColor(int titleLineColor) {
        this.mTitleLineColor = titleLineColor;
        return this;
    }

    /**
     * set title underline height(设置标题下划线高度)
     */
    public NormalDialog titleLineHeight(float titleLineHeight_PX) {
        this.mTitleLineHeight = titleLineHeight_PX;
        return this;
    }

    /**
     * set divider color between btns(设置btn分割线的颜色)
     */
    public NormalDialog dividerColor(int dividerColor) {
        this.mDividerColor = dividerColor;
        return this;
    }
}
