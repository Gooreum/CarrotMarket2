package com.example.goo.carrotmarket.View.MyProfile.ConcernList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.View.MyProfile.SellList.SellListPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-03.
 */

public class ConcernListActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @Nullable
    @BindView(R.id.tabSelling)
    TabItem tabSelling;
    @Nullable
    @BindView(R.id.tabLocalAd)
    TabItem tabLocalAd;
    @Nullable
    @BindView(R.id.tabLocalCompay)
    TabItem tabLocalCompay;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    ConcernListPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concern_list);

        ButterKnife.bind(this);


        //툴바생성
        setToolbar();

        //탭레이아웃 생성
        setTabLayout();


    }

    //toolbar 셋팅
    public void setToolbar(){
        //툴바 생성
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
    }

    //탭 레이아웃 셋팅
    public void setTabLayout(){
        adapter = new ConcernListPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0){

                }else if(tab.getPosition() == 1){

                }else if(tab.getPosition() == 2){

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

