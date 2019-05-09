package com.example.goo.carrotmarket.View.Home.Search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.Search;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.View.Home.HomeAdapter;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by Goo on 2019-05-08.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<Product> searchList;
    private Context mContext;
    private ItemClickListener itemClickListener;

    public SearchAdapter(Context mContext, List<Product> searchList, ItemClickListener itemClickListener) {
        this.searchList = searchList;
        this.mContext = mContext;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_list, parent, false);

        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Product product = searchList.get(position);

        holder.txt_search.setText(product.getTitle().toString());

    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.txt_search)
        TextView txt_search;
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
