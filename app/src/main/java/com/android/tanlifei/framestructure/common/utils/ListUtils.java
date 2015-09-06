package com.android.tanlifei.framestructure.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanlifei
 * @ClassName: ListUtils
 * @date 2015-01-26 上午11:19:03
 */
public class ListUtils {

    /**
     * 默认的分隔符 *
     */
    public static final String DEFAULT_JOIN_SEPARATOR = ",";

    public static <V> int getSize(List<V> sourceList) {
        return sourceList == null ? 0 : sourceList.size();
    }

    /**
     * 判断是否为空或者长度为0
     *
     * @param sourceList 源集合
     * @return boolean 如果list是空或者长度为0，返回ture,其它返回false
     * @Title: isEmpty
     * <pre>
     * isEmpty(null)   =   true;
     * isEmpty({})     =   true;
     * isEmpty({1})    =   false;
     * </pre>
     */
    public static <V> boolean isEmpty(List<V> sourceList) {
        return (sourceList == null || sourceList.size() == 0);
    }


    /**
     * 比较两个集合
     *
     * @param actual
     * @param expected
     * @return boolean 返回类型
     * @Title: isEquals
     * <pre>
     * isEquals(null, null) = true;
     * isEquals(new ArrayList&lt;String&gt;(), null) = false;
     * isEquals(null, new ArrayList&lt;String&gt;()) = false;
     * isEquals(new ArrayList&lt;String&gt;(), new ArrayList&lt;String&gt;()) = true;
     * </pre>
     */
    public static <V> boolean isEquals(ArrayList<V> actual, ArrayList<V> expected) {
        if (actual == null) {
            return expected == null;
        }
        if (expected == null) {
            return false;
        }
        if (actual.size() != expected.size()) {
            return false;
        }

        for (int i = 0; i < actual.size(); i++) {
            if (!ObjectUtils.isEquals(actual.get(i), expected.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 转换成的字符串，分隔符为“，”
     *
     * @param list
     * @return String 如果列表为空，则返回“”
     * @Title: join
     * <pre>
     * join(null)      =   "";
     * join({})        =   "";
     * join({a,b})     =   "a,b";
     * </pre>
     */
    public static String join(List<String> list) {
        return join(list, DEFAULT_JOIN_SEPARATOR);
    }


    /**
     * 根据算定义分隔符转换成的字符串（算定义分隔符为一个字符）
     *
     * @param list
     * @param separator 算定义分隔符 （算定义分隔符为一个字符）
     * @return String 如果列表为空，则返回“”
     * @Title: join
     * <pre>
     * join(null, '#')     =   "";
     * join({}, '#')       =   "";
     * join({a,b,c}, ' ')  =   "abc";
     * join({a,b,c}, '#')  =   "a#b#c";
     * </pre>
     */
    public static String join(List<String> list, char separator) {
        return join(list, new String(new char[]{separator}));
    }


    /**
     * 根据算定义分隔符转换成的字符串（算定义分隔符为字符串）
     *
     * @param list
     * @param separator 算定义分隔符
     * @return String 如果列表为空，则返回“”
     * @Title: join
     * <pre>
     * join(null, "#")     =   "";
     * join({}, "#$")      =   "";
     * join({a,b,c}, null) =   "a,b,c";
     * join({a,b,c}, "")   =   "abc";
     * join({a,b,c}, "#")  =   "a#b#c";
     * join({a,b,c}, "#$") =   "a#$b#$c";
     * </pre>
     */
    public static String join(List<String> list, String separator) {
        if (isEmpty(list)) {
            return "";
        }
        if (separator == null) {
            separator = DEFAULT_JOIN_SEPARATOR;
        }

        StringBuilder joinStr = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            joinStr.append(list.get(i));
            if (i != list.size() - 1) {
                joinStr.append(separator);
            }
        }
        return joinStr.toString();
    }

    /**
     * 根据算定义分隔符转换成的List<Object>（算定义分隔符为字符串）
     *
     * @param sourceStr 源数据
     * @param separator 分隔符
     * @return 如果sourceStr为空，则null
     */
    public static List<Object> strTransformationList(String sourceStr, String separator) {
        List<Object> list = null;
        String mSeparator = ";";
        if (!StringUtils.isEmpty(sourceStr)) {
            list = new ArrayList<Object>();
            if (!StringUtils.isEmpty(separator))
                mSeparator = separator;
            String[] str = sourceStr.split(mSeparator);
            if (null != str && str.length > 0) {
                for (int i = 0; i < str.length; i++) {
                    list.add(str[i]);
                }

            }
        }
        return list;
    }

    /**
     * add distinct entry to list
     *
     * @param <V>
     * @param sourceList
     * @param entry
     * @return if entry already exist in sourceList, return false, else add it and return true.
     */
    public static <V> boolean addDistinctEntry(List<V> sourceList, V entry) {
        return (sourceList != null && !sourceList.contains(entry)) ? sourceList.add(entry) : false;
    }


    /**
     * add all distinct entry to list1 from list2
     *
     * @param <V>
     * @param sourceList
     * @param entryList
     * @return the count of entries be added
     */
    public static <V> int addDistinctList(List<V> sourceList, List<V> entryList) {
        if (sourceList == null || isEmpty(entryList)) {
            return 0;
        }

        int sourceCount = sourceList.size();
        for (V entry : entryList) {
            if (!sourceList.contains(entry)) {
                sourceList.add(entry);
            }
        }
        return sourceList.size() - sourceCount;
    }


    /**
     * 添加不重复的对象到目标列表的最前面
     *
     * @param <V>
     * @param sourceList
     * @param entryList
     */
    public static <V> List<V> addBeforeObject(List<V> sourceList, V entryList) {
        List<V> list = new ArrayList<V>();
        list.add(entryList);
        list.addAll(sourceList);
        return list;
    }


    /**
     * 添加不重复的列表到目标列表的最前面
     *
     * @param <V>
     * @param sourceList
     * @param entryList
     */
    public static <V> int addBeforeList(List<V> sourceList, List<V> entryList) {
        return addDistinctList(entryList, sourceList);
    }


    /**
     * remove duplicate entries in list
     *
     * @param <V>
     * @param sourceList
     * @return the count of entries be removed
     */
    public static <V> int distinctList(List<V> sourceList) {
        if (isEmpty(sourceList)) {
            return 0;
        }

        int sourceCount = sourceList.size();
        int sourceListSize = sourceList.size();
        for (int i = 0; i < sourceListSize; i++) {
            for (int j = (i + 1); j < sourceListSize; j++) {
                if (sourceList.get(i).equals(sourceList.get(j))) {
                    sourceList.remove(j);
                    sourceListSize = sourceList.size();
                    j--;
                }
            }
        }
        return sourceCount - sourceList.size();
    }

    /**
     * add not null entry to list
     *
     * @param sourceList
     * @param value
     * @return <ul>
     * <li>if sourceList is null, return false</li>
     * <li>if value is null, return false</li>
     * <li>return {@link List#add(Object)}</li>
     * </ul>
     */
    public static <V> boolean addListNotNullValue(List<V> sourceList, V value) {
        return (sourceList != null && value != null) ? sourceList.add(value) : false;
    }

    /**
     * @see {@link ArrayUtils#getLast(Object[], Object, Object, boolean)} defaultValue is null, isCircle is true
     */
    @SuppressWarnings("unchecked")
    public static <V> V getLast(List<V> sourceList, V value) {
        return (sourceList == null) ? null : (V) ArrayUtils.getLast(sourceList.toArray(), value, true);
    }

    /**
     * @see {@link ArrayUtils#getNext(Object[], Object, Object, boolean)} defaultValue is null, isCircle is true
     */
    @SuppressWarnings("unchecked")
    public static <V> V getNext(List<V> sourceList, V value) {
        return (sourceList == null) ? null : (V) ArrayUtils.getNext(sourceList.toArray(), value, true);
    }

    /**
     * 颠倒列表
     *
     * @param <V>
     * @param sourceList
     * @return
     */
    public static <V> List<V> invertList(List<V> sourceList) {
        if (isEmpty(sourceList)) {
            return sourceList;
        }

        List<V> invertList = new ArrayList<V>(sourceList.size());
        for (int i = sourceList.size() - 1; i >= 0; i--) {
            invertList.add(sourceList.get(i));
        }
        return invertList;
    }
}