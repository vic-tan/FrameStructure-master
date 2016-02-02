package com.example.localinterface;

import android.content.Context;

import com.constants.fixed.UrlConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanlifei on 16/1/28.
 */
public class JsonReader {

    //本地自定义json常量
    public static final String CUSTOM_JSON_FOLDER = "customjson";//asset子文件夹名
    private static volatile JsonReader instance = null;
    private static Map<String, JsonReaderBean> jsonReader = new HashMap<String, JsonReaderBean>();

    private JsonReader() {
    }

    public static JsonReader getInstance() {
        if (instance == null) {
            synchronized (JsonReader.class) {
                if (instance == null) {
                    instance = new JsonReader();
                    addJsonReader(jsonReader);
                }
            }
        }
        return instance;
    }

    /**
     * 读取本地文件开关,上线时注释下面的代码即可
     */
    private static void addJsonReader(Map<String, JsonReaderBean> jsonReader) {
        AddJsonReaderByCommon.addTestUrl(jsonReader);//公用模块
        AddJsonReaderByTest1.addTestUrl(jsonReader);//模块一
    }


    /**
     * 读取本地文件内容
     *
     * @param context
     * @param url
     * @return
     */
    public String getJsonReaderFileContent(Context context, String url) {
        if (null != url && "".equals(url) || null == jsonReader.get(url)) {
            return getFileFromAssets(context, jsonReader.get(UrlConstants.ERROR).getFileName());
        } else {
            return getFileFromAssets(context, jsonReader.get(url).getFileName());
        }

    }

    /**
     * 是不读取本地数据
     *
     * @param url
     * @return true 时表示读取本地数据，false 请求网络接口
     */
    public boolean getJsonReader(String url) {
        if (null == jsonReader.get(url)) {
            return false;
        } else {
            return jsonReader.get(url).isReader();
        }
    }

    /**
     * 读取本地文件数据
     *
     * @param context
     * @param fileName
     * @return
     */
    private String getFileFromAssets(Context context, String fileName) {
        if (fileName.equals("")) {
            return null;
        }
        StringBuilder s = new StringBuilder("");
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(CUSTOM_JSON_FOLDER + File.separator + fileName));
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

}
