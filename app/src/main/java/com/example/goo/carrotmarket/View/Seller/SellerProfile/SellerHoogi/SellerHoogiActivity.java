package com.example.goo.carrotmarket.View.Seller.SellerProfile.SellerHoogi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.goo.carrotmarket.Model.Hoogi;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.View.Seller.SellerProfile.SellerProfileActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-06-09.
 */

public class SellerHoogiActivity extends AppCompatActivity implements SellerHoogiView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    String nick;
    com.example.goo.carrotmarket.Util.ToolBar toolBar = new com.example.goo.carrotmarket.Util.ToolBar();

    SellerHoogiAdapter adapter;
    SellerHoogiPresenter presenter;
    SellerHoogiAdapter.ItemClickListener itemClickListener;
    Intent intent;
    List<Hoogi> hoogi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoogi_list);
        intent = getIntent();
        ButterKnife.bind(this);
        //툴바 생성
        toolBar.setToolbar(toolbar, this);
        nick = intent.getStringExtra("nick");
        presenter = new SellerHoogiPresenter(this);


        Toast.makeText(this, nick, Toast.LENGTH_SHORT).show();
        presenter.getHoogi(nick);

        //리사이클러뷰 아이템 클릭 리스너
        itemClickListener = ((view1, position) -> {

            Intent intent = new Intent(this, SellerProfileActivity.class);
            if (nick.equals(hoogi.get(position).getSeller())) {
                intent.putExtra("nick", hoogi.get(position).getBuyer());
            } else if (nick.equals(hoogi.get(position).getBuyer())) {
                intent.putExtra("nick", hoogi.get(position).getSeller());
            }
            startActivity(intent);
        });

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
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onGetResult(List<Hoogi> hoogiList) {
        hoogi = hoogiList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SellerHoogiAdapter(this, hoogiList, itemClickListener, nick);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();
    }
}