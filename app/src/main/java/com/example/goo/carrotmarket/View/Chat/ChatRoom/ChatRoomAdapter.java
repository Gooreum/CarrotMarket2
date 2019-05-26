package com.example.goo.carrotmarket.View.Chat.ChatRoom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-23.
 */

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<String> chat_data;
    private ArrayList<String> user;
    private HashMap<String, String> users;
    private String nick;
    private SessionManager sessionManager;

    public ChatRoomAdapter(Context mContext, ArrayList<String> chat_data, ArrayList<String> user) {

        this.mContext = mContext;
        this.chat_data = chat_data;
        this.user = user;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_room, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        sessionManager = new SessionManager(mContext);
        users = sessionManager.getUserDetail();
        nick = users.get(sessionManager.NICK).toString();

        RelativeLayout.LayoutParams rl = (RelativeLayout.LayoutParams) holder.txt_me.getLayoutParams();

        if (user.get(position).toString().equals(nick)) {
            holder.relative_chat_me.setVisibility(View.VISIBLE);
            holder.relative_chat_you.setVisibility(View.GONE);

            holder.txt_me.setText(chat_data.get(position));
            holder.user.setText(user.get(position));




        } else {
            holder.relative_chat_me.setVisibility(View.GONE);
            holder.relative_chat_you.setVisibility(View.VISIBLE);

            holder.txt_you.setText(chat_data.get(position));
            holder.user.setText(user.get(position).toString());



        }

        holder.txt_me.setLayoutParams(rl);

    }

    @Override
    public int getItemCount() {
        return chat_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.txt_me)
        TextView txt_me;
        @BindView(R.id.user)
        TextView user;
        @BindView(R.id.txt_date)
        TextView txt_date;

        @BindView(R.id.txt_you)
        TextView txt_you;
        @BindView(R.id.txt_date_you)
        TextView txt_date_you;
        @BindView(R.id.relative_chat_me)
        RelativeLayout relative_chat_me;
        @BindView(R.id.relative_chat_you)
        RelativeLayout relative_chat_you;


        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public TextView getText_chat() {
            return txt_me;
        }

        public TextView getUser() {

            return user;
        }

        public TextView getDate() {

            return txt_date;
        }


    }


}