package com.example.goo.carrotmarket.View.LoginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Home.HomeActivity2;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Goo on 2019-05-02.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterView, View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.profileImg)
    RoundedImageView view_profileImg;
    @BindView(R.id.nick)
    EditText txt_nick;
    @BindView(R.id.done)
    TextView done;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    RegisterPresenter presenter;
    SessionManager sessionManager;
    HashMap<String, String> hashUser;

    String phone, nick, city1, gu1, dong1, profileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        //툴바 생성
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);


        //세션 매니저 생성
        sessionManager = new SessionManager(this);
        hashUser = sessionManager.getUserDetail();
        phone = getIntent().getStringExtra("phone");
        city1 = hashUser.get(sessionManager.CITY).toString();
        gu1 = hashUser.get(sessionManager.GU).toString();
        dong1 = hashUser.get(sessionManager.DONG).toString();


        //presenter 생성


        done.setOnClickListener((View.OnClickListener) this);
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

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void failToGetLocation(String message) {
        Toast.makeText(RegisterActivity.this, message.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(String message) {
        Toast.makeText(RegisterActivity.this, "회원가입이 완료 되었습니다.", Toast.LENGTH_SHORT).show();
        sessionManager.createSession(true, nick, profileImg, city1, gu1, dong1);
        Intent intent = new Intent(RegisterActivity.this, HomeActivity2.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.done:

                nick = txt_nick.getText().toString();

                profileImg = "http://18.218.21.240/CarrotMarket/productsImages/2019050115353353019729.jpg";
                presenter = new RegisterPresenter(this);
                presenter.makeMember(nick, city1, gu1, dong1, phone);

                break;
        }
    }
}
