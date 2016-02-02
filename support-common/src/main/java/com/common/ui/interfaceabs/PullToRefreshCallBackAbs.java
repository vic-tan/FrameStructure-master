package com.common.ui.interfaceabs;

import com.common.ui.base.activity.BaseActivity;
import com.common.bean.base.BaseJson;
import com.common.engine.interf.IPullToRefreshCallBack;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * PullToRefresh 刷新基本方法接口的适配器模式
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #isCustomParseJson()} 是否手动解析Json标识,为true手动解析，可以在customParseJson方法里自行解析 </li>
 * <li>{@link #customParseJson(BaseJson, PullToRefreshBase.Mode)} 手动解析json</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public abstract class PullToRefreshCallBackAbs extends BaseActivity implements IPullToRefreshCallBack {

    /**
     * 是否手动解析Json标识,为true手动解析，可以在customParseJson方法里自行解析
     *
     * @return false 则自动解析json ，true 为手动解析，可以在customParseJson方法里自行解析
     */
    @Override
    public boolean isCustomParseJson() {
        return false;
    }

    /**
     * 手动解析json
     *
     * @param baseJson 请求的回来的baseJson
     * @param mode     类型，上拉还是下拉
     */
    @Override
    public void customParseJson(BaseJson baseJson, PullToRefreshBase.Mode mode) {
    }
}
