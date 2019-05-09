package com.example.goo.carrotmarket.View.MyProfile.SellList;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Goo on 2019-05-03.
 */

public class SellListPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    SellListPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SellListSellingFragment();
            case 1:
                return new SellListCompleteFragment();
            case 2:
                return new SellListHideFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
