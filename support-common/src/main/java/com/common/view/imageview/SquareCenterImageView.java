package com.common.view.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;
/**
 * 正方形的ImageView
 * @author dty
 *
 */
public class SquareCenterImageView extends ImageView {
    public SquareCenterImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
 
    public SquareCenterImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    public SquareCenterImageView(Context context) {
        super(context);
    }
    
    @Override
    public void setImageBitmap(Bitmap bm) {
    		super.setImageBitmap(bm);
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        //高度和宽度一样
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}


