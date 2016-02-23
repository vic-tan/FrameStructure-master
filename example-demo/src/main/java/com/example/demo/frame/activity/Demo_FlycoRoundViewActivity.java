package com.example.demo.frame.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.demo.R;
import com.common.ui.base.activity.BaseActivity;
import com.common.view.roundview.RoundTextView;
import com.common.view.roundview.RoundViewDelegate;


public class Demo_FlycoRoundViewActivity extends BaseActivity {

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_flyco_round_view_main);
        findViewById(R.id.rtv_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click--->RoundTextView_1", Toast.LENGTH_SHORT).show();
            }
        });

        RoundTextView rtv_2 = (RoundTextView) findViewById(R.id.rtv_2);
        rtv_2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "LongClick--->RoundTextView_2", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        final RoundTextView rtv_3 = (RoundTextView) findViewById(R.id.rtv_3);
        rtv_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoundViewDelegate delegate = rtv_3.getDelegate();
                delegate.setBackgroundColor(
                        delegate.getBackgroundColor() == Color.parseColor("#ffffff")
                                ? Color.parseColor("#F6CE59") : Color.parseColor("#ffffff"));
            }
        });
    }


    @Override
    protected String setActionBarTitle() {
        return "公用圆角";
    }

}
