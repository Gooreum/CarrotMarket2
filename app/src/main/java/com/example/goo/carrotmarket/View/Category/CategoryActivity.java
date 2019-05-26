package com.example.goo.carrotmarket.View.Category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Detail.DetailActivity;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-01.
 */

public class CategoryActivity extends AppCompatActivity implements CategoryView {
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtCategory)
    TextView txtCategory;


    CategoryPresenter presenter;
    CategoryAdapter adapter;
    CategoryAdapter.ItemClickListener itemClickListener;

    List<Product> product;


    Intent intent;

    SessionManager sessionManager;
    HashMap<String, String> user;
    String city, gu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();


        //로그인한 상태일 경우, 자신이 설정한 두개의 동네 중에서 현재 설정된 값을 '시'와 '구'의 값으로 생성하기
        if (sessionManager.isLoggIn() == true) {
            if (user.get(sessionManager.LOCATION1_STATE.toString()).equals("1")) {
                city = user.get(sessionManager.CITY.toString());
                gu = user.get(sessionManager.GU.toString());
            } else if (user.get(sessionManager.LOCATION2_STATE.toString()).equals("1")) {
                city = user.get(sessionManager.CITY2.toString());
                gu = user.get(sessionManager.GU2.toString());
            }
        //비회원인 경우
        } else {
            city = user.get(sessionManager.CITY.toString());
            gu = user.get(sessionManager.GU.toString());
        }

        //툴바에 선택한 카테고리 값 적어주기
        intent = getIntent();
        String category = intent.getStringExtra("category");
        txtCategory.setText(category);

        //프렌젠터를 생성하고, 값을 가지고 옴.
        presenter = new CategoryPresenter(this);
        presenter.getProducts(category, city, gu);

        //새로고침
        swipe_refresh.setOnRefreshListener(
                () -> presenter.getProducts(category, city, gu)
        );

        //어뎁터의 아이템 클릭 리스너 인터페이스 구현
        itemClickListener = ((view1, position) -> {

            String id = String.valueOf(product.get(position).getId());
            String seller = product.get(position).getSeller();
            Intent intent = new Intent(CategoryActivity.this, DetailActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("seller", seller);
            startActivity(intent);
            Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_category_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

                return true;

            case R.id.search:


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress() {
        swipe_refresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipe_refresh.setRefreshing(false);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(CategoryActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(List<Product> products) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CategoryAdapter(CategoryActivity.this, products, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        product = products;
    }
}