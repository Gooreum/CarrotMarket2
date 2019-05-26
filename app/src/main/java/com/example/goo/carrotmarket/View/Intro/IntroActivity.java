package com.example.goo.carrotmarket.View.Intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Home.HomeActivity2;
import com.example.goo.carrotmarket.View.SelectingLocation.FindMyLocationActivity;
import com.example.goo.carrotmarket.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroActivity extends AppCompatActivity {
    @BindView(R.id.cardview_login)
    CardView cardview_login;
    Intent intent;

    SessionManager sessionManager;
    HashMap<String, String> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //로그인 세션
        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();
        //user.get(sessionManager.DONG).toString() != null || user.get(sessionManager.DONG).toString().equals("")
        if (sessionManager.isLoggIn() == true) {
            Intent intent = new Intent(IntroActivity.this, HomeActivity2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            cardview_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(IntroActivity.this, FindMyLocationActivity.class);
                    startActivity(intent);
                }
            });
        }


    }


}
