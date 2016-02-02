package com.common.adapter.base;

/**
 * 多个布局实现接口
 * @param <T>
 */
public interface MultiItemTypeSupport<T>
{
	int getLayoutId(int position, T t);

	int getViewTypeCount();

	int getItemViewType(int postion, T t);
}