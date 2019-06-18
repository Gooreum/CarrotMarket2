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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.goo.carrotmarket.Model.Chat;
import com.example.goo.carrotmarket.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        // holder.txt_date.setText(chat.getDate2().toString());
        holder.txt_date.setText(transformDate(chat.getDate2()) + "");
        holder.txt_chat_description.setText(chat.getMessage().toString());


        Glide.with(mContext).load(chat.getProfileImg().toString()).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.profileimg).into(holder.profileImg);

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

    // 날짜가 yyyymmdd 형식으로 입력되었을 경우 Date로 변경하는 메서드
    public String transformDate(String date) {
        SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        // Date로 변경하기 위해서는 날짜 형식을 yyyy-mm-dd로 변경해야 한다.
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy년MM월dd일");

        java.util.Date tempDate = null;

        try {
            // 현재 yyyymmdd로된 날짜 형식으로 java.util.Date객체를 만든다.
            tempDate = beforeFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // java.util.Date를 yyyy-mm-dd 형식으로 변경하여 String로 반환한다.
        String transDate = afterFormat.format(tempDate);

        // 반환된 String 값을 Date로 변경한다.
        // Date d = Date.valueOf(transDate);

        return transDate;
    }
}
