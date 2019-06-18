package com.example.goo.carrotmarket.View.Chat.ChatRoom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.goo.carrotmarket.Model.ChatMessage;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Goo on 2019-05-23.
 */

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ViewHolder> {
    private Context mContext;
    private List<ChatMessage> chat_message;
    private ArrayList<String> user;
    private HashMap<String, String> users;
    private String nick;
    private SessionManager sessionManager;
    private ItemClickListener itemClickListener;


    public ChatRoomAdapter(Context mContext, List<ChatMessage> chat_message, ItemClickListener itemClickListener) {

        this.mContext = mContext;
        this.chat_message = chat_message;
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_room, parent, false);

        return new ViewHolder(view,itemClickListener);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        sessionManager = new SessionManager(mContext);
        users = sessionManager.getUserDetail();
        nick = users.get(sessionManager.NICK).toString();
        String url = "http://54.180.32.57/CarrotMarket/productImages/20190511043543325746955.jpg";
        // holder.txt_date.setText(chat_message.get(position).getDate());

        RelativeLayout.LayoutParams rl = (RelativeLayout.LayoutParams) holder.txt_me.getLayoutParams();

        if (chat_message.get(position).getNick().equals(nick)) {
            holder.relative_chat_me.setVisibility(View.VISIBLE);
            holder.relative_chat_you.setVisibility(View.GONE);

            holder.txt_me.setText(chat_message.get(position).getMessage());
            holder.user.setText(chat_message.get(position).getNick());
            holder.txt_date.setText(chat_message.get(position).getDate());

        } else {
            holder.relative_chat_me.setVisibility(View.GONE);
            holder.relative_chat_you.setVisibility(View.VISIBLE);

            holder.txt_you.setText(chat_message.get(position).getMessage());
            holder.user.setText(chat_message.get(position).getNick());
            holder.txt_date_you.setText(chat_message.get(position).getDate());
            Glide.with(mContext).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.furniture).into(holder.profileImg);

        }

        holder.txt_me.setLayoutParams(rl);

    }

    @Override
    public int getItemCount() {
        return chat_message.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.txt_me)
        TextView txt_me;
        @BindView(R.id.user)
        TextView user;
        @BindView(R.id.txt_date)
        TextView txt_date;
        @BindView(R.id.profile_img)
        CircleImageView profileImg;
        @BindView(R.id.txt_you)
        TextView txt_you;
        @BindView(R.id.txt_date_you)
        TextView txt_date_you;
        @BindView(R.id.relative_chat_me)
        RelativeLayout relative_chat_me;
        @BindView(R.id.relative_chat_you)
        RelativeLayout relative_chat_you;
        ItemClickListener itemClickListener;

        public ViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            this.itemClickListener = itemClickListener;
            profileImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
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

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}