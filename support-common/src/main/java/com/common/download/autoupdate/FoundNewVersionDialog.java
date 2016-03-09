package com.common.download.autoupdate;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.common.R;
import com.common.dialog.base.dialog.BaseDialog;
import com.common.utils.CornerUtils;
import com.common.utils.ResUtils;
import com.common.utils.ViewFindUtils;
import com.zhy.autolayout.utils.AutoUtils;


/**
 * 自定义发现新的版本的提示框
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public abstract class FoundNewVersionDialog extends BaseDialog<FoundNewVersionDialog> {
    private TextView mTvOk,mTvExit;
    private TextView mTvContent;

    public FoundNewVersionDialog(Context context) {
        super(context);
    }


    @Override
    public View onCreateView() {
        widthScale(0.85f);
        View inflate = View.inflate(mContext, R.layout.common_autoupdate_found_new_version, null);
        AutoUtils.autoSize(inflate);
        mTvContent = ViewFindUtils.find(inflate,R.id.tv_content);
       // mTvContent.setTextSize(dp2px(7));
        mTvOk = ViewFindUtils.find(inflate, R.id.tv_ok);
        mTvExit = ViewFindUtils.find(inflate, R.id.tv_exit);
        setSelector(mTvOk);
        setSelector(mTvExit);
        inflate.setBackground(CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), dp2px(5)));
        return inflate;
    }

    public void setSelector(TextView v){
        v.setBackground(CornerUtils.btnSelector((int) ResUtils.getDimens(R.dimen.common_border_radius), Color.parseColor("#ffffff"), Color.parseColor("#E3E3E3"), -2));
    }



    public TextView getmTvOk() {
        return mTvOk;
    }

    public TextView getmTvContent() {
        return mTvContent;
    }

    public TextView getmTvExit() {
        return mTvExit;
    }
}
