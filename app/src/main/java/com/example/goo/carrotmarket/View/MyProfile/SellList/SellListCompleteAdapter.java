package com.example.goo.carrotmarket.View.MyProfile.SellList;

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
import com.example.goo.carrotmarket.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by Goo on 2019-05-31.
 */

public class SellListCompleteAdapter extends RecyclerView.Adapter<SellListCompleteAdapter.ViewHolder> {

    private List<Product> listItems;
    private Context mContext;
    private ItemClickListener itemClickListener;


    public SellListCompleteAdapter(Context mContext, List<Product> listItems, ItemClickListener itemClickListener) {
        this.listItems = listItems;
        this.mContext = mContext;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_home, parent, false);

        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Product product = listItems.get(position);

        holder.txt_title.setText(product.getTitle());
        holder.txt_description.setText(product.getDescription());
        holder.txt_price.setText(product.getPrice() + "원");
        holder.txt_location.setText(product.getDong().toString());

        int imageCnt = product.getImageCnt();
        if (imageCnt <= 1) {
            holder.card_imageCnt.setVisibility(View.GONE);
        } else {
            holder.card_imageCnt.setVisibility(View.VISIBLE);
            holder.imgCnt.setText(product.getImageCnt() + "");
        }


        if (product.getState() == 2) {
            holder.relative_temp.setVisibility(View.VISIBLE);
            holder.cardview_traded.setVisibility(View.GONE);
            holder.cardview_reserving.setVisibility(View.VISIBLE);
        } else if (product.getState() == 3) {
            holder.relative_temp.setVisibility(View.VISIBLE);
            holder.cardview_traded.setVisibility(View.VISIBLE);
            holder.cardview_reserving.setVisibility(View.GONE);
        }else{
            holder.relative_temp.setVisibility(View.GONE);
        }


        //String uri = product.getImage0().toString();
        if (product.getImageCnt() == 0) {
            String category = product.getCategory().toString();
            switch (category) {
                case "디지털/가전":
                    Glide.with(mContext).load(R.drawable.camera).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.camera).into(holder.productThumb);
                    break;
                case "가구/인테리어":
                    Glide.with(mContext).load(R.drawable.furniture).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.furniture).into(holder.productThumb);
                    break;
                case "유아동/유아도서":
                    Glide.with(mContext).load(R.drawable.pacifier).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.pacifier).into(holder.productThumb);
                    break;
                case "생활/가공식품":
                    Glide.with(mContext).load(R.drawable.pot).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.pot).into(holder.productThumb);
                    break;
                case "여성의류":
                    Glide.with(mContext).load(R.drawable.women).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.women).into(holder.productThumb);
                    break;
                case "여성잡화":
                    Glide.with(mContext).load(R.drawable.handbag).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.handbag).into(holder.productThumb);
                    break;
                case "뷰티/미용":
                    Glide.with(mContext).load(R.drawable.cosmetic).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.cosmetic).into(holder.productThumb);
                    break;
                case "남성패션/잡화":
                    Glide.with(mContext).load(R.drawable.shirt).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.shirt).into(holder.productThumb);
                    break;
                case "스포츠/레저":
                    Glide.with(mContext).load(R.drawable.basketball).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.basketball).into(holder.productThumb);
                    break;
                case "게임/취미":
                    Glide.with(mContext).load(R.drawable.game).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.game).into(holder.productThumb);
                    break;
                case "도서/티켓/음반":
                    Glide.with(mContext).load(R.drawable.ticket).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.ticket).into(holder.productThumb);
                    break;
                case "반려동물용품":
                    Glide.with(mContext).load(R.drawable.crossbones).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.crossbones).into(holder.productThumb);
                    break;
                case "기타 중고물품":
                    Glide.with(mContext).load(R.drawable.snowman).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.snowman).into(holder.productThumb);
                    break;
                case "삽니다":
                    Glide.with(mContext).load(R.drawable.cash).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.cash).into(holder.productThumb);
                    break;

            }
            // Glide.with(mContext).load(R.drawable.dress).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.dress).into(holder.productThumb);
        } else {
            Glide.with(mContext).load(product.getImage0().toString()).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.dress).into(holder.productThumb);
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.cardview_product)
        CardView cardview_product;
        @BindView(R.id.location)
        TextView txt_location;
        @BindView(R.id.description)
        TextView txt_description;
        @BindView(R.id.price)
        TextView txt_price;
        @BindView(R.id.title)
        TextView txt_title;
        @BindView(R.id.productThumb)
        RoundedImageView productThumb;
        @BindView(R.id.card_imageCnt)
        CardView card_imageCnt;
        @BindView(R.id.imgCnt)
        TextView imgCnt;
        @BindView(R.id.relative_temp)
        RelativeLayout relative_temp;
        @BindView(R.id.cardview_traded)
        CardView cardview_traded;
        @BindView(R.id.cardview_reserving)
        CardView cardview_reserving;
        ItemClickListener itemClickListener;

        public ViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            this.itemClickListener = itemClickListener;
            cardview_product.setOnClickListener(this);
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
