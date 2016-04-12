package com.common.view.dialog.base.popup;

import android.content.Context;
import android.view.View;


/**
 * Use dialog to realize bubble style popup(利用Dialog实现泡泡样式的弹窗)
 * thanks https://github.com/michaelye/EasyDialog
 */
public class BubblePopup extends BaseBubblePopup<BubblePopup> {

    public BubblePopup(Context context, View wrappedView) {
        super(context, wrappedView);
    }

    @Override
    public View onCreateBubbleView() {
        return null;
    }
}
