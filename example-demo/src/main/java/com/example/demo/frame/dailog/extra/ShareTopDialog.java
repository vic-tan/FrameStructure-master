package com.example.demo.frame.dailog.extra;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.common.dialog.base.dialog.TopBaseDialog;
import com.common.utils.ToastUtils;
import com.common.utils.ViewFindUtils;
import com.example.demo.R;


public class ShareTopDialog extends TopBaseDialog<ShareTopDialog> {
    private LinearLayout mLlWechatFriendCircle;
    private LinearLayout mLlWechatFriend;
    private LinearLayout mLlQq;
    private LinearLayout mLlSms;

    public ShareTopDialog(Context context, View animateView) {
        super(context, animateView);
    }

    public ShareTopDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        //showAnim(new FlipVerticalSwingEnter());
        dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.dialog_share, null);
        mLlWechatFriendCircle = ViewFindUtils.find(inflate, R.id.ll_wechat_friend_circle);
        mLlWechatFriend = ViewFindUtils.find(inflate, R.id.ll_wechat_friend);
        mLlQq = ViewFindUtils.find(inflate, R.id.ll_qq);
        mLlSms = ViewFindUtils.find(inflate, R.id.ll_sms);

        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        mLlWechatFriendCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext, "朋友圈");
                dismiss();
            }
        });
        mLlWechatFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext, "微信");
                dismiss();
            }
        });
        mLlQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext, "QQ");
                dismiss();
            }
        });
        mLlSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext, "短信");
                dismiss();
            }
        });
    }
}
