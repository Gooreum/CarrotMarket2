package com.example.goo.carrotmarket.View.MyProfile.SetKeyword;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.goo.carrotmarket.Model.Keyword;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Goo on 2019-05-26.
 */

public class SetKeywordActivity extends AppCompatActivity implements SetKeywordView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_keyword_counts)
    TextView txt_keyword_counts;
    @BindView(R.id.edit_keyword)
    EditText edit_keyword;
    @BindView(R.id.btn_apply)
    Button btn_apply;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    SetKeywordPresenter presenter;

    SetKeywordView view;
    SetKeywordAdapter adapter;
    SetKeywordAdapter.ItemClickListener itemClickListener;
    List<Keyword> keyword;

    SessionManager sessionManager;
    HashMap<String, String> user;

    String nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword);

        ButterKnife.bind(this);

        //툴바 생성
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        presenter = new SetKeywordPresenter(this);


        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();

        nick = user.get(sessionManager.NICK).toString();

        presenter.getKeywords(compositeDisposable, nick);


        setItemClickListener();
    }


    //아이템 클릭 리스너 인터페이스 구현
    public void setItemClickListener() {
        itemClickListener = ((view1, position) -> {
//
//            presenter.sendChatRoomNum(position);
//            Intent intent = new Intent(getContext(), ChatRoomActivity.class);
//            intent.putExtra("nick", nick);
//            getContext().startActivity(intent);

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
    public void onErrorLoading(String message) {

    }

    @Override
    public void onGetResult(List<Keyword> keywords) {
        //리사이클러뷰 메니저
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SetKeywordAdapter(this, keywords, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        keyword = keywords;
    }
}