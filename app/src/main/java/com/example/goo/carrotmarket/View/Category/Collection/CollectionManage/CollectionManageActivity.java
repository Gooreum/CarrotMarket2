package com.example.goo.carrotmarket.View.Category.Collection.CollectionManage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.UserInfo;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Category.Collection.CollectionAdapter;
import com.example.goo.carrotmarket.View.Category.Collection.CollectionPresenter;
import com.example.goo.carrotmarket.View.Detail.DetailActivity;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-14.
 */

public class CollectionManageActivity extends AppCompatActivity implements CollectionManageView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.progress)
    ProgressBar progress;

    CollectionManagePresenter presenter;
    CollectionManageAdapter adapter;
    CollectionManageAdapter.ItemClickListener itemClickListener, itemClickListener2;

    List<UserInfo> userinfo;

    Intent intent;
    SessionManager sessionManager;
    HashMap<String, String> user;
    String nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_manage);

        ButterKnife.bind(this);


        //툴바 선언
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);


        //product = new ArrayList<>();
        presenter = new CollectionManagePresenter(this);
        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();
        nick = user.get(sessionManager.NICK).toString();


        presenter.getCollectionUserList(nick);

        //모아보기 추가하기
        itemClickListener = ((view1, position) -> {
            presenter.changeCollectState(userinfo.get(position).getNick(), nick, 1);

        });
        //모아보기 해제하기
        itemClickListener2 = ((view1, position) -> {
            presenter.changeCollectState(userinfo.get(position).getNick(), nick, 2);
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

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        //swipe_refresh.setRefreshing(false);
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(List<UserInfo> userinfos) {

        userinfo = userinfos;
        //리사이클러뷰 메니저
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CollectionManageAdapter(this, userinfos, itemClickListener, itemClickListener2);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onGetCollectResult(String message) {
        if (message.equals("1")) {
            Snackbar.make(getWindow().getDecorView().getRootView(), "모아보기에 추가되었습니다.", Snackbar.LENGTH_SHORT).show();
        } else if (message.equals("2")) {
            Snackbar.make(getWindow().getDecorView().getRootView(), "모아보기에서 해제되었습니다.", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(getWindow().getDecorView().getRootView(), "네트워크 오류", Snackbar.LENGTH_SHORT).show();
        }
    }
}