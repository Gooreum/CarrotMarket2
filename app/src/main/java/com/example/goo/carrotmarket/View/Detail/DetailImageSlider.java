package com.example.goo.carrotmarket.View.Detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.goo.carrotmarket.Model.Product;

import com.example.goo.carrotmarket.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Goo on 2019-05-01.
 */

public class DetailImageSlider extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private List<Product> arrayList;
    private Context context;

    public DetailImageSlider(List<Product> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;

    }

    @Override
    public int getCount() {
        return arrayList.get(0).getImageCnt();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_slider_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        if (arrayList.get(0).imageList().size() == 0) {

            view.setVisibility(View.GONE);

        } else {

            try {
                Picasso.with(context).load(arrayList.get(0).imageList().get(position)).fit().centerCrop().error(R.drawable.dress).into(imageView);
            } catch (Exception e) {
                Picasso.with(context).load(R.drawable.dress).fit().centerCrop().error(R.drawable.dress).into(imageView);
            }

        }


        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
