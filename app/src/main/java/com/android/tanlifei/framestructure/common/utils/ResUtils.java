package com.android.tanlifei.framestructure.common.utils;

import android.graphics.drawable.Drawable;

import com.android.tanlifei.framestructure.ui.GlobalApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
            InputStreamReader in = new InputStreamReader(GlobalApplication.appContext.getResources().getAssets().open(fileName));
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


    //************************************************ Raw values************************************************************//
    public static String getFileFromRaw(int id) {
        StringBuilder s = new StringBuilder();
        try {
            InputStreamReader in = new InputStreamReader(GlobalApplication.appContext.getResources().openRawResource(id));
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

    public static String getString(int id) {
        return GlobalApplication.appContext.getResources().getString(id);
    }


    //************************************************ colors values************************************************************//

    public static int getColor(int id) {
        return GlobalApplication.appContext.getResources().getColor(id);
    }

    //************************************************ arrays values************************************************************//

    public static String[] getStringArray(int id) {
        return GlobalApplication.appContext.getResources().getStringArray(id);
    }

    public static int[] getIntArray(int id) {
        return GlobalApplication.appContext.getResources().getIntArray(id);
    }

    //************************************************ dimens values************************************************************//

    public static float getDimens(int id) {
        return GlobalApplication.appContext.getResources().getDimension(id);
    }

    //************************************************ Drawable values************************************************************//

    public static Drawable getDrawable(int id) {
        return GlobalApplication.appContext.getResources().getDrawable(id);
    }

}
