package com.example.demo.frame.dailog.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.common.dialog.base.popup.BubblePopup;
import com.common.ui.base.activity.BaseActivity;
import com.common.utils.ToastUtils;
import com.common.utils.ViewFindUtils;
import com.example.demo.R;
import com.example.demo.frame.dailog.extra.CustomBubblePopup;


public class BubblePopupActivity extends BaseActivity implements View.OnClickListener{
    TextView mTvTopLeft;
    TextView mTvTopRight;
    TextView mTvBottomLeft;
    TextView mTvBottomRight;
    TextView mTvCenter;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_bubble_popup);
        mTvTopLeft = (TextView) ViewFindUtils.find(this, R.id.tv_top_left);
        mTvTopRight = (TextView) ViewFindUtils.find(this, R.id.tv_top_right);
        mTvBottomLeft = (TextView) ViewFindUtils.find(this, R.id.tv_bottom_left);
        mTvBottomRight = (TextView) ViewFindUtils.find(this, R.id.tv_bottom_right);
        mTvCenter = (TextView) ViewFindUtils.find(this, R.id.tv_center);

        mTvTopLeft.setOnClickListener(this);
        mTvTopRight.setOnClickListener(this);
        mTvBottomLeft.setOnClickListener(this);
        mTvBottomRight.setOnClickListener(this);
        mTvCenter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_center){
            clickCenterBtn();
        }else if(v.getId() == R.id.tv_top_left){
            clickTopLeftBtn();
        }else if(v.getId() == R.id.tv_top_right){
            clickTopRightBtn();
        }else if(v.getId() == R.id.tv_bottom_left){
            clickBottomLeftBtn();
        }else if(v.getId() == R.id.tv_bottom_right){
            clickBottomRightBtn();
        }
    }

    void clickCenterBtn() {
        View inflate = View.inflate(mContext, R.layout.popup_bubble_image, null);
        BubblePopup bubblePopup = new BubblePopup(mContext, inflate);
        bubblePopup.anchorView(mTvCenter)
                .autoDismiss(true)
                .show();
    }

    void clickTopLeftBtn() {
        View inflate = View.inflate(mContext, R.layout.popup_bubble_text, null);
        TextView tv = (TextView) ViewFindUtils.find(inflate, R.id.tv_bubble);
        BubblePopup bubblePopup = new BubblePopup(mContext, inflate);
        tv.setText("最美的不是下雨天,是曾与你躲过雨的屋檐~");
        bubblePopup.anchorView(mTvTopLeft)
                .gravity(Gravity.BOTTOM)
                .show();

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show( "tv_bubble");
            }
        });
    }

    void clickTopRightBtn() {
        CustomBubblePopup customBubblePopup = new CustomBubblePopup(mContext);
//        customBubblePopup.setCanceledOnTouchOutside(false);
        customBubblePopup
                .gravity(Gravity.BOTTOM)
                .anchorView(mTvTopRight)
                .triangleWidth(20)
                .triangleHeight(10)
                .showAnim(null)
                .dismissAnim(null)
                .show();
    }

    void clickBottomLeftBtn() {
        View inflate = View.inflate(mContext, R.layout.popup_bubble_text, null);
        new BubblePopup(mContext, inflate)
                .anchorView(mTvBottomLeft)
                .showAnim(null)
                .dismissAnim(null)
                .show();
    }

    void clickBottomRightBtn() {
        View inflate = View.inflate(mContext, R.layout.popup_bubble_image, null);
        new BubblePopup(mContext, inflate).anchorView(mTvBottomRight)
                .bubbleColor(Color.parseColor("#8BC34A"))
                .show();
    }


}
