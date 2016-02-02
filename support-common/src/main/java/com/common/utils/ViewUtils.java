package com.common.utils;

import android.app.Activity;
import android.view.View;

/**
 * User: qii
 * Date: 14-1-31
 */
public class ViewUtils {

    @SuppressWarnings({"unchecked", "UnusedDeclaration"})
    public static <T extends View> T findViewById(View view, int id) {
        return (T) view.findViewById(id);
    }

    @SuppressWarnings({"unchecked", "UnusedDeclaration"})
    public static <T extends View> T findViewById(Activity activity, int id) {
        return (T) activity.findViewById(id);
    }
}
