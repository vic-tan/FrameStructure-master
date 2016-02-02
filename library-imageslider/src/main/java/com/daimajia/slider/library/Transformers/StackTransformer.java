package com.daimajia.slider.library.Transformers;

import android.view.View;

import com.daimajia.slider.library.utils.ViewHelper;

public class StackTransformer extends BaseTransformer {

	@Override
	protected void onTransform(View view, float position) {
		ViewHelper.setTranslationX(view,position < 0 ? 0f : -view.getWidth() * position);
	}

}
