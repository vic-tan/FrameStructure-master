package com.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * CommonParserHandler <br>统一解析Json
 * <li>
 * <Strong> Method </Strong>getData
 * <ul> {@link #getKeyResult(String, String)} 根据key拿vulue数据的内容</ul>
 * <ul>{@link #parseToObjectBean(String, Class)} json字符串转化为实体bean</ul>
 * <ul>{@link #parseToObjectList(String, Class)} json字符串转化为实体bean集合</ul>
 * <ul>{@link #parseToObjectList(JSONObject, Class, String)} 获取为key字段json的内容,并转化为实体bean集合</ul>
 * </li>
 *
 * @author tanlifei
 * @version 2015-01-26
 */
public class JsonUtils {

    /**
     * 拿到字段的值
     *
     * @param json
     * @param key  要解析的字段名
     * @return
     * @throws JSONException
     */
    public static String getKeyResult(String json, String key) {
        try {
            JSONObject objJson = JSON
                    .parseObject(json);
            return objJson.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * json字符串转化为实体bean
     *
     * @param json
     * @param clazz 实体bean名
     * @return
     * @throws JSONException
     */
    public static <T> T parseToObjectBean(String json, Class<T> clazz) {
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json字符串转化为实体bean集合
     *
     * @param json
     * @param clazz 实体bean名
     * @return
     * @throws JSONException
     */
    public static <T> List<T> parseToObjectList(String json, Class<T> clazz) {
        try {
            JSONArray jsonArray = JSON.parseArray(json);
            Iterator<Object> iterator = jsonArray.iterator();
            List<T> container = new ArrayList<T>();
            while (iterator.hasNext())
                container.add(JSON.parseObject(iterator.next().toString(), clazz));
            return container;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取为key字段json的内容,并转化为实体bean集合
     *
     * @param json
     * @param clazz 实体bean名
     * @param key   要解析的字段名
     * @return
     * @throws JSONException
     */
    public static <T> List<T> parseToObjectList(JSONObject json, Class<T> clazz, String key) {
        try {
            List<T> container = new ArrayList<T>();
            container = JSON.parseArray(json.getString(key), clazz);
            return container;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
