package com.android.tanlifei.framestructure.engine.interf;

import com.android.tanlifei.framestructure.common.constants.enumConstants.PromptStatus;

/**
 * 提示点击刷新接口
 *
 * @author tanlifei
 * @date 2015年2月3日 上午10:50:58
 */
public interface ILoadingPromptCallBack {

    /**
     * 刷新（点击重新请求）
     * @param status 重新请求前的状态码
     */
    void onRefresh(PromptStatus status);
}
