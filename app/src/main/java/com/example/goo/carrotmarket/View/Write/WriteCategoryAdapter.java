package com.example.goo.carrotmarket.View.Write;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.goo.carrotmarket.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Goo on 2019-04-28.
 */

public class WriteCategoryAdapter extends RecyclerView.Adapter<WriteCategoryAdapter.ViewHolder> {
    private Context mContext;
    private ItemClickListener itemClickListener;
    private List<String> arrayList;

    public WriteCategoryAdapter(Context mContext, List<String> arrayList, ItemClickListener itemClickListener) {

        this.mContext = mContext;
        this.arrayList = arrayList;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_write_category, parent, false);

        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // arrayList = arrayList;
        holder.txt_category.setText(arrayList.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.cardView_Category)
        CardView cardView_Category;

        @BindView(R.id.category)
        TextView txt_category;
        @BindView(R.id.check)
        ImageView check;


        ItemClickListener itemClickListener;

        public ViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);


            ButterKnife.bind(this, itemView);


            this.itemClickListener = itemClickListener;
            cardView_Category.setOnClickListener(this);
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

