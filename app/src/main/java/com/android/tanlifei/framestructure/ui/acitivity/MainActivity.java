package com.android.tanlifei.framestructure.ui.acitivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.common.constants.UrlConstants;
import com.android.tanlifei.framestructure.common.constants.enumConstants.RequestStatus;
import com.android.tanlifei.framestructure.common.http.RequestTask;
import com.android.tanlifei.framestructure.common.utils.Logger;


public class MainActivity extends Activity {

    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (RequestStatus.RequestStatus(msg.what)) {
                case SUCCESS:
                    Logger.i("aa");
                    break;
                case FAILURE:
                    Logger.i("Logger000");
                    break;
            }

            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestTask.post(UrlConstants.ASK_ASKING_HOTEST_LIST, null, handler);
    }

}
