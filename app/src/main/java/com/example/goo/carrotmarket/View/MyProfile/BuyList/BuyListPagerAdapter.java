package com.example.goo.carrotmarket.View.MyProfile.BuyList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Goo on 2019-05-03.
 */

public class BuyListPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    BuyListPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BuyListWaitingHoogiFragment();
            case 1:
                return new BuyListCompleteHoogiFragment();
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
