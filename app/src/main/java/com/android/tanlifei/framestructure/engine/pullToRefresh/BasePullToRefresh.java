package com.android.tanlifei.framestructure.engine.pullToRefresh;

import android.content.Context;
import android.view.View;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.bean.base.PageBean;
import com.android.tanlifei.framestructure.common.constants.JsonConstants;
import com.android.tanlifei.framestructure.common.constants.enumConstants.PromptStatus;
import com.android.tanlifei.framestructure.common.http.HttpTask;
import com.android.tanlifei.framestructure.common.http.base.BaseHttpParams;
import com.android.tanlifei.framestructure.common.http.base.CallbackBean;
import com.android.tanlifei.framestructure.common.http.base.TaskParamsBean;
import com.android.tanlifei.framestructure.common.utils.JsonUtils;
import com.android.tanlifei.framestructure.common.utils.ResUtils;
import com.android.tanlifei.framestructure.common.utils.StringUtils;
import com.android.tanlifei.framestructure.common.utils.ToastUtils;
import com.android.tanlifei.framestructure.common.view.prompt.LoadingPrompt;
import com.android.tanlifei.framestructure.engine.interf.IHttpTaskCallBack;
import com.android.tanlifei.framestructure.engine.interf.ILoadingPromptReStartCallBack;
import com.android.tanlifei.framestructure.engine.interf.IPullToRefreshCallBack;
import com.handmark.pulltorefresh.library.PullToRefreshBase;


