package com.example.goo.carrotmarket.View.Category.Collection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Category.CategoryActivity;
import com.example.goo.carrotmarket.View.Category.CategoryAdapter;
import com.example.goo.carrotmarket.View.Category.CategoryPresenter;
import com.example.goo.carrotmarket.View.Detail.DetailActivity;
import com.example.goo.carrotmarket.View.Home.HomeAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-09.
 */

public class CollectionActivity extends AppCompatActivity implements CollectionView {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtCategory)
    TextView txtCategory;


    CollectionPresenter presenter;
    CollectionAdapter adapter;
    CollectionAdapter.ItemClickListener itemClickListener;

    List<Product> product;

    Intent intent;
    SessionManager sessionManager;
    HashMap<String, String> user;
    String nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        ButterKnife.bind(this);

        //툴바 선언
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        //리사이클러뷰 메니저
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        //product = new ArrayList<>();
        presenter = new CollectionPresenter(this);
        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();
        nick = user.get(sessionManager.NICK).toString();


        presenter.getProductCollection(nick);

        swipe_refresh.setOnRefreshListener(
                () -> presenter.getProductCollection(nick)
        );

        itemClickListener = ((view1, position) -> {

            String id = String.valueOf(product.get(position).getId());
            String seller = product.get(position).getSeller();
            int hide = product.get(position).getHide();
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("seller", seller);
            intent.putExtra("hide", hide);
            startActivity(intent);
            Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_collection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

                return true;

            case R.id.manage:


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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(List<Product> products) {
        product = products;
        adapter = new CollectionAdapter(this, product, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);


    }
}