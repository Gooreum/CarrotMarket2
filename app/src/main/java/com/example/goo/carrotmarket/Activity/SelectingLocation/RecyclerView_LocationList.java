package com.example.goo.carrotmarket.Activity.SelectingLocation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.goo.carrotmarket.Activity.Main.HomeActivity;
import com.example.goo.carrotmarket.Model.Location;
import com.example.goo.carrotmarket.R;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Goo on 2019-04-13.
 */

public class RecyclerView_LocationList extends RecyclerView.Adapter<RecyclerView_LocationList.ViewHolder> {
    private List<Location> listItems;
    private Context mContext;
    private ItemClickListener itemClickListener;


    public RecyclerView_LocationList(Context mContext, List<Location> listItems, ItemClickListener itemClickListener) {
        this.listItems = listItems;
        this.mContext = mContext;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_location, parent, false);

        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Location location = listItems.get(position);
        holder.txt_location.setText(location.getLocation());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        CardView cardview_location;
        TextView txt_location;
        ItemClickListener itemClickListener;

        public ViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            cardview_location = itemView.findViewById(R.id.cardview_location);
            txt_location = itemView.findViewById(R.id.txt_location);

            this.itemClickListener = itemClickListener;
            cardview_location.setOnClickListener(this);
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