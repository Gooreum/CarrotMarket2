package com.example.goo.carrotmarket.View.Chat.ChatList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.goo.carrotmarket.Model.Chat;
import com.example.goo.carrotmarket.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

/**
 * Created by Goo on 2019-05-22.
 */

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    private List<Chat> listItems;
    private Context mContext;
    private ItemClickListener itemClickListener;

    public ChatListAdapter(Context mContext, List<Chat> listItems, ItemClickListener itemClickListener) {
        this.listItems = listItems;
        this.mContext = mContext;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_room_list, parent, false);

        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Chat chat = listItems.get(position);

        holder.txt_nick.setText(chat.getUser_partner().toString());

       // holder.txt_location.setText(userinfo.getDong1().toString());
//        holder.txt_date.setText(userinfo.getDong1().toString());
//        holder.txt_chat_description.setText(userinfo.getDong1().toString());

        //Glide.with(mContext).load(userinfo.getProfileImage().toString()).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.dress).into(holder.profileimg);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.relative_chat)
        RelativeLayout relative_chat;
        @BindView(R.id.profileImg)
        CircleImageView profileImg;
        @BindView(R.id.txt_nick)
        TextView txt_nick;
        @BindView(R.id.txt_location)
        TextView txt_location;
        @BindView(R.id.txt_date)
        TextView txt_date;
        @BindView(R.id.txt_chat_description)
        TextView txt_chat_description;

        ItemClickListener itemClickListener;

        public ViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            this.itemClickListener = itemClickListener;
            relative_chat.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
