package com.example.goo.carrotmarket.View.Chat.ChatList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.goo.carrotmarket.Model.UserInfo;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Chat.ChatRoom.ChatRoomActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Goo on 2019-04-24.
 */

public class ChatListFragment extends Fragment implements ChatListView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;

    SessionManager sessionManager;
    HashMap<String, String> user;

    ChatListAdapter adapter;
    ChatListAdapter.ItemClickListener itemClickListener;
    ChatListPresenter presenter;
    List<UserInfo> userinfo;

    CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat, container, false);


        //로그인 세션
        sessionManager = new SessionManager(getContext());
        user = sessionManager.getUserDetail();
        String nick = user.get(sessionManager.DONG).toString();
        ButterKnife.bind(this, view);


        //툴바 설정
        setToolbar();
        //1.로그인 한 상태일 때
        userinfo = new ArrayList<>();
        presenter = new ChatListPresenter(this);

        //유저 목록 가져오기 및 새로고침 리스너 셋팅
        setPresenter();

        //2.로그인 하지 않은 상태일 때


        //리사이클러뷰 아이템 클릭 리스너
        setItemClickListener();

        return view;

    }
    //유저 목록 가져오기 및 새로고침 리스너 셋팅
    public void setPresenter() {
        presenter.getUsers(compositeDisposable);


        //새로고침
        swipe_refresh.setOnRefreshListener(
                () -> presenter.getUsers(compositeDisposable)
        );
    }

    //아이템 클릭 리스너 인터페이스 구현
    public void setItemClickListener() {
        itemClickListener = ((view1, position) -> {
            // int nick_id = userinfo.get(position).getNickId():
            String nick = userinfo.get(position).getNick();
            //String seller = sellingProducts.get(position).getSeller();
            presenter.sendChatRoomNum(position);
            Intent intent = new Intent(getContext(), ChatRoomActivity.class);
            intent.putExtra("nick", nick);
            getContext().startActivity(intent);

        });
    }

    //툴바설정
    public void setToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {

        super.onResume();
        Log.i("온 리쥼 : ", "온 리쥼");

        //유저 목록 가져오기 및 새로고침 리스너 셋팅
        if (sessionManager.isLoggIn() == true) {

            setPresenter();

        }
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
    public void showProgress() {
        swipe_refresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipe_refresh.setRefreshing(false);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getContext(), "에러", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(List<UserInfo> userinfos) {
        //리사이클러뷰 메니저
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ChatListAdapter(getContext(), userinfos, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        userinfo = userinfos;
    }
}