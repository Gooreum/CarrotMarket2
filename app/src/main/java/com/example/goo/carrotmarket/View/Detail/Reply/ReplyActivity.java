package com.example.goo.carrotmarket.View.Detail.Reply;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.goo.carrotmarket.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReplyActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_comment)
    EditText edit_comment;
    @BindView(R.id.btn_send_inactive)
    Button btn_send_inactive;
    @BindView(R.id.btn_send_active)
    Button btn_send_active;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        ButterKnife.bind(this);
        //툴바 설정
        setToolbar();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Company> companies = new ArrayList<>();
        ArrayList<Product> googleProducts = new ArrayList<>();
        googleProducts.add(new Product("AdSens"));
        googleProducts.add(new Product("AdSens2"));
        googleProducts.add(new Product("AdSens3"));
        googleProducts.add(new Product("AdSens4"));
        googleProducts.add(new Product("AdSens5"));
        googleProducts.add(new Product("AdSens6"));
        googleProducts.add(new Product("AdSens7"));
        googleProducts.add(new Product("AdSens8"));
        googleProducts.add(new Product("AdSens9"));

        Company google = new Company("Google", googleProducts);
        companies.add(google);

        ArrayList<Product> microsoftProducts = new ArrayList<>();
        microsoftProducts.add(new Product("Window"));
        microsoftProducts.add(new Product("Window2"));
        microsoftProducts.add(new Product("Window3"));
        microsoftProducts.add(new Product("Window4"));
        microsoftProducts.add(new Product("Window5"));
        microsoftProducts.add(new Product("Window6"));
        microsoftProducts.add(new Product("Window7"));
        microsoftProducts.add(new Product("Window8"));
        microsoftProducts.add(new Product("Window9"));

        Company microSoft = new Company("MicroSoft", microsoftProducts);
        companies.add(microSoft);

        ProductAdapter adapter = new ProductAdapter(companies);
        recyclerView.setAdapter(adapter);


        countCommentCharacter();
    }

    //툴바 설정
    public void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

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

    public void countCommentCharacter() {
        edit_comment.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력되는 텍스트에 변화가 있을 때 호출된다.
                if (s.length() == 0) {
                    btn_send_active.setVisibility(View.GONE);
                    btn_send_inactive.setVisibility(View.VISIBLE);
                } else {
                    btn_send_active.setVisibility(View.VISIBLE);
                    btn_send_inactive.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // 입력이 끝났을 때 호출된다.
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에 호출된다.
            }
        });
    }

}
