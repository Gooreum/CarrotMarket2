package com.example.goo.carrotmarket.View.Chat.ChatRoom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/*import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;*/


/**
 * Created by Goo on 2019-05-23.
 */

public class ChatRoomActivity extends AppCompatActivity implements View.OnClickListener {
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


    private boolean isConnected;
    ChatRoomAdapter adapter;
    SessionManager sessionManager;
    String nick;
    HashMap<String, String> user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);
        intent = getIntent();
        //툴바 셋팅
        setToolbar();

        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();
        nick = user.get(sessionManager.NICK).toString();



        project_id = intent.getStringExtra("nick");


        chat_data = new ArrayList<>();
        users = new ArrayList<>();

        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));

        //툴바에 채팅하고 있는 상대 이름 적기
        setToolbarText(project_id);

        //메세지를 한 글자라도 입력해야 전송버튼 누를 수 있도록 변경
        countCommentCharacter();

        //소켓 통신 준비!
        setSocket();

        //전송버튼 리스너
        btn_send_active.setOnClickListener(this);

    }

    public void setToolbarText(String nick) {
        txt_nick.setText(nick);
    }

    public void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
    }


    public void setSocket() {
        try {

            socket = IO.socket("http://54.180.32.57:3000/chat");

        } catch (URISyntaxException e) {
            Log.d("error", "onCreate : " + e.toString());
        }

        socket.connect();
        socket.emit("roomNum", project_id);
        socket.on("message", handling);
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
                    addMessage(message, nick);


                }
            });

        }
    };


    public void send_message() {
        String message = edit_comment.getText().toString().trim();
        // edit_comment.setTag("");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("message", message);
            jsonObject.put("nick", nick);
            jsonObject.put("roomNum", project_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        socket.emit("message", jsonObject);
        edit_comment.setText("");
    }

    public void addMessage(String message, String user) {
        chat_data.add(message);
        users.add(user);
        adapter = new ChatRoomAdapter(this, chat_data, users);
        adapter.notifyItemChanged(chat_data.size() - 1);
        recyclerViewChat.setAdapter(adapter);
        recyclerViewChat.scrollToPosition(chat_data.size() - 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.disconnect();
        socket.off("message", handling);
    }

    public void countCommentCharacter() {
        edit_comment.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력되는 텍스트에 변화가 있을 때 호출된다.
                if (s.length() == 0) {
                    btn_send_active.setVisibility(View.GONE);
                    btn_send_inactive.setVisibility(View.VISIBLE);
                } else {
                    btn_send_active.setVisibility(View.VISIBLE);
                    btn_send_inactive.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // 입력이 끝났을 때 호출된다.
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에 호출된다.
            }
        });
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
                send_message();
                break;
        }
    }
}
