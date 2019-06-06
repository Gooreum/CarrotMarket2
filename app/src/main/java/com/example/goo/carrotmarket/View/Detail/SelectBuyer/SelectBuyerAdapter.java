package com.example.goo.carrotmarket.View.Detail.SelectBuyer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.goo.carrotmarket.Model.Hoogi;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.View.Chat.ChatList.ChatListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

/**
 * Created by Goo on 2019-06-03.
 */

public class SelectBuyerAdapter extends RecyclerView.Adapter<SelectBuyerAdapter.ViewHolder> {


    private List<Hoogi> listItems;
    private Context mContext;
    private ItemClickListener itemClickListener;

    public SelectBuyerAdapter(Context mContext, List<Hoogi> listItems, ItemClickListener itemClickListener) {
        this.listItems = listItems;
        this.mContext = mContext;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_buyer, parent, false);

        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Hoogi hoogi = listItems.get(position);

        holder.txt_nick.setText(hoogi.getBuyer().toString());
        if (hoogi.getHoogi_state() == 1) {
            holder.txt_hoogi_state.setVisibility(View.VISIBLE);
        }
        //Glide.with(mContext).load(userinfo.getProfileImage().toString()).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.dress).into(holder.profileimg);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.relative_buyer)
        RelativeLayout relative_buyer;
        @BindView(R.id.profileImg)
        CircleImageView profileImg;
        @BindView(R.id.txt_nick)
        TextView txt_nick;
        @BindView(R.id.txt_hoogi_state)
        TextView txt_hoogi_state;


        ItemClickListener itemClickListener;

        public ViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            this.itemClickListener = itemClickListener;
            relative_buyer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener extends ChatListAdapter.ItemClickListener {
        void onItemClick(View view, int position);
    }
}
