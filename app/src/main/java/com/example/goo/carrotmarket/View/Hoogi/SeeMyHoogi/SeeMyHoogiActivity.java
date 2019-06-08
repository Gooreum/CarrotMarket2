package com.example.goo.carrotmarket.View.Hoogi.SeeMyHoogi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goo.carrotmarket.Model.Hoogi;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-06-08.
 */

public class SeeMyHoogiActivity extends AppCompatActivity implements SeeMyHoogiView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.txt_to)
    TextView txt_to;
    @BindView(R.id.txt_hoogi)
    TextView txt_hoogi;
    @BindView(R.id.txt_product)
    TextView txt_product;
    @BindView(R.id.txt_from)
    TextView txt_from;


    Intent intent;

    SessionManager sessionManager;
    HashMap<String, String> user;

    int product_id;
    String buyer, title, seller, nick;

    SeeMyHoogiPresenter presenter;
    List<Hoogi> hoogi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_my_hoogi);
        ButterKnife.bind(this);
        setToolbar();
        intent = getIntent();
        //로그인 세션
        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();
        nick = user.get(sessionManager.NICK).toString();


        title = intent.getStringExtra("title");
        buyer = intent.getStringExtra("buyer");
        seller = intent.getStringExtra("seller");
        product_id = intent.getIntExtra("id", 0);


        hoogi = new ArrayList<>();
        presenter = new SeeMyHoogiPresenter(this);

        presenter.getHoogi(buyer ,seller , product_id);

        Toast.makeText(this, "바이어는 " + buyer + "판매자는 : " + seller + "아이디 값은 : " + product_id, Toast.LENGTH_SHORT).show();

    }

    //toolbar 셋팅
    public void setToolbar() {
        //툴바 생성
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
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
    public void onGetResult(List<Hoogi> hoogiList) {
        hoogi = hoogiList;
        Toast.makeText(this, hoogi.toString(), Toast.LENGTH_SHORT).show();

        //내가 판매자와 같다면
        if (nick.equals(seller)) {
            txt_to.setText("To." + buyer);
            txt_from.setText("From." + seller);
            txt_hoogi.setText(hoogiList.get(0).getSeller_to_buyer());
            txt_product.setText(buyer + "님과 " + title + "를 거래했어요.");
            //내가 구매자와 같다면
        } else {
            txt_to.setText("To." + seller);
            txt_from.setText("From." + buyer);
            txt_hoogi.setText(hoogiList.get(0).getBuyer_to_seller());
            txt_product.setText(seller + "님과 '" + title + "'를 거래했어요.");
        }

    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();
        finish();
    }
}