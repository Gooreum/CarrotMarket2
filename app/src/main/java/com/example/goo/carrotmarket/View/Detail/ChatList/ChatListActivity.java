package com.example.goo.carrotmarket.View.Detail.ChatList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goo.carrotmarket.Model.Chat;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.View.Chat.ChatRoom.ChatRoomActivity;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Goo on 2019-05-29.
 */

public class ChatListActivity extends AppCompatActivity implements ChatListView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;
    @BindView(R.id.txt_chat_list_cnt)
    TextView txt_chat_list_cnt;

    HashMap<String, String> user;

    ChatListAdapter adapter;
    ChatListAdapter.ItemClickListener itemClickListener;
    ChatListPresenter presenter;
    List<Chat> chat;

    Intent intent;
    String nick;
    int product_id;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_chat_list);

        ButterKnife.bind(this);
        //툴바 셋팅
        setToolbar();

        intent = getIntent();
        nick = intent.getStringExtra("nick");
        product_id = intent.getIntExtra("id", 0);
        presenter = new ChatListPresenter(this);

        //유저 목록 가져오기 및 새로고침 리스너 셋팅
        setPresenter();

        //2.로그인 하지 않은 상태일 때


        //리사이클러뷰 아이템 클릭 리스너
        setItemClickListener();
    }

    public void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

    }

    //유저 목록 가져오기 및 새로고침 리스너 셋팅
    public void setPresenter() {
        presenter.getChatListFromDetail(compositeDisposable, nick, product_id);


        //새로고침
        swipe_refresh.setOnRefreshListener(
                () -> presenter.getChatListFromDetail(compositeDisposable, nick, product_id)
        );
    }

    //아이템 클릭 리스너 인터페이스 구현
    public void setItemClickListener() {
        itemClickListener = ((view1, position) -> {

            Intent intent = new Intent(this, ChatRoomActivity.class);
            intent.putExtra("id", chat.get(position).getRoom_id());
            intent.putExtra("roomNum", chat.get(position).getRoom_id());
            intent.putExtra("nick", nick);
            intent.putExtra("seller", chat.get(position).getNick_seller());
            intent.putExtra("partner", chat.get(position).getUser_partner());
            Toast.makeText(this, chat.get(position).getRoom_id(), Toast.LENGTH_SHORT).show();
            startActivity(intent);

        });
    }

    @Override
    public void onResume() {

        super.onResume();
        Log.i("온 리쥼 : ", "온 리쥼");

        setPresenter();


    }


    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        Log.i("onDestroy : ", "디스트로이");
        super.onDestroy();
    }

    @Override
    public void onStop() {
        //compositeDisposable.clear();
        Log.i("온 스탑 : ", "클리어");
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("온 포즈 : ", "온포즈");
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
        swipe_refresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipe_refresh.setRefreshing(false);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, "에러", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(List<Chat> chats) {
        //리사이클러뷰 메니저
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChatListAdapter(this, chats, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        chat = chats;
        if (chat.size() > 0) {
            int chat_cnt = chat.size();
            txt_chat_list_cnt.setText(chat_cnt+"");
        }
    }

}