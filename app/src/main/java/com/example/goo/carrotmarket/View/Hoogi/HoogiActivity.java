package com.example.goo.carrotmarket.View.Hoogi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Goo on 2019-06-06.
 */

public class HoogiActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.image_bad)
    CircleImageView image_bad;
    @BindView(R.id.image_bad_color)
    CircleImageView image_bad_color;
    @BindView(R.id.image_soso)
    CircleImageView image_soso;
    @BindView(R.id.image_soso_color)
    CircleImageView image_soso_color;
    @BindView(R.id.image_happy)
    CircleImageView image_happy;
    @BindView(R.id.image_happy_color)
    CircleImageView image_happy_color;

    @BindView(R.id.relative_bad_hoogi)
    RelativeLayout relative_bad_hoogi;
    @BindView(R.id.relative_soso_hoogi)
    RelativeLayout relative_soso_hoogi;
    @BindView(R.id.relative_happy_hoogi)
    RelativeLayout relative_happy_hoogi;

    Intent intent;

    SessionManager sessionManager;
    HashMap<String, String> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoogi);
        ButterKnife.bind(this);
        //로그인 세션
        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();

        setToolbar();
        setButtonListener();
    }


    //툴바 설정
    public void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

    }

    public void setButtonListener() {
        image_bad.setOnClickListener(this);
        image_bad_color.setOnClickListener(this);
        image_soso.setOnClickListener(this);
        image_soso_color.setOnClickListener(this);
        image_happy.setOnClickListener(this);
        image_happy_color.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //내 게시글일 떄 숨기기/숨기기 내 게시글 전용 메뉴 아이템 띄워주기
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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_bad:
                image_bad.setVisibility(View.GONE);
                image_bad_color.setVisibility(View.VISIBLE);
                image_soso.setVisibility(View.VISIBLE);
                image_soso_color.setVisibility(View.GONE);
                image_happy.setVisibility(View.VISIBLE);
                image_happy_color.setVisibility(View.GONE);

                relative_bad_hoogi.setVisibility(View.VISIBLE);
                relative_soso_hoogi.setVisibility(View.GONE);
                relative_happy_hoogi.setVisibility(View.GONE);

                break;

            case R.id.image_bad_color:


                break;

            case R.id.image_soso:

                image_bad.setVisibility(View.VISIBLE);
                image_bad_color.setVisibility(View.GONE);
                image_soso.setVisibility(View.GONE);
                image_soso_color.setVisibility(View.VISIBLE);
                image_happy.setVisibility(View.VISIBLE);
                image_happy_color.setVisibility(View.GONE);

                relative_bad_hoogi.setVisibility(View.GONE);
                relative_soso_hoogi.setVisibility(View.VISIBLE);
                relative_happy_hoogi.setVisibility(View.GONE);

                break;

            case R.id.image_soso_color:


                break;

            case R.id.image_happy:

                image_bad.setVisibility(View.VISIBLE);
                image_bad_color.setVisibility(View.GONE);
                image_soso.setVisibility(View.VISIBLE);
                image_soso_color.setVisibility(View.GONE);
                image_happy.setVisibility(View.GONE);
                image_happy_color.setVisibility(View.VISIBLE);

                relative_bad_hoogi.setVisibility(View.GONE);
                relative_soso_hoogi.setVisibility(View.GONE);
                relative_happy_hoogi.setVisibility(View.VISIBLE);

                break;

            case R.id.image_happy_color:


                break;
        }
    }
}