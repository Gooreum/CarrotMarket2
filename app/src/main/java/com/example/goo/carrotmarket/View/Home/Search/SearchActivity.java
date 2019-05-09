package com.example.goo.carrotmarket.View.Home.Search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.goo.carrotmarket.Model.Location;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.Search;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.View.Detail.DetailActivity;
import com.example.goo.carrotmarket.View.Detail.DetailSellerProductsAdapter;
import com.example.goo.carrotmarket.View.MyProfile.BuyList.BuyListPagerAdapter;
import com.example.goo.carrotmarket.View.SelectingLocation.FindMyLocationActivity;
import com.example.goo.carrotmarket.View.SelectingLocation.RecyclerView_LocationList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-07.
 */

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @Nullable
    @BindView(R.id.tabSelling)
    TabItem tabSelling;
    @Nullable
    @BindView(R.id.tabLocalCompay)
    TabItem tabLocalCompay;
    @Nullable
    @BindView(R.id.tabPeople)
    TabItem tabPeople;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.relative_search_filter)
    RelativeLayout relative_search_filter;
    @BindView(R.id.recyclerView_search_filter)
    RecyclerView recyclerView_search_filter;
    //@BindView(R.id.search_view)

    @BindView(R.id.relative_tab)
    RelativeLayout relative_tab;


    static SearchView search_view ;
    //뷰페이저 어댑터
    SearchPagerAdapter pagerAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);
        search_view = findViewById(R.id.search_view);

        //툴바 생성
        setToolbar();
        //탭 레이아웃 생성
        setTabLayout();

        relative_search_filter.setVisibility(View.GONE);

    }

    //툴바 생성

    public void setToolbar() {

        //툴바 생성
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

    }

    //탭 레이아웃 생성
    public void setTabLayout() {
        pagerAdapter = new SearchPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {

                } else if (tab.getPosition() == 1) {

                } else if (tab.getPosition() == 2) {

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_back, menu);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

                return true;
            default:

                return super.onOptionsItemSelected(item);
        }


    }


}
