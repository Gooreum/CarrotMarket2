package com.example.goo.carrotmarket.View.Home.Search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.UserInfo;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.View.Home.HomeAdapter;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

/**
 * Created by Goo on 2019-05-07.
 */

public class SearchPeopleAdapter extends RecyclerView.Adapter<SearchPeopleAdapter.ViewHolder> {

    private List<UserInfo> listItems;
    private Context mContext;
    private ItemClickListener itemClickListener;


    public SearchPeopleAdapter(Context mContext, List<UserInfo> listItems, ItemClickListener itemClickListener) {
        this.listItems = listItems;
        this.mContext = mContext;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_user, parent, false);

        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        UserInfo userInfo = listItems.get(position);

        holder.txt_nick.setText(userInfo.getNick());
        holder.txt_dong.setText(userInfo.getDong1());
        Glide.with(mContext).load(userInfo.getProfileImg().toString()).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.ic_person_outline_black_24dp).into(holder.profileImg);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.txt_nick)
        TextView txt_nick;
        @BindView(R.id.txt_dong)
        TextView txt_dong;
        @BindView(R.id.profileImg)
        CircleImageView profileImg;
        @BindView(R.id.relative_search)
        RelativeLayout relative_search;

        ItemClickListener itemClickListener;

        public ViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            this.itemClickListener = itemClickListener;
            relative_search.setOnClickListener(this);
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