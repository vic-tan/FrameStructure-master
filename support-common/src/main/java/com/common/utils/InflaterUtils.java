package com.common.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 加载一个View布局工具类
* @ClassName: InflaterUtils
* @Description: 用一句话描述该文件做什么
* @author tanlifei 
* @date 2015年3月16日 下午1:21:47
*
 */
public class InflaterUtils {

	public static View inflater(LayoutInflater inflater, int layoutId) {
		return inflater.inflate(layoutId, null);
	}

	public static View inflater(LayoutInflater inflater, int layoutId, ViewGroup container) {
		return inflater.inflate(layoutId, container);
	}

	public static View inflater(Context context, int layoutId) {
		return LayoutInflater.from(context).inflate(layoutId, null);
	}

	public static View inflater(Context context, int layoutId, ViewGroup container) {
		return LayoutInflater.from(context).inflate(layoutId, container);
	}
}
