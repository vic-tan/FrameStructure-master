package com.common.dialog;

import com.common.ui.base.main.BaseApplication;
import com.common.utils.DensityUtils;

/**
 * Created by tanlifei on 16/3/7.
 */
public class Utils {
    public static int dp2px(float dpVal){
        return DensityUtils.dp2px(BaseApplication.appContext,dpVal);
    }
}
