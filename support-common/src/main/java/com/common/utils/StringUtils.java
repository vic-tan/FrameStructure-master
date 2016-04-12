package com.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tanlifei
 * @ClassName: StringUtil
 * @Description: 字符串操作工具类
 * @date 2015-01-26 上午10:43:16
 */
public class StringUtils {

    private StringUtils() {

    }

    /**
     * @param str 源字符串
     * @return int 返回类型
     * @Title: convert2Int
     * @Description: 字符串转为整型，如转换出异常将返回0
     */
    public static int convert2Int(String str) {
        try {
            int i = Integer.parseInt(str);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param str 操作字符串
     * @return String 返回类型
     * @Title: trim
     * @Description: 去除字符串的前后空格
     */
    public static String trim(String str) {
        if (str == null)
            return null;
        return str.trim();
    }

    /**
     * 是否为空或者长度 为0
     *
     * @param str
     * @return boolean 返回类型
     * @Title: isBlank
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * @param str 需要处理的字符串
     * @return String 返回类型
     * @Title: trimorempty
     * @Description: 返回清空和去除空前后格的字符串
     */
    public static String trimorempty(String str) {
        String result = "";
        if (str == null)
            result = "";
        else
            result = trim(str);
        return result;

    }

    /**
     * @param str 源字符串
     * @return boolean 返回类型
     * @Title: isEmpty
     * @Description: 判断是否是空字符串
     */
    public static boolean isEmpty(String str) {
        if (str == null)
            return true;
        if ("".equals(str.trim()))
            return true;
        return false;
    }

    public static String getStr(String str) {
        if (str == null)
            return "";
        if ("".equals(str.trim()))
            return "";
        else
            return str;
    }

    /**
     * compare two string
     *
     * @param actual
     * @param expected
     * @return
     * @see ObjectUtils#isEquals(Object, Object)
     */

    /**
     * 两字符串是否相等
     *
     * @param actual
     * @param expected
     * @return boolean 返回类型
     * @Title: isEquals
     */
    public static boolean isEquals(String actual, String expected) {
        return actual == expected
                || (actual == null ? expected == null : actual.equals(expected));
    }

    /**
     * @param source 源字符串
     * @param regex  正则表达式
     * @return String 返回类型
     * @Title: getRegexString
     * @Description: 从字符串中得到所有匹配的字符串
     */
    public static String getRegexString(String source, String regex) {
        if ((null == source) || (null == regex))
            return "";
        StringBuffer result = new StringBuffer();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            result.append(matcher.group());
        }
        return result.toString();
    }

    /**
     * @param str
     * @return String 返回类型
     * @Title: setNbsp
     * @Description: 滤除空格
     */
    public static String setNbsp(String str) {
        int j = str.length();
        StringBuffer stringbuffer = new StringBuffer(j + 500);
        for (int i = 0; i < j; i++) {
            if (str.charAt(i) == ' ') {
                stringbuffer.append(" ");
            } else {
                stringbuffer.append(str.charAt(i) + "");
            }
        }
        return stringbuffer.toString();
    }

    /**
     * @param input 输入的字符串
     * @return boolean 返回类型
     * @Title: isNumeric
     * @Description: 判断字符串是否全是数字字符
     */
    public static boolean isNumeric(String input) {
        if (isEmpty(input)) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char charAt = input.charAt(i);
            if (!Character.isDigit(charAt)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param input          输入的字符串
     * @param sourceEncoding 源字符集名称
     * @param targetEncoding 目标字符集名称
     * @return String 返回类型
     * @Title: changeEncoding
     * @Description: 用一句话描述该文件做什么
     */
    public static String changeEncoding(String input, String sourceEncoding,
                                        String targetEncoding) {
        if (input == null || input.equals("")) {
            return input;
        }
        try {
            byte[] bytes = input.getBytes(sourceEncoding);
            return new String(bytes, targetEncoding);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return input;
    }

    /**
     * 转化为UTF-8字符集
     *
     * @param str
     * @param defultReturn
     * @return String 返回类型
     * @Title: utf8Encode
     * @Description: 用一句话描述该文件做什么
     */
    public static String utf8Encode(String str, String defultReturn) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                if (!isEmpty(defultReturn))
                    return URLEncoder.encode(str, defultReturn);
                else
                    return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * 在html中处理特殊字符
     *
     * @param source
     * @return String 返回类型
     * @Title: htmlEscapeCharsToString
     * <pre>
     * htmlEscapeCharsToString(null) = null;
     * htmlEscapeCharsToString("") = "";
     * htmlEscapeCharsToString("mp3") = "mp3";
     * htmlEscapeCharsToString("mp3&lt;") = "mp3<";
     * htmlEscapeCharsToString("mp3&gt;") = "mp3\>";
     * htmlEscapeCharsToString("mp3&amp;mp4") = "mp3&mp4";
     * htmlEscapeCharsToString("mp3&quot;mp4") = "mp3\"mp4";
     * htmlEscapeCharsToString("mp3&lt;&gt;&amp;&quot;mp4") = "mp3\<\>&\"mp4";
     * </pre>
     * @throws:throws
     */
    public static String htmlEscapeCharsToString(String source) {
        return StringUtils.isEmpty(source) ? source : source
                .replaceAll("&lt;", "<").replaceAll("&gt;", ">")
                .replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }
}