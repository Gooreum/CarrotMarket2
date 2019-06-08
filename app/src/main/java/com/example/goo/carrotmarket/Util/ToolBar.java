package com.example.goo.carrotmarket.Util;

import android.support.v7.app.AppCompatActivity;

import com.example.goo.carrotmarket.R;

/**
 * Created by Goo on 2019-06-09.
 */

public class ToolBar {


    public void setToolbar(android.support.v7.widget.Toolbar toolbar, AppCompatActivity appCompatActivity) {

        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

    }
}
