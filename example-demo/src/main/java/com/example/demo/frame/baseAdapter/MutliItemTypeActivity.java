package com.example.demo.frame.baseAdapter;

import android.app.ListActivity;
import android.os.Bundle;

import com.example.demo.R;
import com.example.demo.frame.baseAdapter.adapter.ChatAdapter;
import com.example.demo.frame.baseAdapter.bean.ChatMessage;

import java.util.ArrayList;


/**
 * Created by tanlifei on 15/9/4.
 */
public class MutliItemTypeActivity extends ListActivity
{
    private ArrayList<ChatMessage> mDatas = new ArrayList<ChatMessage>();


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initDatas();
        getListView().setDivider(null);

        setListAdapter(new ChatAdapter(this,mDatas));
    }


    private void initDatas()
    {

        ChatMessage msg = null;
        msg = new ChatMessage(R.mipmap.test_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);

        msg = new ChatMessage(R.mipmap.test_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.mipmap.test_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);

    }
}
