package com.example.goo.carrotmarket.View.Intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Home.HomeActivity2;
import com.example.goo.carrotmarket.View.SelectingLocation.FindMyLocationActivity;
import com.example.goo.carrotmarket.R;

import java.util.HashMap;

public class IntroActivity extends AppCompatActivity {
    private Button btnLogin;
    Intent intent;

    SessionManager sessionManager;
    HashMap<String, String> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //로그인 세션
        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();

        if (user.get(sessionManager.DONG).toString() != null) {
            Intent intent = new Intent(IntroActivity.this, HomeActivity2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }


        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(IntroActivity.this, FindMyLocationActivity.class);
                startActivity(intent);
            }
        });

    }


}
