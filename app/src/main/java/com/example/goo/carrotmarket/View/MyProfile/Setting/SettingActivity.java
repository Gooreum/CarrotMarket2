package com.example.goo.carrotmarket.View.MyProfile.Setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.View.MyProfile.SellList.SellListPagerAdapter;
import com.example.goo.carrotmarket.View.MyProfile.Setting.Logout.LogoutPresenter;
import com.example.goo.carrotmarket.View.MyProfile.Setting.Logout.LogoutView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-05.
 */

public class SettingActivity extends AppCompatActivity implements LogoutView, View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.relative_logout)
    RelativeLayout relative_logout;


    private LogoutPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ButterKnife.bind(this);

        //툴바 생성
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        //프레젠터 생성
        presenter = new LogoutPresenter(this, this);


        //버튼 이벤트
        initButton();


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

    public void initButton() {
        relative_logout.setOnClickListener(this);
    }

    @Override
    public void showDialog() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relative_logout:
                presenter.showDialog(this);
                break;
        }
    }
}