package com.common.dialog.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.common.R;
import com.common.dialog.base.dialog.BottomBaseDialog;
import com.common.dialog.bean.DialogMenuItem;
import com.common.dialog.listener.OnOperItemClickL;
import com.common.utils.CornerUtils;
import com.common.utils.InflaterUtils;
import com.common.utils.ResUtils;
import com.common.utils.ViewFindUtils;
import com.constants.fixed.GlobalConstants;

import java.util.ArrayList;

/**
 * Dialog like iOS ActionSheet(iOS风格对话框)
 */
public class ActionSheetDialog extends BottomBaseDialog<ActionSheetDialog> {
    /** ListView */
    private ListView mLv;
    /** title */
    private TextView mTvTitle;
    /** title underline(标题下划线) */
    private View mVLineTitle;
    /** mCancel button(取消按钮) */
    private TextView mTvCancel;
    /** corner radius,dp(圆角程度,单位dp) */
    private float mCornerRadius = ResUtils.getDimens(R.dimen.common_border_radius);
    /** title background color(标题背景颜色) */
    private int mTitleBgColor = Color.parseColor("#ddffffff");
    /** title text(标题) */
    private String mTitle = "提示";
    /** title height(标题栏高度) */
    //private int mTitleHeight = (int)ResUtils.getDimens(R.dimen.common_dialog_action_sheet_height);
    /** title textcolor(标题颜色) */
    private int mTitleTextColor = Color.parseColor("#8F8F8F");
    /** title textsize(标题字体大小,单位sp) */
    private int mTitleTextSize = (int)ResUtils.getDimens(R.dimen.common_dialog_content_size);
    /** ListView background color(ListView背景色) */
    private int mLvBgColor = Color.parseColor("#ddffffff");
    /** divider color(ListView divider颜色) */
    private int mDividerColor = Color.parseColor("#D7D7D9");
    /** divider height(ListView divider高度) */
    private int mDividerHeight = (int)ResUtils.getDimens(R.dimen.common_dialog_title_split_line_size);
    /** item press color(ListView item按住颜色) */
    private int mItemPressColor = Color.parseColor("#ffcccccc");
    /** item textcolor(ListView item文字颜色) */
    private int mItemTextColor = Color.parseColor("#44A2FF");
    /** item textsize(ListView item文字大小) */
    private int mItemTextSize = (int)ResUtils.getDimens(R.dimen.common_dialog_content_size);
    /** item height(ListView item高度) */
    //private int mItemHeight = (int)ResUtils.getDimens(R.dimen.common_dialog_action_sheet_height);
    /** enable title show(是否显示标题) */
    private boolean mIsTitleShow = true;
    /*** cancel btn text(取消按钮内容) */
    private String mCancelText = "取消";
    /** cancel btn text color(取消按钮文字颜色) */
    private int mCancelTextColor = Color.parseColor("#44A2FF");
    /** cancel btn text size(取消按钮文字大小) */
    private int mCancelTextSize = (int)ResUtils.getDimens(R.dimen.common_dialog_content_size);
    /** adapter(自定义适配器) */
    private BaseAdapter mAdapter;
    /** operation items(操作items) */
    private ArrayList<DialogMenuItem> mContents = new ArrayList<>();
    private OnOperItemClickL mOnOperItemClickL;
    private LayoutAnimationController mLac;

    public void setOnOperItemClickL(OnOperItemClickL onOperItemClickL) {
        mOnOperItemClickL = onOperItemClickL;
    }

    public ActionSheetDialog(Context context, ArrayList<DialogMenuItem> baseItems, View animateView) {
        super(context, animateView);
        mContents.addAll(baseItems);
        init();
    }

    public ActionSheetDialog(Context context, String[] items, View animateView) {
        super(context, animateView);
        mContents = new ArrayList<>();
        for (String item : items) {
            DialogMenuItem customBaseItem = new DialogMenuItem(item, 0);
            mContents.add(customBaseItem);
        }
        init();
    }

    public ActionSheetDialog(Context context, BaseAdapter adapter, View animateView) {
        super(context, animateView);
        mAdapter = adapter;
        init();
    }

