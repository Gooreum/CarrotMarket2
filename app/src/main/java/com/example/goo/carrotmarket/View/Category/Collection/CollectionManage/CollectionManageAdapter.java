package com.example.goo.carrotmarket.View.Category.Collection.CollectionManage;

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
import com.example.goo.carrotmarket.API.ApiClient;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.UserInfo;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.View.Category.Collection.CollectionAdapter;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Goo on 2019-05-14.
 */

public class CollectionManageAdapter extends RecyclerView.Adapter<CollectionManageAdapter.ViewHolder> {

    private List<UserInfo> listItems;
    private Context mContext;
    private ItemClickListener itemClickListener;
    private ItemClickListener itemClickListener2;

    public CollectionManageAdapter(Context mContext, List<UserInfo> listItems, ItemClickListener itemClickListener, ItemClickListener itemClickListener2) {
        this.listItems = listItems;
        this.mContext = mContext;
        this.itemClickListener = itemClickListener;
        this.itemClickListener2 = itemClickListener2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection_manage, parent, false);

        return new ViewHolder(view, itemClickListener, itemClickListener2);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        UserInfo userinfo = listItems.get(position);

        holder.txt_nick.setText(userinfo.getNick());
        //holder.txt_location.setText(userinfo.getL());

        //Glide.with(mContext).load(product.getImage0().toString()).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.dress).into(holder.productThumb);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        boolean flag;
        @BindView(R.id.profileImg)
        CircleImageView profileImg;
        @BindView(R.id.nick)
        TextView txt_nick;
        @BindView(R.id.location)
        TextView txt_location;
        @BindView(R.id.relative_collect)
        RelativeLayout relative_collect;
        @BindView(R.id.relative_cancel_collect)
        RelativeLayout relative_cancel_collect;


        ItemClickListener itemClickListener;
        ItemClickListener itemClickListener2;

        public ViewHolder(View itemView, ItemClickListener itemClickListener, ItemClickListener itemClickListener2) {
            super(itemView);

            ButterKnife.bind(this, itemView);


            this.itemClickListener = itemClickListener;
            this.itemClickListener2 = itemClickListener2;
            relative_collect.setOnClickListener(this);
            relative_cancel_collect.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.relative_collect:
                    // flag = false;
                    relative_collect.setVisibility(View.GONE);
                    relative_cancel_collect.setVisibility(View.VISIBLE);
                    itemClickListener.onItemClick(v, getAdapterPosition());

                    break;

                case R.id.relative_cancel_collect:
                    //flag = true;
                    relative_collect.setVisibility(View.VISIBLE);
                    relative_cancel_collect.setVisibility(View.GONE);
                    itemClickListener2.onItemClick(v, getAdapterPosition());
            }

        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface ItemClickListener2 {
        void onItemClick(View view, int position);
    }


}
