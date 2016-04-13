package com.common.utils;

import java.text.SimpleDateFormat;

/**
 * String 时间 转化格式化
 * Created by tanlifei on 16/4/12.
 */
public class DateFormatUtils {

    public static String format(String date) {
        if (StringUtils.isEmpty(date)) {
            return date;
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return df.format(df.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
            return date;
        }
    }

    public static String format(String date, FormatType formatType) {
        if (StringUtils.isEmpty(date)) {
            return date;
        }
        String format = "yyyy-MM-dd hh:mm:ss";
        try {
            if (formatType == FormatType.DAY) {//显示日期
                format = "yyyy-MM-dd";
            } else if (formatType == FormatType.HOURS) {// 显示年月日，时
                format = "yyyy-MM-dd hh";
            } else if (formatType == FormatType.MINUTES) {//显示 年月日，时分
                format = "yyyy-MM-dd hh:mm";
            } else if (formatType == FormatType.SECONDS) {//显示 年月日，时分秒
                format = "yyyy-MM-dd hh:mm:ss";
            }
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(df.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
            return date;
        }
    }


    public enum FormatType {
        DAY,//显示日期 yyyy-MM-dd
        HOURS,// 显示年月日，时 yyyy-MM-dd hh
        MINUTES,//显示 年月日，时分 yyyy-MM-dd hh:mm
        SECONDS,//显示 年月日，时分秒 yyyy-MM-dd hh:mm:ss
    }
}
