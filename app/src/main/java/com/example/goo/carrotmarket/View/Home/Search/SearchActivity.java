package com.example.goo.carrotmarket.View.Home.Search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;

import android.view.MenuItem;
import android.view.View;

import android.widget.RelativeLayout;


import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.GlobalBus.Events;
import com.example.goo.carrotmarket.Util.GlobalBus.GlobalBus;

import com.squareup.otto.Subscribe;


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
    @BindView(R.id.search_view)
    SearchView searchView;

    @BindView(R.id.relative_tab)
    RelativeLayout relative_tab;


    static SearchView search_view;
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

        //검색
        search();

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

        //탭을 옮길 때 마다 프래그먼트가 새로고침이 안되고, 검색 결과를 그대로 가지고 있음.
        //새로운 검색 쿼리가 입력 되어야 새로고침이 됨.
        viewPager.setOffscreenPageLimit(3);


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

    public void search() {

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener()

        {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Events.Event1 event1 =new Events.Event1(query);

                GlobalBus.getBus().post(event1);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    @Subscribe
    public void connectEvent1(Events.Event1 event1) {
        Log.i("MyTag", event1.getMessage());

    }
}
