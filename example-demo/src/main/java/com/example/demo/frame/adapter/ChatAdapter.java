package com.example.demo.frame.adapter;

import android.content.Context;

import com.example.demo.R;
import com.common.adapter.base.MultiItemCommonAdapter;
import com.common.adapter.base.MultiItemTypeSupport;
import com.common.adapter.base.ViewHolder;
import com.example.demo.frame.bean.ChatMessage;

import java.util.List;

/**
 * Created by zhy on 15/9/4.
 */
public class ChatAdapter extends MultiItemCommonAdapter<ChatMessage>
{
    public ChatAdapter(Context context, List<ChatMessage> datas)
    {
        super(context, datas, new MultiItemTypeSupport<ChatMessage>()
        {
            @Override
            public int getLayoutId(int position, ChatMessage msg)
            {
                if (msg.isComMeg())
                {
                    return R.layout.test_main_chat_from_msg;
                }
                return R.layout.test_main_chat_send_msg;
            }

            @Override
            public int getViewTypeCount()
            {
                return 2;
            }
            @Override
            public int getItemViewType(int postion, ChatMessage msg)
            {
                if (msg.isComMeg())
                {
                    return ChatMessage.RECIEVE_MSG;
                }
                return ChatMessage.SEND_MSG;
            }
        });
    }

    @Override
    public void convert(ViewHolder holder, ChatMessage chatMessage)
    {

        if (holder.getLayoutId() == R.layout.test_main_chat_from_msg) {
            holder.setText(R.id.chat_from_content, chatMessage.getContent());
            holder.setText(R.id.chat_from_name, chatMessage.getName());
            holder.setImageResource(R.id.chat_from_icon, chatMessage.getIcon());

        } else if (holder.getLayoutId() == R.layout.test_main_chat_send_msg) {
            holder.setText(R.id.chat_send_content, chatMessage.getContent());
            holder.setText(R.id.chat_send_name, chatMessage.getName());
            holder.setImageResource(R.id.chat_send_icon, chatMessage.getIcon());

        }
    }
}
