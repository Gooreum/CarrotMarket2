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

import com.example.goo.carrotmarket.Model.Chat;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Chat.ChatRoom.ChatRoomActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.socket.emitter.Emitter;

import static com.example.goo.carrotmarket.View.Home.HomeActivity2.socket;

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
    List<Chat> chat;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    String nick;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        Log.i("온 크리에이트 : ", "chatroomList 온 크리에이트");

        //로그인 세션
        sessionManager = new SessionManager(getContext());
        user = sessionManager.getUserDetail();
        nick = user.get(sessionManager.NICK).toString();
        ButterKnife.bind(this, view);


        //툴바 설정
        setToolbar();
        //1.로그인 한 상태일 때
        chat = new ArrayList<>();
        presenter = new ChatListPresenter(this);

        //유저 목록 가져오기 및 새로고침 리스너 셋팅
        setPresenter();



        //리사이클러뷰 아이템 클릭 리스너
        setItemClickListener();

        return view;

    }


    private Emitter.Listener handling = new Emitter.Listener() {

        @Override
        public void call(final Object... args) {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    JSONObject data = (JSONObject) args[0];

                    int refresh = 0;

                    try {
                        refresh = data.getInt("refresh");
                    } catch (JSONException e) {
                        System.out.println("------------------에러 ------------------");
                        return;
                    }

                    if (refresh == 1) {
                        presenter.getChatList(compositeDisposable, nick);
                        Toast.makeText(getActivity(), "메세지 값이 갱신되었소", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    };


    //유저 목록 가져오기 및 새로고침 리스너 셋팅
    public void setPresenter() {
        if (sessionManager.isLoggIn() == true) {
            presenter.getChatList(compositeDisposable, nick);
            presenter.prepareNetwork(socket, handling, nick);

            //새로고침
            swipe_refresh.setOnRefreshListener(
                    () -> presenter.getChatList(compositeDisposable, nick)
            );
        }
    }


    //아이템 클릭 리스너 인터페이스 구현
    public void setItemClickListener() {
        itemClickListener = ((view1, position) -> {

            Intent intent = new Intent(getContext(), ChatRoomActivity.class);
            intent.putExtra("id", chat.get(position).getProduct_id());
            intent.putExtra("roomNum", chat.get(position).getRoom_id());
            intent.putExtra("nick", user.get(sessionManager.NICK).toString());
            intent.putExtra("seller", chat.get(position).getNick_seller());
            intent.putExtra("buyer", chat.get(position).getNick_buyer());
            intent.putExtra("partner", chat.get(position).getUser_partner());
            Toast.makeText(getContext(), chat.get(position).getRoom_id(), Toast.LENGTH_SHORT).show();
            getContext().startActivity(intent);

            System.out.println("id값은 : " + chat.get(position).getProduct_id());
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.i("온 리쥼 : ", "온 리쥼");
        // socket.connect();
        //  socket.emit("myChatList", nick);
        //  socket.on("myChatList", handling);

    }


    @Override
    public void onDestroy() {
        Log.i("onDestroy : ", "디스트로이");
        super.onDestroy();
        compositeDisposable.clear();
        socket.disconnect();
        socket.off("myChatList", handling);
        Toast.makeText(getContext(), "디스트로이", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {

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
    public void onGetResult(List<Chat> chats) {
        //리사이클러뷰 메니저
        chat = chats;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ChatListAdapter(getContext(), chat, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

    //툴바설정
    public void setToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        setHasOptionsMenu(true);
    }

}