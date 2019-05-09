package com.example.goo.carrotmarket.View.MyProfile.ConcernList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.goo.carrotmarket.View.MyProfile.SellList.SellListCompleteFragment;
import com.example.goo.carrotmarket.View.MyProfile.SellList.SellListHideFragment;
import com.example.goo.carrotmarket.View.MyProfile.SellList.SellListSellingFragment;

/**
 * Created by Goo on 2019-05-03.
 */

public class ConcernListPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    ConcernListPagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new ConcernListSellingFragment();
            case 1 :
                return new ConcernListLocalAdFragment();
            case 2 :
                return new ConcernListLocalCompFragment();
            default :
                return null;
        }


    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
