package com.android.tanlifei.framestructure.common.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Created by tanlifei on 15/9/17.
 */
public class AnimationUtils {

    /**
     * 图片圆形旋转
     * @param v  旋转控件
     * @param drawableId 图片id
     */
    public static void show(ImageView v,int drawableId) {
        v.clearAnimation();
        v.setImageResource(drawableId);
        v.startAnimation(getRotateAnimation());
    }


    /**
     *
     * 图片圆形旋转
     * @return
     */
    public static RotateAnimation getRotateAnimation() {
        RotateAnimation mRotateAnimation = new RotateAnimation(0f, 359f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setDuration(1000L);
        mRotateAnimation.setInterpolator(new LinearInterpolator());
        mRotateAnimation.setRepeatCount(-1);
        mRotateAnimation.setRepeatMode(Animation.RESTART);
        return mRotateAnimation;
    }
}
