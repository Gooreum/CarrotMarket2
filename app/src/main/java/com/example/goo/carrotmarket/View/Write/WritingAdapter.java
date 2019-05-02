package com.example.goo.carrotmarket.View.Write;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.View.Home.HomeAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Goo on 2019-04-27.
 */

public class WritingAdapter extends RecyclerView.Adapter<WritingAdapter.ViewHolder> {

    private Context mContext;
    private ItemClickListener itemClickListener;
    private ArrayList<Uri> arrayList;

    public WritingAdapter(Context mContext, ArrayList<Uri> arrayList, ItemClickListener itemClickListener) {

        this.mContext = mContext;
        this.itemClickListener = itemClickListener;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_writing, parent, false);

        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder_Writing_Adapter: called.");

        Picasso.with(mContext).load(arrayList.get(position).toString()).fit().centerCrop().error(R.mipmap.ic_launcher_round).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        CardView cardview_img;
        RelativeLayout relativeImg;
        ImageView close;
        ImageView img;

        //  ImageView productImg;
        ItemClickListener itemClickListener;

        public ViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            cardview_img = itemView.findViewById(R.id.cardview_img);
            relativeImg = itemView.findViewById(R.id.relativeImg);
            close = itemView.findViewById(R.id.close);
            img = itemView.findViewById(R.id.img);

            //   productImg = itemView.findViewById(R.id.productThumb);
            this.itemClickListener = itemClickListener;
            cardview_img.setOnClickListener(this);
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