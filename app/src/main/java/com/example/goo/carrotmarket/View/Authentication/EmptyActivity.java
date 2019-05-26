package com.example.goo.carrotmarket.View.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.UserInfo;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.LoginRegister.RegisterActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-02.
 */

public class EmptyActivity extends AppCompatActivity implements AuthenticationView {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.nick)
    TextView nick;
    @BindView(R.id.dong)
    TextView dong;

    Intent intent;
    private List<UserInfo> user;
    private AuthenticationPresenter presenter;
    //  Button btn_PhoneLogin, btn_EmailLogin;
    SessionManager sessionManager;
    HashMap<String, String> hashUser;
    String phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        intent = getIntent();
        ButterKnife.bind(this);

        sessionManager = new SessionManager(this);
        hashUser = sessionManager.getUserDetail();
        user = new ArrayList<>();
        phoneNum = intent.getStringExtra("phone").toString();

        presenter = new AuthenticationPresenter(this);
        presenter.getData(phoneNum);

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
    public void onErrorLoading(String message) {
        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(List<UserInfo> userInfo) {
        user = userInfo;
        if (user.size() != 0) {
            String phoneNumber = user.get(0).getPhone().toString();
            String nick = user.get(0).getNick().toString();
            String profileImg = user.get(0).getProfileImage().toString();
            String city = user.get(0).getCity1().toString();
            String dong = user.get(0).getDong1().toString();
            String gu = user.get(0).getGu1().toString();
            String location1_state = user.get(0).getLocation1_state().toString();
            String city2 = user.get(0).getCity2().toString();
            String dong2 = user.get(0).getDong2().toString();
            String gu2 = user.get(0).getGu2().toString();
            String location2_state = user.get(0).getLocation2_state().toString();
            System.out.println("핸드폰 번호는 : " + user.get(0).getPhone().toString());

            sessionManager.createSession(true, nick, profileImg, city, gu, dong, location1_state, city2, gu2, dong2, location2_state);
            finish();

        } else {
            Intent intent = new Intent(EmptyActivity.this, RegisterActivity.class);
            intent.putExtra("phone", phoneNum);
            startActivity(intent);
            finish();
        }
    }
}