    private void init() {
        widthScale(GlobalConstants.DIALOG_ACTION_SHEET_DIALOG_SCALE);
        /** LayoutAnimation */
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
                0f, Animation.RELATIVE_TO_SELF, 6f, Animation.RELATIVE_TO_SELF, 0);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setDuration(350);
        animation.setStartOffset(150);

        mLac = new LayoutAnimationController(animation, 0.12f);
        mLac.setInterpolator(new DecelerateInterpolator());
    }

    @Override
    public View onCreateView() {
        View ll_container = InflaterUtils.inflater(mContext, R.layout.common_dialog_action_sheet);
        /** title */
        mTvTitle = ViewFindUtils.find(ll_container, R.id.mTvTitle);
        /** listview */
        mLv = ViewFindUtils.find(ll_container,R.id.mlist);


        /** title */
        /*mTvTitle = new TextView(mContext);
        mTvTitle.setGravity(Gravity.CENTER);
        mTvTitle.setPadding(20, 10, 20, 10);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.topMargin = 40;

        ll_container.addView(mTvTitle, params);*/

        /** title underline */
        mVLineTitle = ViewFindUtils.find(ll_container, R.id.mVLineTitle);
        //ll_container.addView(mVLineTitle);

        /** listview */
        /*mLv = new ListView(mContext);
        mLv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));
        mLv.setCacheColorHint(Color.TRANSPARENT);
        mLv.setFadingEdgeLength(0);
        mLv.setVerticalScrollBarEnabled(false);
        mLv.setSelector(new ColorDrawable(Color.TRANSPARENT));

        ll_container.addView(mLv);*/

        /** mCancel btn */
        mTvCancel = ViewFindUtils.find(ll_container, R.id.mTvCancel);
        /*mTvCancel.setGravity(Gravity.CENTER);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.topMargin = 14;
        lp.bottomMargin = 14;
        mTvCancel.setLayoutParams(lp);

        ll_container.addView(mTvCancel);*/

        return ll_container;
    }

    @Override
    public void setUiBeforShow() {
        /** title */
        float radius = mCornerRadius;
        //mTvTitle.setHeight(mTitleHeight);
        mTvTitle.setBackgroundDrawable(CornerUtils.cornerDrawable(mTitleBgColor, new float[]{radius, radius, radius,
                radius, 0, 0, 0, 0}));
        mTvTitle.setText(mTitle);
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleTextSize);
        mTvTitle.setTextColor(mTitleTextColor);
        mTvTitle.setVisibility(mIsTitleShow ? View.VISIBLE : View.GONE);

        /** title underline */
        mVLineTitle.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, mDividerHeight));
        mVLineTitle.setBackgroundColor(mDividerColor);
        mVLineTitle.setVisibility(mIsTitleShow ? View.VISIBLE : View.GONE);

        /** mCancel btn */
        //mTvCancel.setHeight(mItemHeight);
        mTvCancel.setText(mCancelText);
        mTvCancel.setTextSize(TypedValue.COMPLEX_UNIT_PX, mCancelTextSize);
        mTvCancel.setTextColor(mCancelTextColor);
        mTvCancel.setBackgroundDrawable(CornerUtils.listItemSelector(radius, mLvBgColor, mItemPressColor, 1, 0));

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        /** listview */
        mLv.setDivider(new ColorDrawable(mDividerColor));
        mLv.setDividerHeight(mDividerHeight);

        if (mIsTitleShow) {
            mLv.setBackgroundDrawable(CornerUtils.cornerDrawable(mLvBgColor, new float[]{0, 0, 0, 0, radius, radius, radius,
                    radius}));
        } else {
            mLv.setBackgroundDrawable(CornerUtils.cornerDrawable(mLvBgColor, radius));
        }

        if (mAdapter == null) {
            mAdapter = new ListDialogAdapter();
        }

        mLv.setAdapter(mAdapter);
        mLv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mOnOperItemClickL != null) {
                    mOnOperItemClickL.onOperItemClick(parent, view, position, id);
                }
            }
        });

        mLv.setLayoutAnimation(mLac);
    }

    /** set title background color(设置标题栏背景色) */
    public ActionSheetDialog titleBgColor(int titleBgColor) {
        mTitleBgColor = titleBgColor;
        return this;
    }

    /** set title text(设置标题内容) */
    public ActionSheetDialog title(String title) {
        mTitle = title;
        return this;
    }

    /** set titleHeight(设置标题高度) */
    /*public ActionSheetDialog titleHeight(int titleHeight) {
        mTitleHeight = titleHeight;
        return this;
    }*/

    /** set title textsize(设置标题字体大小) */
    public ActionSheetDialog titleTextSize_PX(int titleTextSize_PX) {
        mTitleTextSize = titleTextSize_PX;
        return this;
    }

    /** set title textcolor(设置标题字体颜色) */
    public ActionSheetDialog titleTextColor(int titleTextColor) {
        mTitleTextColor = titleTextColor;
        return this;
    }

    /** enable title show(设置标题是否显示) */
    public ActionSheetDialog isTitleShow(boolean isTitleShow) {
        mIsTitleShow = isTitleShow;
        return this;
    }

    /** set ListView background color(设置ListView背景) */
    public ActionSheetDialog lvBgColor(int lvBgColor) {
        mLvBgColor = lvBgColor;
        return this;
    }

    /** set corner radius(设置圆角程度,单位dp) */
    public ActionSheetDialog cornerRadius(float cornerRadius_PX) {
        mCornerRadius = cornerRadius_PX;
        return this;
    }

    /** set divider color(ListView divider颜色) */
    public ActionSheetDialog dividerColor(int dividerColor) {
        mDividerColor = dividerColor;
        return this;
    }

    /** set divider height(ListView divider高度) */
    public ActionSheetDialog dividerHeight(int dividerHeight_PX) {
        mDividerHeight = dividerHeight_PX;
        return this;
    }

    /** set item press color(item按住颜色) */
    public ActionSheetDialog itemPressColor(int itemPressColor) {
        mItemPressColor = itemPressColor;
        return this;
    }

    /** set item textcolor(item字体颜色)* @return ActionSheetDialog */
    public ActionSheetDialog itemTextColor(int itemTextColor) {
        mItemTextColor = itemTextColor;
        return this;
    }

    /** set item textsize(item字体大小) */
    public ActionSheetDialog itemTextSize(int itemTextSize_PX) {
        mItemTextSize = itemTextSize_PX;
        return this;
    }

    /** set item height(item高度) */
    /*public ActionSheetDialog itemHeight(int itemHeight_PX) {
        mItemHeight = itemHeight_PX;
        return this;
    }*/

    /** set layoutAnimation(设置layout动画 ,传入null将不显示layout动画) */
    public ActionSheetDialog layoutAnimation(LayoutAnimationController lac) {
        mLac = lac;
        return this;
    }

    /** set cancel btn text(设置取消按钮内容) */
    public ActionSheetDialog cancelText(String cancelText) {
        mCancelText = cancelText;
        return this;
    }

    /** cancel btn text color(取消按钮文字颜色) */
    public ActionSheetDialog cancelText(int cancelTextColor) {
        mCancelTextColor = cancelTextColor;
        return this;
    }

    /** cancel btn text size(取消按钮文字大小) */
    public ActionSheetDialog cancelTextSize(int cancelTextSize) {
        mCancelTextSize = cancelTextSize;
        return this;
    }

    class ListDialogAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mContents.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final DialogMenuItem item = mContents.get(position);

            View llItem = InflaterUtils.inflater(mContext,R.layout.common_dialog_normal_list_item);

            ImageView ivItem = ViewFindUtils.find(llItem,R.id.ivItem);

            TextView tvItem = ViewFindUtils.find(llItem,R.id.tvItem);
            tvItem.setTextColor(mItemTextColor);
            tvItem.setGravity(Gravity.CENTER);
            tvItem.setTextSize(TypedValue.COMPLEX_UNIT_PX, mItemTextSize);
            if (mIsTitleShow) {
                llItem.setBackgroundDrawable((CornerUtils.listItemSelector(mCornerRadius, Color.TRANSPARENT, mItemPressColor,
                        position == mContents.size() - 1)));
            } else {
                llItem.setBackgroundDrawable(CornerUtils.listItemSelector(mCornerRadius, Color.TRANSPARENT, mItemPressColor,
                        mContents.size(), position));
            }



            ivItem.setImageResource(item.mResId);
            tvItem.setText(item.mOperName);
            ivItem.setVisibility(item.mResId == 0 ? View.GONE : View.VISIBLE);

            return llItem;


        }
    }
}
