package com.android.tanlifei.framestructure.tests;

import android.test.InstrumentationTestCase;

import com.android.tanlifei.framestructure.common.TestConstants;
import com.android.tanlifei.framestructure.common.TestHttpUtils;
import com.android.tanlifei.framestructure.common.constants.UrlConstants;
import com.android.tanlifei.framestructure.common.utils.Logger;
import com.android.tanlifei.framestructure.common.utils.PhoneUtils;
import com.android.tanlifei.framestructure.ui.GlobalApplication;

import java.util.HashMap;

/**
 * 单元测试例子
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class ExampleTest extends InstrumentationTestCase {


    public void test() throws Exception {
        int a = 1;
        int b = 5;
        int c = a + b;
        Logger.i(TestConstants.TAG, "" + c);
    }

    public void testHttp2() throws Exception {
        TestHttpUtils.post(UrlConstants.TEST_TWO_SUCCESS, new HashMap<String, Object>());
    }

    public void testNetWork() throws Exception {
        PhoneUtils.isNetworkOk(GlobalApplication.appContext);
        Logger.i(TestConstants.TAG, PhoneUtils.isNetworkOk(GlobalApplication.appContext) + "---");
    }

    public void testDensity() throws Exception {
        Logger.i(TestConstants.TAG, PhoneUtils.getDensity(getInstrumentation().getContext()) + "---");
    }
}