/**
 * 列表PullToRefresh 基类
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #baseInit(Context, View, IPullToRefreshCallBack)} 初始化基本数据</li>
 * <li>{@link #startRequest()} 开始请求网络</li>
 * <li>{@link #parseJsonController(BaseJson)}  处理请求成功后的业务</li>
 * <li>{@link #pullDownToRefresh()} 下拉刷新业务处理</li>
 * <li>{@link #pullUpToRefresh()}  上拉刷新处理</li>
 * <li>{@link #pullFromStartParseJson(String)} 下拉完成要解析的json</li>
 * <li>{@link #pullFromEndParseJson(String)} 上拉完成要解析的json</li>
 * <li>{@link #parseJson(String)} 把json解析的对象放到集合里</li>
 * <li>{@link #initPageBean()} 初始化分页参数</li>
 * <li>{@link #parsePageBean(String)} 解析分页数据</li>
 * <li>{@link #isLastPage()} 当前面是不是最后一页</li>
 * <li>{@link #reSetPageBean()} 下拉刷新时重置分页属性</li>
 * <li>{@link #addPageNumber()} 上拉刷新时累加分页属性</li>
 * <li>{@link #isEmptyDate(String)} 集合数据为空</li>
 * </ul>
 * <ul>
 * <strong>子类要实现或者实现父类的的方法</strong>
 * <li>{@link #requestFinish()} 请求完成后的操作</li>
 * <li>{@link #onRefresh(PromptStatus)} 加载布局点击刷新（点击重新请求）</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public abstract class BasePullToRefresh implements ILoadingPromptReStartCallBack {

    protected View baseView; // 布局
    protected PageBean pageBean;// 分页参数实体
    protected Context context;
    protected IPullToRefreshCallBack refreshCallBack;// 回调接口
    protected LoadingPrompt loadingPrompt;// 加载提示
    protected PullToRefreshBase.Mode mode = PullToRefreshBase.Mode.PULL_FROM_START;//上拉下拉标识,用来区分解析json时分别调用各自的解析方法

    /**
     * 初始化基本数据
     *
     * @param context         上下文
     * @param refreshCallBack //请求加载数据回调
     */
    protected void baseInit(Context context, View baseView, IPullToRefreshCallBack refreshCallBack) {
        this.context = context;
        this.refreshCallBack = refreshCallBack;
        this.baseView = baseView;
        initPageBean();//初始化分页参数
        loadingPrompt = new LoadingPrompt(context, this);//初始化加载提示布局

    }

    /**
     * 开始请求网络
     */
    protected void startRequest() {
        HttpTask.post(new TaskParamsBean(refreshCallBack.taskUrl(), refreshCallBack
                .taskParams(BaseHttpParams.pageParams(pageBean
                        .getPageNumber())), null), new IHttpTaskCallBack() {
            @Override
            public void taskHandler(CallbackBean callbackBean) {
                switch (callbackBean.getStatus()) {
                    case NETWORK_ERROR:
                        loadingPrompt.displayNetworkErrorLayout();
                        requestFinish();
                        break;
                    case START:
                        loadingPrompt.displayProgressLayout();
                        break;
                    case FAILURE:
                    case SERVICE_ERROR:
                        loadingPrompt.displayserviceErrorLayout();
                        requestFinish();
                        break;
                    case TIMEOUT_ERROR:
                    case CANCEL:
                        loadingPrompt.displayTimeoutErrorLayout();
                        requestFinish();
                        break;
                    case SUCCESS:
                        parseJsonController(callbackBean.getBaseJson());
                        requestFinish();
                        break;
                    default:
                        requestFinish();
                        break;
                }
            }
        });
    }

    /**
     * 处理请求成功后的业务
     *
     * @param baseJson
     */
    private void parseJsonController(BaseJson baseJson) {
        if (refreshCallBack.isCustomParseJson()) {//手动解析Json
            parsePageBean(baseJson.getData());//解析分页数据
            if (isEmptyDate(JsonUtils.getKeyResult(baseJson.getData(), JsonConstants.JSON_LIST))) {
                loadingPrompt.displayEmptyLayout();
                return;
            }
            refreshCallBack.customParseJson(baseJson, mode);
            return;
        } else if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {//自动解析下拉json
            parsePageBean(baseJson.getData());//解析分页数据
            if (isEmptyDate(JsonUtils.getKeyResult(baseJson.getData(), JsonConstants.JSON_LIST))) {
                loadingPrompt.displayEmptyLayout();
                return;
            }
            pullFromStartParseJson(JsonUtils.getKeyResult(baseJson.getData(), JsonConstants.JSON_LIST));
            return;
        } else if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {//自动解析上拉json
            parsePageBean(baseJson.getData());//解析分页数据
            //pageBean.setPageNumber(pageBean.getPageNumber()+1);//测试本数据用的
            if (isEmptyDate(JsonUtils.getKeyResult(baseJson.getData(), JsonConstants.JSON_LIST))) {
                loadingPrompt.displayEmptyLayout();
                return;
            }
            pullFromEndParseJson(JsonUtils.getKeyResult(baseJson.getData(), JsonConstants.JSON_LIST));
            return;
        } else if (mode == PullToRefreshBase.Mode.DISABLED) {//没有上拉下拉时
            if (isEmptyDate(baseJson.getData())) {
                loadingPrompt.displayEmptyLayout();
                return;
            }
            pullFromStartParseJson(baseJson.getData());
            return;
        }
    }

    /**
     * 下拉刷新业务处理
     */
    protected void pullDownToRefresh() {
        mode = PullToRefreshBase.Mode.PULL_FROM_START;
        reSetPageBean();
        startRequest();
    }


    /**
     * 上拉刷新处理
     */
    protected void pullUpToRefresh() {
        if (!isLastPage()) {//不是最后一页
            addPageNumber();
            startRequest();
            mode = PullToRefreshBase.Mode.PULL_FROM_END;
        } else {//最后一页
            ToastUtils.show(context, ResUtils.getStr(R.string.common_pull_to_refresh_last_page));
            requestFinish();
            //handler.obtainMessage(HttpTaskStatus.FINISH.value()).sendToTarget();
        }
    }


    /**
     * 下拉完成要解析的json
     */
    private void pullFromStartParseJson(String json) {
        refreshCallBack.getList().clear();//清除以前的数据
        parseJson(json);//把json解析的对象放到集合里
    }

    /**
     * 上拉完成要解析的json
     */
    private void pullFromEndParseJson(String json) {
        parseJson(json);//把json解析的对象放到集合里
    }

    /**
     * 把json解析的对象放到集合里
     */
    private void parseJson(String json) {
        refreshCallBack.getList().addAll(JsonUtils.parseToObjectList(json, refreshCallBack.parseClassName()));
        refreshCallBack.getAdapter().notifyDataSetChanged();
    }


    /**
     * 初始化分页参数
     */
    protected void initPageBean() {
        pageBean = new PageBean();
        pageBean.setPageNumber(1);
    }

    /**
     * 解析分页数据
     *
     * @param json
     */
    protected void parsePageBean(String json) {
        pageBean = JsonUtils.parseToObjectBean(json, PageBean.class);
    }


    /**
     * 当前面是不是最后一页
     */
    protected boolean isLastPage() {
        if (null == pageBean) {
            initPageBean();
            return false;
        }
        if (pageBean.getTotalPage() > pageBean.getPageNumber()) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * 下拉刷新时重置分页属性
     */
    protected void reSetPageBean() {
        if (null == pageBean) {
            initPageBean();
            return;
        }
        pageBean.setPageNumber(1);
    }

    /**
     * 上拉刷新时累加分页属性
     */
    protected void addPageNumber() {
        if (null == pageBean) {
            initPageBean();
            return;
        }
        pageBean.setPageNumber(pageBean.getPageNumber() + 1);
    }

    /**
     * 集合数据为空
     *
     * @param lisStr
     * @return
     */
    private boolean isEmptyDate(String lisStr) {
        if (StringUtils.isEmpty(lisStr) || StringUtils.isEquals(lisStr, "[]")) {
            return true;
        }
        return false;
    }

    /**
     * 请求完成后的操作
     */
    public abstract void requestFinish();

    /**
     * 加载布局点击刷新（点击重新请求）
     *
     * @param status 重新请求前的状态码
     */
    @Override
    public void onRefresh(PromptStatus status) {
        startRequest();
    }

}
