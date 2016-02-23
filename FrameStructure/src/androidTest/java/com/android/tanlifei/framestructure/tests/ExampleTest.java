package com.android.tanlifei.framestructure.tests;

import android.test.InstrumentationTestCase;

import com.android.tanlifei.framestructure.common.TestConstants;
import com.android.tanlifei.framestructure.ui.main.GlobalApplication;
import com.common.utils.Logger;
import com.common.utils.NetUtils;

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
        //TestHttpUtils.post(UrlConstants.TEST_TWO_SUCCESS, new HashMap<String, Object>());
    }

    public void testNetWork() throws Exception {
        NetUtils.isConnected(GlobalApplication.appContext);
        Logger.i(TestConstants.TAG, NetUtils.isConnected(GlobalApplication.appContext) + "---");
    }

    public void testDensity() throws Exception {
    }
}
