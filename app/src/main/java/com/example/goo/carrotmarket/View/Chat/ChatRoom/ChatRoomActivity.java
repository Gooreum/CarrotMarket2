package com.example.goo.carrotmarket.View.Chat.ChatRoom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by Goo on 2019-05-23.
 */

public class ChatRoomActivity extends AppCompatActivity implements View.OnClickListener, ChatRoomView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerViewChat)
    RecyclerView recyclerViewChat;
    @BindView(R.id.edit_comment)
    EditText edit_comment;
    @BindView(R.id.btn_send_inactive)
    Button btn_send_inactive;
    @BindView(R.id.btn_send_active)
    Button btn_send_active;
    @BindView(R.id.txt_nick)
    TextView txt_nick;

    Intent intent;
    String project_id;

    Socket socket;
    ArrayList<String> chat_data;
    ArrayList<String> users;


    ChatRoomPresenter presenter;
    ChatRoomAdapter adapter;


    SessionManager sessionManager;
    String nick;
    HashMap<String, String> user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);

        presenter = new ChatRoomPresenter(this);
        intent = getIntent();
        //툴바 셋팅
        setToolbar();

        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();
        nick = user.get(sessionManager.NICK).toString();


        project_id = intent.getStringExtra("nick");

        chat_data = new ArrayList<>();
        users = new ArrayList<>();


        //툴바에 채팅하고 있는 상대 이름 적기
        presenter.setToolbar();

        //메세지를 한 글자라도 입력해야 전송버튼 누를 수 있도록 변경
        presenter.countCommentCharacter(edit_comment, btn_send_active, btn_send_inactive);

        //소켓 통신 준비!
        socket = presenter.setSocket(socket);
        presenter.prepareNetwork(socket, project_id, handling);


        //전송버튼 리스너
        btn_send_active.setOnClickListener(this);

    }


    private Emitter.Listener handling = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String message;
                    String nick;
                    try {

                        message = data.getString("message").toString();
                        nick = data.getString("nick").toString();

                    } catch (JSONException e) {
                        return;
                    }

                    presenter.addMessage(message, nick);

                }
            });

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.disconnect();
        socket.off("message", handling);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_chat, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case android.R.id.home:


                finish();

                return true;
            case R.id.report:


                return true;

            case R.id.noAlarm:


                return true;

            case R.id.compliment:


                return true;

            case R.id.uncompliment:


                return true;

            case R.id.block:


                return true;


            case R.id.out:


                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_active:

                presenter.sendMessage(edit_comment, nick, project_id, socket);
                break;
        }
    }


    //-----------ChatRoomView 인터페이스 구현---------------


    @Override
    public void setEditTextEmpty() {
        edit_comment.setText("");
    }

    @Override
    public void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        txt_nick.setText(nick);
    }

    @Override
    public void setAdapter(String message, String user) {
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        chat_data.add(message);
        users.add(user);
        adapter = new ChatRoomAdapter(this, chat_data, users);
        adapter.notifyItemChanged(chat_data.size() - 1);
        recyclerViewChat.setAdapter(adapter);
        recyclerViewChat.scrollToPosition(chat_data.size() - 1);
    }

    @Override
    public void showActiveButton() {
        btn_send_active.setVisibility(View.VISIBLE);
        btn_send_inactive.setVisibility(View.GONE);
    }

    @Override
    public void showInactiveButton() {
        btn_send_active.setVisibility(View.GONE);
        btn_send_inactive.setVisibility(View.VISIBLE);
    }

}
