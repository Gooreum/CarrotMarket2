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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private long date = Long.parseLong(getCurrentTime("yyyyMMddHHmmssSSS"));
    String product_date2 = "0";
    long product_date = 0;

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

        product_date = date - Long.parseLong(product.getDate());
        product_date2 = "0";

        setDate(product);

        if (product.getUpdateWritingCnt() >= 1) {
            holder.txt_uploadTime.setText("끌올 " + product_date2);
        } else {
            holder.txt_uploadTime.setText(product_date2 + "");
        }

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
        @BindView(R.id.uploadTime)
        TextView txt_uploadTime;
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
    public static String getCurrentTime(String timeFormat) {
        return new SimpleDateFormat(timeFormat).format(System.currentTimeMillis());
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
        product_date2 = afterFormat.format(tempDate);

        // 반환된 String 값을 Date로 변경한다.
        // Date d = Date.valueOf(transDate);

        return product_date2;

    }

    public void setDate(Product product) {
        //1분 미만인 경우
        if (product_date < 60000) {
            product_date = product_date / 1000;
            product_date2 = product_date + "초 전";
            //1시간 미만인 경우
        } else if (product_date >= 60000 && product_date < 3600000) {
            product_date = product_date / 60000;
            product_date2 = product_date + "분 전";
            //하루 미만인 경우
        } else if (product_date >= 3600000 && product_date < 86400000) {
            product_date = product_date / 3600000;
            product_date2 = product_date + "시간 전";
            //하루 이상인 경우
        } else if (product_date > 86400000) {
            //product_date = Long.parseLong(product.getDate());
            transformDate(product.getDate());
            //product_date2 = String.valueOf(product_date);
        }
    }

}
