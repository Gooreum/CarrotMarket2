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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
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

    @BindView(R.id.relative_product)
    RelativeLayout relative_product;

    @BindView(R.id.relative_reserve)
    RelativeLayout relative_reserve;
    @BindView(R.id.txt_date)
    TextView txt_date;
    @BindView(R.id.txt_delete)
    TextView txt_delete;

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.price)
    TextView price;

    @BindView(R.id.recyclerViewChat)
    RecyclerView recyclerViewChat;
    @BindView(R.id.edit_comment)
    EditText edit_comment;
    @BindView(R.id.btn_send_inactive)
    Button btn_send_inactive;
    @BindView(R.id.btn_send_active)
    Button btn_send_active;
    @BindView(R.id.btn_send_first_chat)
    Button btn_send_first_chat;
    @BindView(R.id.txt_nick)
    TextView txt_nick;

    Intent intent;
    int product_id;

    Socket socket;
    ArrayList<String> chat_data;
    ArrayList<String> users;


    ChatRoomPresenter presenter;
    ChatRoomAdapter adapter;


    SessionManager sessionManager;
    String nick;
    HashMap<String, String> user;

    InputMethodManager imm;


    String roomNum;
    String seller;
    String partner;
    String date;
    String roomNumExist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);

        presenter = new ChatRoomPresenter(this);
        intent = getIntent();

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);


        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();
        nick = user.get(sessionManager.NICK).toString();
        seller = intent.getStringExtra("seller");
        partner = intent.getStringExtra("partner");

        product_id = intent.getIntExtra("id",0);

        //툴바 셋팅
        setToolbar();

        chat_data = new ArrayList<>();
        users = new ArrayList<>();


        roomNum = intent.getStringExtra("roomNum");
        Toast.makeText(this,"하하 : "+ roomNum, Toast.LENGTH_SHORT).show();

        //툴바에 채팅하고 있는 상대 이름 적기
        presenter.setToolbar();


        //소켓 통신 준비!
        socket = presenter.setSocket(socket);
        presenter.prepareNetwork(socket, roomNum, handling, handling_first_chat_complete);

        if (roomNum.equals("firstChat")) {

            presenter.countCommentCharacterFirstBtn(edit_comment);
        } else {

            presenter.countCommentCharacter(edit_comment);
        }

        setButtonListener();


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

    private Emitter.Listener handling_first_chat_complete = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];

                    try {

                        roomNum = data.getString("roomNum").toString();
                        showInactiveButton();
                        //System.out.println("방 이름은 : " + roomNum);
                        System.out.println("시발 방이름은22222222222 : " + roomNum);
                        presenter.countCommentCharacter(edit_comment);
                    } catch (JSONException e) {
                        return;
                    }

                    // presenter.addMessage(message, nick);

                }
            });

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.disconnect();
        socket.off("message", handling);
        socket.off("firstChat", handling);
    }

    @Override
    public void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        txt_nick.setText(partner);
    }

    //-----------ChatRoomView 인터페이스 구현---------------


    @Override
    public void setEditTextEmpty() {
        edit_comment.setText("");
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
        btn_send_first_chat.setVisibility(View.GONE);
    }

    @Override
    public void showInactiveButton() {
        btn_send_active.setVisibility(View.GONE);
        btn_send_inactive.setVisibility(View.VISIBLE);
        btn_send_first_chat.setVisibility(View.GONE);
    }

    @Override
    public void showActiveFirstButton() {
        btn_send_active.setVisibility(View.GONE);
        btn_send_inactive.setVisibility(View.GONE);
        btn_send_first_chat.setVisibility(View.VISIBLE);
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

                presenter.sendMessage(edit_comment, nick, roomNum, socket);
                break;

            case R.id.txt_delete:

                break;

            case R.id.btn_send_first_chat:
                roomNum = getCurrentTime("yyyyMMddHHmmssSSS");
                //String nick, Socket socket, String product_id, String nick_seller, String nick_buyer
                presenter.sendFirstMessage(roomNum, edit_comment, nick, seller, socket, product_id, seller, nick);
                break;

        }
    }


    //버튼 리스너
    public void setButtonListener() {

        btn_send_active.setOnClickListener(this);
        btn_send_first_chat.setOnClickListener(this);
        txt_delete.setOnClickListener(this);
        recyclerViewChat.setOnClickListener(this);
    }


    //화면 터치시 키보드 내리기
    public void downKeyboard() {

        imm.hideSoftInputFromWindow(edit_comment.getWindowToken(), 0);
    }


    public static String getCurrentTime(String timeFormat) {
        return new SimpleDateFormat(timeFormat).format(System.currentTimeMillis());
    }
}
