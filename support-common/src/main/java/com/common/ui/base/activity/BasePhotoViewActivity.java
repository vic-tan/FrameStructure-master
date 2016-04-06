package com.common.ui.base.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.common.R;
import com.common.ui.base.main.BaseApplication;
import com.common.utils.ImageLoaderUtils;
import com.common.utils.InflaterUtils;
import com.common.utils.ListUtils;
import com.common.utils.NetUtils;
import com.common.utils.ResUtils;
import com.common.utils.StartActUtils;
import com.common.utils.StringUtils;
import com.common.utils.ToastUtils;
import com.common.utils.ViewFindUtils;
import com.common.view.imageview.SmoothImageView;
import com.common.view.viewpager.HackyViewPager;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 查看图片基类
 *
 * @author tanlifei
 * @Description: 用一句话描述该文件做什么
 * @date 2015年2月28日 下午3:42:37
 */
//TODO 1、返回回到原点。2、返回时有一个闪图的出现。3、如果有GridView 有边距时返回不能跟原图边上对齐回去。4、活用一行显示多少张图片，边距。 5、返回时当前界面没有时处理
public abstract class BasePhotoViewActivity extends BaseActivity {

    private View baseView;
    private ViewPager mViewPager;
    private TextView index;
    private boolean isAima = false;//是不是已经动画过了，这个标识用来page多张滑动时只有点开第一张有动画，滑动时不在有动画
    private int numColumns = 3;//每行有多少张图片，用来计算page多张滑动后返回计算位置
    private int currentColumns = 0;//点击图片所在的行数
    private int currentColumnsIndex = 0;//点击图片的在的行数的位置
    private int verticalSpacing = (int) ResUtils.getDimens(R.dimen.common_photo_gridview_vertical_spacing);//
    private int horizontalSpacing = (int) ResUtils.getDimens(R.dimen.common_photo_gridview_horizontal_spacing);//
    private AutoRelativeLayout frameRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        baseView = InflaterUtils.inflater(this, R.layout.common_pager_photo_view);
        frameRoot = (AutoRelativeLayout) ViewFindUtils.find(baseView, R.id.frame_root);
        frameRoot.setBackgroundColor(ResUtils.getColor(android.R.color.black));
        isAima = false;
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(baseView);
        initWidget();
        initData();
        initListener();
    }

    private void initListener() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                index.setText((mViewPager.getCurrentItem() + 1) + "/" + setPhotoList().size());
                frameRoot.setBackgroundColor(ResUtils.getColor(android.R.color.black));
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }

        });
    }


    private void initData() {
        mViewPager.setAdapter(new SamplePagerAdapter());
        if (setCurrtentIndex() != -1 && setCurrtentIndex() >= 0) {
            mViewPager.setCurrentItem(setCurrtentIndex());
        }

        if (!ListUtils.isEmpty(setPhotoList())) {
            if (setPhotoList().size() == 1) {
                index.setVisibility(View.GONE);
            } else {
                index.setVisibility(View.VISIBLE);
                index.setText((mViewPager.getCurrentItem() + 1) + "/" + setPhotoList().size());
            }
        }
    }

    private void initWidget() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        index = (TextView) findViewById(R.id.index);
        mViewPager.setOffscreenPageLimit(3);// 设置缓存页面，当前页面的相邻N各页面都会被
    }


    /**
     * 准备..是否带缩略图业务
     */
    protected void prepare(final Holder holder, final String thumbnailUrl, final String artworkUrl) {
        if (StringUtils.isEmpty(thumbnailUrl)) {//没有缩略图时
            holder.thumbnail.setVisibility(View.GONE);
            displayArtwork(holder, thumbnailUrl, artworkUrl);
        } else {
            holder.thumbnail.setVisibility(View.VISIBLE);
            displayThumbnail(holder, thumbnailUrl, artworkUrl);
        }
    }


    /**
     * 加载缩略图业务
     */
    protected void displayThumbnail(final Holder holder, final String thumbnailUrl, final String artworkUrl) {
        BaseApplication.imageLoader.displayImage(thumbnailUrl, holder.thumbnail, ImageLoaderUtils.setCommonBuilder().displayer(new SimpleBitmapDisplayer()).build(), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                view.setLayoutParams(setLayoutParams(AutoFrameLayout.LayoutParams.WRAP_CONTENT));
                holder.artwork.setVisibility(View.GONE);
                holder.loading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                displayLoadingFailed(failReason);
                holder.loading.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                displayArtwork(holder, thumbnailUrl, artworkUrl);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                holder.loading.setVisibility(View.GONE);

            }
        });
    }


    /**
     * 加载原图业务
     */
    protected void displayArtwork(final Holder holder, final String thumbnailUrl, final String artworkUrl) {
        BaseApplication.imageLoader.displayImage(artworkUrl, holder.artwork, ImageLoaderUtils.displayConfigDisplay(), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                holder.loading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                holder.loading.setVisibility(View.GONE);
                if (StringUtils.isEmpty(thumbnailUrl)) {
                    holder.artwork.setVisibility(View.VISIBLE);
                    holder.thumbnail.setVisibility(View.GONE);
                    displayLoadingFailed(failReason);
                } else {
                    holder.thumbnail.setVisibility(View.VISIBLE);
                    holder.artwork.setVisibility(View.GONE);
                    holder.thumbnail.setLayoutParams(setLayoutParams(AutoFrameLayout.LayoutParams.MATCH_PARENT));
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.thumbnail.setVisibility(View.GONE);
                holder.loading.setVisibility(View.GONE);
                holder.artwork.setVisibility(View.VISIBLE);

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                holder.loading.setVisibility(View.GONE);
                holder.thumbnail.setLayoutParams(setLayoutParams(AutoFrameLayout.LayoutParams.MATCH_PARENT));
            }
        });
    }


    /**
     * 加载失败业务
     *
     * @param failReason 失败原因
     */
    protected void displayLoadingFailed(FailReason failReason) {
        if (!NetUtils.isConnected(BasePhotoViewActivity.this)) {//网络错误
            ToastUtils.show(R.string.common_photo_not_network);
            return;
        }
        switch (failReason.getType()) {
            case IO_ERROR://加载失败
                //ToastUtils.show( R.string.common_photo_io_error);
                break;
            case OUT_OF_MEMORY://内存不足
                //ToastUtils.show(R.string.common_photo_out_of_memory);
                break;
            case DECODING_ERROR://解析错误
                //ToastUtils.show( R.string.common_photo_decoding_error);
                break;
            case UNKNOWN://未知路径
                //ToastUtils.show( R.string.common_photo_unknown);
                break;
        }
    }

    /**
     * 缩略图加载过程中布局大小
     *
     * @param withAndHight
     * @return
     */
    protected AutoFrameLayout.LayoutParams setLayoutParams(int withAndHight) {
        AutoFrameLayout.LayoutParams lp = new AutoFrameLayout.LayoutParams(withAndHight, withAndHight);
        lp.gravity = Gravity.CENTER;
        return lp;
    }


    /**
     * list数据列表
     *
     * @return
     */
    public abstract List setPhotoList();

    /**
     * 缩略图url
     *
     * @param position
     * @return
     */
    public abstract String setThumbnail(int position);

    /**
     * 原始图url
     *
     * @param position
     * @return
     */
    public abstract String setArtwork(int position);


    /**
     * 当前选择下标
     *
     * @return
     */
    public abstract int setCurrtentIndex();

    public int setWidth() {
        return -1;
    }

    public int setHeight() {
        return -1;
    }

    public int setLocationX() {
        return -1;
    }

    public int setLocationY() {
        return -1;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (setLocationX() == -1 && setLocationY() == -1) {//表示没有从哪里来回哪里去动画
            return;
        }
        if (isFinishing()) {
            overridePendingTransition(0, 0);
        }
    }

    static class Holder {
        public SmoothImageView artwork;
        public SmoothImageView thumbnail;
        public ProgressBar loading;
        public FrameLayout frameLaout;
    }

    class SamplePagerAdapter extends PagerAdapter implements View.OnClickListener, PhotoViewAttacher.OnPhotoTapListener {

        @Override
        public int getCount() {
            return setPhotoList().size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            final Holder holder = new Holder();
            View view = InflaterUtils.inflater(container.getContext(), R.layout.common_photo_view_item);
            holder.artwork = (SmoothImageView) view.findViewById(R.id.iv_artwork);
            holder.thumbnail = (SmoothImageView) view.findViewById(R.id.iv_thumbnail);
            holder.loading = (ProgressBar) view.findViewById(R.id.prg_load);
            holder.frameLaout = (FrameLayout) view.findViewById(R.id.frame_pthoto);
            photoTransformIn(holder, position);
            ((HackyViewPager) container).addView(view, 0);
            listener(holder);
            prepare(holder, setThumbnail(position), setArtwork(position));
            return view;
        }

        /**
         * 进入activity画动
         *
         * @param holder
         */
        private void photoTransformIn(Holder holder, int position) {
            if (setLocationX() == -1 && setLocationY() == -1) {//表示没有从哪里来回哪里去动画
                return;
            }
            if (setCurrtentIndex() == position) {
                clickCurrtentTransformIn(holder, position);
            } else if (position < setCurrtentIndex()) {
                moveBeforeTransformIn(holder, position);
            } else if (position > setCurrtentIndex()) {
                moveAfterTransformIn(holder, position);

            }
            if (!isAima) {
                holder.artwork.transformIn();
                holder.thumbnail.transformIn();
                isAima = true;
            }

            // holder.artwork.setLayoutParams(new AutoFrameLayout.LayoutParams(-1, -1));
            holder.artwork.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //holder.thumbnail.setLayoutParams(new AutoFrameLayout.LayoutParams(-1, -1));
            holder.thumbnail.setScaleType(ImageView.ScaleType.FIT_CENTER);

        }

        /**
         * 点击view位置计算
         */
        private void clickCurrtentTransformIn(Holder holder, int position) {
            currentColumns = position / numColumns;
            currentColumnsIndex = getViewColunmsIndex(position);
            holder.artwork.setOriginalInfo(setWidth(), setHeight(), setLocationX(), setLocationY() - getActionBarHeight());
            holder.thumbnail.setOriginalInfo(setWidth(), setHeight(), setLocationX(), setLocationY() - getActionBarHeight());
        }


        private int setMoveBeforeVerticalSpacing(int position) {
            return Math.abs((currentColumns - getViewCoumns(position)) * verticalSpacing);
        }

        /**
         * 点击view位置向前移动位置计算
         */
        private void moveBeforeTransformIn(Holder holder, int position) {
            if (getViewCoumns(position) == currentColumns) {//当前行
                if (getViewColunmsIndex(position) < currentColumnsIndex) {//同一位置之前的
                    setViewLocation(holder, setLocationX() - setWidth() * (currentColumnsIndex - getViewColunmsIndex(position)) - horizontalSpacing, setLocationY());
                }
            } else if (getViewCoumns(position) < currentColumns) {//上一行
                if (getViewColunmsIndex(position) == currentColumnsIndex) {//上一行同一位置
                    setViewLocation(holder, setLocationX(), setLocationY() - setHeight() * (currentColumns - getViewCoumns(position)) - setMoveBeforeVerticalSpacing(position));
                } else if (getViewColunmsIndex(position) < currentColumnsIndex) {//上一行同一位置之前的
                    setViewLocation(holder, setLocationX() - setWidth() * (currentColumnsIndex - getViewColunmsIndex(position)) - horizontalSpacing, setLocationY() - setHeight() * getViewColunmsCount(position) - setMoveBeforeVerticalSpacing(position));
                } else if (getViewColunmsIndex(position) > currentColumnsIndex) {//上一行同一位置之后的
                    setViewLocation(holder, setLocationX() + setWidth() * (getViewColunmsIndex(position) - currentColumnsIndex) + horizontalSpacing, setLocationY() - setHeight() * getViewColunmsCount(position) - setMoveBeforeVerticalSpacing(position));
                }

            }
        }


        /**
         * 点击view位置向后移动位置计算
         */
        private void moveAfterTransformIn(Holder holder, int position) {
            if (getViewCoumns(position) == currentColumns) {//当前行
                if (getViewColunmsIndex(position) > currentColumnsIndex) {//同一位置之后的
                    setViewLocation(holder, setLocationX() + horizontalSpacing + setWidth() * (getViewColunmsIndex(position) - currentColumnsIndex), setLocationY());
                }
            } else if (getViewCoumns(position) > currentColumns) {//下一行
                if (getViewColunmsIndex(position) == currentColumnsIndex) {//下一行同一位置
                    setViewLocation(holder, setLocationX(), setLocationY() + setHeight() * (-getViewColunmsCount(position)) + setMoveBeforeVerticalSpacing(position));
                } else if (getViewColunmsIndex(position) < currentColumnsIndex) {//下一行同一位置之前的
                    setViewLocation(holder, setLocationX() - setWidth() * (currentColumnsIndex - getViewColunmsIndex(position)) - horizontalSpacing, setLocationY() + setHeight() * (-getViewColunmsCount(position)) + setMoveBeforeVerticalSpacing(position));
                } else if (getViewColunmsIndex(position) > currentColumnsIndex) {//下一行同一位置之后的
                    setViewLocation(holder, setLocationX() + setWidth() * (getViewColunmsIndex(position) - currentColumnsIndex) + horizontalSpacing, setLocationY() + setHeight() * (-getViewColunmsCount(position)) + setMoveBeforeVerticalSpacing(position));
                }

            }
        }

        /**
         * 当前view 与点击view的行数差
         *
         * @param position
         * @return
         */
        private int getViewColunmsCount(int position) {
            return currentColumns - getViewCoumns(position);
        }

        /**
         * 当前view所在的行数位置
         *
         * @param holder
         * @param locationX
         * @param locationY
         */
        private void setViewLocation(Holder holder, int locationX, int locationY) {
            holder.artwork.setOriginalInfo(setWidth(), setHeight(), locationX, locationY - getActionBarHeight());
            holder.thumbnail.setOriginalInfo(setWidth(), setHeight(), locationX, locationY - getActionBarHeight());
        }

        /**
         * 当前view所在的行数
         *
         * @param position
         * @return
         */
        private int getViewCoumns(int position) {
            return position / numColumns;
        }


        /**
         * 当前view在所在行的下标
         *
         * @param position
         * @return
         */
        private int getViewColunmsIndex(int position) {
            return position % numColumns;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void onClick(View view) {
            photoFinishType(view);
        }

        @Override
        public void onPhotoTap(View view, float v, float v1) {
            photoFinishType(view);
        }

        /**
         * 事件监听
         *
         * @param holder
         */
        public void listener(Holder holder) {
            holder.artwork.setOnPhotoTapListener(this);
            holder.thumbnail.setOnPhotoTapListener(this);
            holder.frameLaout.setOnClickListener(this);
        }


        /**
         * 关闭activity分类
         *
         * @param view
         */
        private void photoFinishType(View view) {
            try {
                if (setLocationX() == -1 && setLocationY() == -1) {//表示没有从哪里来回哪里去动画
                    StartActUtils.finish(BasePhotoViewActivity.this);
                } else {
                    //初始化渐变动画
                    //frameRoot.setBackgroundColor(ResUtils.getColor(android.R.color.transparent));
                    index.setVisibility(View.GONE);
                    smoothImagerFinish((SmoothImageView) view);
                }
            }catch (Exception e){
                e.printStackTrace();
                StartActUtils.finish(BasePhotoViewActivity.this);
            }
        }

        /**
         * 关闭activity动画
         *
         * @param view
         */
        public void smoothImagerFinish(SmoothImageView view) {
            view.setOnTransformListener(new SmoothImageView.TransformListener() {
                @Override
                public void onTransformComplete(int mode) {
                    if (mode == 2) {
                        finish();
                    }
                }
            });
            view.transformOut();
        }
    }


}
