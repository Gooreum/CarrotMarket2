package com.example.goo.carrotmarket.View.Seller.SellerProfile.SellerHoogi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.goo.carrotmarket.Model.Hoogi;
import com.example.goo.carrotmarket.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

/**
 * Created by Goo on 2019-06-09.
 */

public class SellerHoogiAdapter extends RecyclerView.Adapter<SellerHoogiAdapter.ViewHolder> {

    private List<Hoogi> listItems;
    private Context mContext;
    private ItemClickListener itemClickListener;
    private String nick;

    public SellerHoogiAdapter(Context mContext, List<Hoogi> listItems, ItemClickListener itemClickListener, String nick) {
        this.listItems = listItems;
        this.mContext = mContext;
        this.itemClickListener = itemClickListener;
        this.nick = nick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoogi_list, parent, false);

        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Hoogi hoogi = listItems.get(position);


        //판매자가 판매자일 떄
        if (nick.equals(hoogi.getSeller())) {
            holder.txt_nick.setText(hoogi.getBuyer());
            holder.txt_hoogi.setText(hoogi.getBuyer_to_seller());

            //판매자가 구매자일 때
        } else if (nick.equals(hoogi.getBuyer())) {
            holder.txt_nick.setText(hoogi.getSeller());
            holder.txt_hoogi.setText(hoogi.getSeller_to_buyer());
        }


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.profileImg)
        CircleImageView profileImg;
        @BindView(R.id.txt_nick)
        TextView txt_nick;
        @BindView(R.id.txt_location)
        TextView txt_location;
        @BindView(R.id.txt_hoogi)
        TextView txt_hoogi;
        @BindView(R.id.txt_date)
        TextView txt_date;


        ItemClickListener itemClickListener;

        public ViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            this.itemClickListener = itemClickListener;
            profileImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.profileImg:

                    itemClickListener.onItemClick(v, getAdapterPosition());

                    break;
            }

        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}