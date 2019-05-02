package com.example.goo.carrotmarket.View.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.View.Authentication.AuthenticationActivity;

/**
 * Created by Goo on 2019-04-14.
 */

public class HomeActivity extends AppCompatActivity{


    private TextView text_location;
    private Button btn_authenticate;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        intent = getIntent();
        //btn_authenticate = findViewById(R.id.btn_authentication);
       // text_location = findViewById(R.id.text_location);
        text_location.setText(intent.getStringExtra("Location").toString());


        toRegister(btn_authenticate);
    }

   private void toRegister(Button btn){

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            intent = new Intent(HomeActivity.this, AuthenticationActivity.class);
            startActivity(intent);
            }
        });
    }
}
