package com.common.utils;

import java.text.SimpleDateFormat;

/**
 * Created by tanlifei on 16/4/12.
 */
public class DateFormatUtils {

    public static final int DAYS = 1, HOURS = 2, MINUTES = 3, SECONDS = 4;


    public static String convertStrFormat(String date) {
        if(StringUtils.isEmpty(date)){
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


    public static String convertStrFormat(String date, int type) {
        if(StringUtils.isEmpty(date)){
            return date;
        }
        String format = "yyyy-MM-dd hh:mm:ss";
        try {
            if (type == DAYS) {//显示日期
                format = "yyyy-MM-dd";
            } else if (type == HOURS) {// 显示年月日，时
                format = "yyyy-MM-dd hh";
            } else if (type == MINUTES) {//显示 年月日，时分
                format = "yyyy-MM-dd hh:mm";
            } else if (type == SECONDS) {//显示 年月日，时分秒
                format = "yyyy-MM-dd hh:mm:ss";
            }
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(df.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
            return date;
        }
    }
}
