package com.example.goo.carrotmarket.View.Home.Search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Goo on 2019-05-07.
 */

public class SearchPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;


    SearchPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {



        switch (position) {
            case 0:

                return new SearchSellingFragment();

            case 1:

                return new SearchLocalCompanyFragment();

            case 2:

                return new SearchPeopleFragment();

            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
