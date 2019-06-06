package com.example.goo.carrotmarket.View.Detail.SelectBuyer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.goo.carrotmarket.Model.Hoogi;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Hoogi.HoogiActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-06-03.
 */

public class SelectBuyerActivity extends AppCompatActivity implements View.OnClickListener, SelectBuyerView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.relative_product)
    RelativeLayout relative_product;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recyclerView_buyer)
    RecyclerView recyclerView_buyer;
    @BindView(R.id.recyclerView_chat_list)
    RecyclerView recyclerView_chat_list;
    @BindView(R.id.cardview_look_for_buyer)
    CardView cardview_look_for_buyer;
    @BindView(R.id.txt_not_now)
    TextView txt_not_now;
    @BindView(R.id.productThumb)
    RoundedImageView productThumb;

    String product_title, product_image, nick;
    int product_id;

    SelectBuyerPresenter presenter;
    Intent intent;


    SessionManager sessionManager;
    HashMap<String, String> user;

    List<Hoogi> list;

    SelectBuyerAdapter adapter;
    SelectBuyerAdapter.ItemClickListener itemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellect_buyer);
        ButterKnife.bind(this);
        intent = getIntent();
        //툴바 설정
        setToolbar();
        //버튼 클릭 리스너
        setButtonListener();

        //로그인 세션
        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();
        nick = user.get(sessionManager.NICK).toString();


        product_id = intent.getIntExtra("id", 0);
        product_title = intent.getStringExtra("title");
        product_image = intent.getStringExtra("product_image");


        presenter = new SelectBuyerPresenter(this);
        presenter.getProduct(product_image, product_title);
        presenter.getBuyerList(nick, product_id);


        //리사이클러뷰 아이템 클릭 리스너
        itemClickListener = ((view1, position) -> {


            Intent intent = new Intent(this, HoogiActivity.class);
            intent.putExtra("id", product_id);
            intent.putExtra("buyer", list.get(position).getBuyer().toString());
            intent.putExtra("title", product_title);
            intent.putExtra("seller", list.get(position).getSeller().toString());
            startActivity(intent);


        });

    }

    //툴바 설정
    public void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

    }

    //버튼 클릭 리스너
    public void setButtonListener() {
        relative_product.setOnClickListener(this);
        cardview_look_for_buyer.setOnClickListener(this);
        txt_not_now.setOnClickListener(this);

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
            case R.id.relative_product:
                finish();
                break;

            case R.id.cardview_look_for_buyer:
                Intent intent = new Intent(this, HoogiActivity.class);
                startActivity(intent);
                break;

            case R.id.txt_not_now:
                finish();
                break;
        }
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetProductResult(String product_image, String product_title) {
        Glide.with(this).load(product_image).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.ic_person_outline_black_24dp).into(productThumb);
        title.setText(product_title);
    }

    @Override
    public void moveToChatList(int product_id) {

    }

    @Override
    public void onGetBuyerResult(List<Hoogi> lists) {
        list = lists;
        recyclerView_buyer.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SelectBuyerAdapter(this, list, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView_buyer.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getBuyerList(nick, product_id);
    }
}
