package com.common.utils;

import android.graphics.drawable.Drawable;

import com.common.ui.base.main.BaseApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * tanlifei
 * ResUtils
 */
public class ResUtils {


    //************************************************ Assets values************************************************************//
    public static String getFileFromAssets(String fileName) {
        if (fileName.equals("")) {
            return null;
        }

        StringBuilder s = new StringBuilder("");
        try {
            InputStreamReader in = new InputStreamReader(BaseApplication.getContext().getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //************************************************ Properties values************************************************************//

    /**
     * 根据文件名字读取内部文件
     *
     * @param
     * @return
     */
    public static Properties getProperties(String fileName) {
        if (fileName.equals("")) {
            return null;
        }
        Properties props = new Properties();
        try {
            InputStream in = BaseApplication.getContext().getAssets().open(fileName);
            props.load(in);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return props;
    }


    //************************************************ Raw values************************************************************//
    public static String getFileFromRaw(int id) {
        StringBuilder s = new StringBuilder();
        try {
            InputStreamReader in = new InputStreamReader(BaseApplication.getContext().getResources().openRawResource(id));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //************************************************ string values************************************************************//

    public static String getStr( int id) {
        return BaseApplication.getContext().getResources().getString(id);
    }


    //************************************************ colors values************************************************************//

    public static int getColor(int id) {
        return BaseApplication.getContext().getResources().getColor(id);
    }

    //************************************************ arrays values************************************************************//

    public static String[] getStringArray(int id) {
        return BaseApplication.getContext().getResources().getStringArray(id);
    }

    public static int[] getIntArray( int id) {
        return BaseApplication.getContext().getResources().getIntArray(id);
    }

    //************************************************ dimens values************************************************************//

    public static float getDimens( int id) {
        return BaseApplication.getContext().getResources().getDimension(id);
    }

    //************************************************ Drawable values************************************************************//

    public static Drawable getDrawable(int id) {
        return BaseApplication.getContext().getResources().getDrawable(id);
    }

}
