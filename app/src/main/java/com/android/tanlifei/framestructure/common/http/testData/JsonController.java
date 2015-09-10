package com.android.tanlifei.framestructure.common.http.testData;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.common.constants.UrlConstants;
import com.android.tanlifei.framestructure.common.utils.ResUtils;

/**
 * 根据URL读取本地自定义Json测试数据
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class JsonController {

    public static final String TEST_MODEL_URL = "http://wenku.baidu.com/jingpin/";
    public static final String TEST_MODEL_URL_TWO = "http://wenku.baidu.com/portal/subject/";

    public static String getLocalJson(String url) {
        String json = "";
        if (url.contains(TEST_MODEL_URL)) { // 测试模块URL
            json = testModelUrl(url);
        }else if (url.contains(TEST_MODEL_URL_TWO)) { // 测试模块URL2
            json = testModelUrlTwo(url);
        } else {
            json = ResUtils.getFileFromRaw(R.raw.common_suceess);
        }
        return json;
    }

    public static String testModelUrl(String url) {
        String json = null;
        if (url.contains(UrlConstants.TEST_LIST)) {// 目录
            json = ResUtils.getFileFromRaw(R.raw.test_list_json);
        } else if (url.contains(UrlConstants.TEST_SUCCESS)) { // 最新
            json = ResUtils.getFileFromRaw(R.raw.common_suceess);
        }
        return json;
    }

    public static String testModelUrlTwo(String url) {
        String json = null;
        if (url.contains(UrlConstants.TEST_TWO_LIST)) {// 目录
            json = ResUtils.getFileFromRaw(R.raw.test_list_json);
        } else if (url.contains(UrlConstants.TEST_TWO_SUCCESS)) { // 最新
            json = ResUtils.getFileFromRaw(R.raw.common_suceess);
        }
        return json;
    }


}
