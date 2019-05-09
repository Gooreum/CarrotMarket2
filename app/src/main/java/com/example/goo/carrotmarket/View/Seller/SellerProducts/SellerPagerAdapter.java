package com.example.goo.carrotmarket.View.Seller.SellerProducts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Goo on 2019-05-05.
 */

public class SellerPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private String nick;
    SellerPagerAdapter(FragmentManager fm, int numOfTabs,String nick) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.nick = nick;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                return new SellerAllFragment();
            case 1:

                return new SellerTradingFragment();

            case 2 :

                return new SellerTradedFragment();
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}