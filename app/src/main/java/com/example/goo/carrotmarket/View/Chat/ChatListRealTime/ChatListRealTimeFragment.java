package com.example.goo.carrotmarket.View.Chat.ChatListRealTime;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import io.socket.emitter.Emitter;

import static com.example.goo.carrotmarket.View.Home.HomeActivity2.socket;

/**
 * Created by Goo on 2019-05-30.
 */

public class ChatListRealTimeFragment extends Fragment implements ChatListRealTimeView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    SessionManager sessionManager;
    HashMap<String, String> user;
    String nick;

    ChatListRealTimeAdapter adapter;
    ChatListRealTimeAdapter.ItemClickListener itemClickListener;
    ChatListRealTimePresenter presenter;
    List<Chat> chat;


     //Socket socket;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat, container, false);


        //로그인 세션
        sessionManager = new SessionManager(getContext());
        user = sessionManager.getUserDetail();
        if (sessionManager.isLoggIn() == true) {
            nick = user.get(sessionManager.NICK).toString();
            presenter = new ChatListRealTimePresenter(this);
            presenter.prepareNetwork(socket, handling, nick);
            setItemClickListener();
        }

        ButterKnife.bind(this, view);
        chat = new ArrayList<>();

        //툴바 설정
        setToolbar();
        //1.로그인 한 상태일 때



        // socket = presenter.setSocket(socket);
        //socket = homeActivity2.getSocket();
        // homeActivity2.getSocket();
        //socket = homeActivity2.getSocket();


        //2.로그인 하지 않은 상태일 때


        //리사이클러뷰 아이템 클릭 리스너


        return view;

    }

    private Emitter.Listener handling = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    JSONObject data = (JSONObject) args[0];

                    String room_id, user_partner, message, message_date, nick_buyer, nick_seller;
                    int product_id;

                    try {

                        room_id = data.getString("roomNum").toString();
                        user_partner = data.getString("user_partner").toString();
                        message = data.getString("message").toString();
                        message_date = data.getString("date").toString();
                        product_id = data.getInt("product_id");
                        nick_buyer = data.getString("nick_buyer").toString();
                        nick_seller = data.getString("nick_seller").toString();
                    } catch (JSONException e) {
                        System.out.println("------------------에러 ------------------");
                        return;
                    }

                    //System.out.println("ashfdklahjslkdjas "+ nick_buyer + " lkfsdjflksdjf" + nick_seller);
                    presenter.addMessage(room_id, user_partner, message, message_date, product_id,nick_buyer,nick_seller);

                }
            });

        }
    };

    //툴바설정
    public void setToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        setHasOptionsMenu(true);
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

            System.out.println("id값은 : " + chat.get(position).getProduct_id() );
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Ondestroy", "화면 꺼졌엉~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        socket.disconnect();
        socket.off("myChatList", handling);

    }

    @Override
    public void setAdapter(String room_id, String user_partner, String message, String date, int product_id,String nick_buyer, String nick_seller) {

        chat.add(new Chat(room_id, user_partner, message, date, product_id,nick_buyer,nick_seller));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ChatListRealTimeAdapter(getContext(), chat, itemClickListener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
