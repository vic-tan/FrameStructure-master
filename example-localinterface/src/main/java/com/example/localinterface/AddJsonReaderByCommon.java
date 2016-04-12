package com.example.localinterface;

import com.constants.fixed.UrlConstants;

import java.util.Map;

/**
 * Created by tanlifei on 16/1/28.
 */
public class AddJsonReaderByCommon {
    /**
     * 公用模块,上线时注释下面的代码即可
     */
    public static void addTestUrl(Map<String, JsonReaderBean> jsonReader) {
        jsonReader.put(UrlConstants.SUCCESS, new JsonReaderBean(R.raw.common_suceess, true));
        jsonReader.put(UrlConstants.ERROR, new JsonReaderBean(R.raw.common_error, true));
        jsonReader.put(UrlConstants.APP_VERSION_UPDATE, new JsonReaderBean(R.raw.version_udpate, true));
    }

}
