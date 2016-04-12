package com.common.view.dialog.base.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.common.R;
import com.common.utils.CornerUtils;
import com.common.utils.StatusBarUtils;
import com.common.view.dialog.popu.TriangleView;
import com.common.view.dialog.internal.InternalBasePopup;
import com.nineoldandroids.view.ViewHelper;

/**
 * Use dialog to realize bubble style popup(利用Dialog实现泡泡样式的弹窗)
 * thanks https://github.com/michaelye/EasyDialog
 */
public abstract class BaseBubblePopup<T extends BaseBubblePopup<T>> extends InternalBasePopup<T> {
    protected View mWrappedView;
    protected LinearLayout mLlContent;
    protected TriangleView mTriangleView;
    protected RelativeLayout.LayoutParams mLayoutParams;
    protected int mBubbleColor;
    protected int mCornerRadius;
    protected int mMarginLeft;
    protected int mMarginRight;
    protected int triangleWidth;
    protected int triangleHeight;
    private RelativeLayout.LayoutParams mTriangleLayoutParams;

    public BaseBubblePopup(Context context) {
        super(context);
        mWrappedView = onCreateBubbleView();
        init();
    }

    public BaseBubblePopup(Context context, View wrappedView) {
        super(context);
        mWrappedView = wrappedView;
        init();
    }

    private void init() {
        //showAnim(new BounceLeftEnter());
        //dismissAnim(new FadeExit());
        dimEnabled(false);

        bubbleColor(Color.parseColor("#BB000000"));
        cornerRadius(5);
        margin(8, 8);
        gravity(Gravity.TOP);
        triangleWidth(24);
        triangleHeight(12);
    }

    public abstract View onCreateBubbleView();

    @Override
    public View onCreateView() {
        View inflate = View.inflate(mContext, R.layout.common_popup_bubble, null);
        mLlContent = (LinearLayout) inflate.findViewById(R.id.ll_content);
        mTriangleView = (TriangleView) inflate.findViewById(R.id.triangle_view);
        mLlContent.addView(mWrappedView);

        mLayoutParams = (RelativeLayout.LayoutParams) mLlContent.getLayoutParams();
        mTriangleLayoutParams = (RelativeLayout.LayoutParams) mTriangleView.getLayoutParams();
        //让mOnCreateView充满父控件,防止ViewHelper.setXY导致点击事件无效
        inflate.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        mLlContent.setBackgroundDrawable(
                CornerUtils.cornerDrawable(mBubbleColor, mCornerRadius));
        mLayoutParams.setMargins(mMarginLeft, 0, mMarginRight, 0);
        mLlContent.setLayoutParams(mLayoutParams);

        mTriangleView.setColor(mBubbleColor);
        mTriangleView.setGravity(mGravity == Gravity.TOP ? Gravity.BOTTOM : Gravity.TOP);

        mTriangleLayoutParams.width = triangleWidth;
        mTriangleLayoutParams.height = triangleHeight;
        mTriangleView.setLayoutParams(mTriangleLayoutParams);
    }

    @Override
    public void onLayoutObtain() {
        ViewHelper.setX(mTriangleView, mX - mTriangleView.getWidth() / 2);

        if (mGravity == Gravity.TOP) {
            int y = mY - mTriangleView.getHeight();
            ViewHelper.setY(mTriangleView, y);
            ViewHelper.setY(mLlContent, y - mLlContent.getHeight());
        } else {
            ViewHelper.setY(mTriangleView, mY);
            ViewHelper.setY(mLlContent, mY + mTriangleView.getHeight());
        }

        /**
         * mX--->三角形中心距离屏幕左边距离
         * mDisplayMetrics.widthPixels - mX--->三角形中心距离屏幕右边距离
         */
        int availableLeft = mX - mLayoutParams.leftMargin;//左侧最大可用距离
        int availableRight = mDisplayMetrics.widthPixels - mX - mLayoutParams.rightMargin;//右侧最大可用距离

        int x = 0;
        int contentWidth = mLlContent.getWidth();
        if (contentWidth / 2 <= availableLeft && contentWidth / 2 <= availableRight) {
            x = mX - contentWidth / 2;
        } else {
            if (availableLeft <= availableRight) {//三角形在屏幕中心的左边
                x = mLayoutParams.leftMargin;
            } else {//三角形在屏幕中心的右边
                x = mDisplayMetrics.widthPixels - (contentWidth + mLayoutParams.rightMargin);
            }
        }
        ViewHelper.setX(mLlContent, x);
    }

    @Override
    public T anchorView(View anchorView) {
        if (anchorView != null) {
            mAnchorView = anchorView;
            int[] location = new int[2];
            mAnchorView.getLocationOnScreen(location);

            mX = location[0] + anchorView.getWidth() / 2;
            if (mGravity == Gravity.TOP) {
                mY = location[1] - StatusBarUtils.getHeight(mContext)
                        - 2;
            } else {
                mY = location[1] - StatusBarUtils.getHeight(mContext)
                        + anchorView.getHeight() + 2;
            }
        }
        return (T) this;
    }

    public T bubbleColor(int bubbleColor) {
        mBubbleColor = bubbleColor;
        return (T) this;
    }

    public T cornerRadius(int cornerRadius) {
        mCornerRadius = cornerRadius;
        return (T) this;
    }

    public T margin(int marginLeft, int marginRight) {
        mMarginLeft = marginLeft;
        mMarginRight = marginRight;
        return (T) this;
    }

    public T triangleWidth(int width) {
        triangleWidth = width;
        return (T) this;
    }

    public T triangleHeight(int height) {
        triangleHeight = height;
        return (T) this;
    }
}
