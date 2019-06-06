package com.example.goo.carrotmarket.View.Hoogi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-06-06.
 */

public class HoogiActivity extends AppCompatActivity implements View.OnClickListener, HoogiView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @BindView(R.id.txt_title)
    TextView txt_title;
    @BindView(R.id.txt_nick)
    TextView txt_nick;


    @BindView(R.id.cardview_done)
    CardView cardView_done;
    @BindView(R.id.edit_hoogi)
    EditText edit_hoogi;


    Intent intent;

    SessionManager sessionManager;
    HashMap<String, String> user;

    int product_id;
    String buyer, title, seller, nick;

    HoogiPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoogi2);
        ButterKnife.bind(this);
        intent = getIntent();
        //로그인 세션
        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();
        nick = user.get(sessionManager.NICK).toString();

        presenter = new HoogiPresenter(this);


        product_id = intent.getIntExtra("id", 0);
        buyer = intent.getStringExtra("buyer");
        seller = intent.getStringExtra("seller");
        title = intent.getStringExtra("title");


        presenter.setValues(title, seller, buyer, nick);

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

        cardView_done.setOnClickListener(this);


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

            case R.id.cardview_done:

                String hoogi = edit_hoogi.getText().toString().trim();
                if (hoogi.isEmpty()) {
                    edit_hoogi.setError("내용을 작성해주세요");
                } else {
                    if (nick.equals(seller)) {
                        presenter.sellerToBuyerHoogi(nick, buyer, hoogi, product_id);
                    } else if (nick.equals(buyer)) {
                        presenter.sellerToBuyerHoogi(buyer, nick, hoogi, product_id);
                    }

                }
                break;

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
    public void setValues(String title, String seller, String buyer, String nick) {
        txt_title.setText(title);
        //내가 판매자라면
        if (nick.equals(seller)) {
            txt_nick.setText(buyer + "님과 거래가 어떠셨나요?");

            //내가 구매자라면
        } else if (nick.equals(buyer)) {
            txt_nick.setText(seller + "님과 거래가 어떠셨나요?");
        }

    }

    @Override
    public void onGetResult(String message) {
        if (message.equals("success")) {

            //1.내가 판매자라면
            if (nick.equals(seller)) {
                Toast.makeText(this, buyer + "님에게 후기를 남겼습니다.", Toast.LENGTH_SHORT).show();

                //2.내가 구매자라면
            } else if (nick.equals(buyer)) {
                Toast.makeText(this, seller + "님에게 후기를 남겼습니다.", Toast.LENGTH_SHORT).show();
            }


            finish();
        }
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();
    }
}