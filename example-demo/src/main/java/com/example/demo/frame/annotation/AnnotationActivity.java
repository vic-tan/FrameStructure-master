package com.example.demo.frame.annotation;

import android.os.Bundle;
import android.widget.TextView;

import com.common.ui.base.activity.BaseActivity;
import com.common.utils.ViewFindUtils;
import com.example.demo.R;


//@EActivity(R.layout.test_activity_annotation)
public class AnnotationActivity extends BaseActivity {

   // @ViewById(R.id.tv_title)
    TextView test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_annotation);
        test = (TextView) ViewFindUtils.find(this, R.id.tv_title);
        init();
    }

    @Override
    protected String setActionBarTitle() {
        return "注解";
    }


   // @AfterViews
    void init() {
        test.setText("aa");
    }

}
