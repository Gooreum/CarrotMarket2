package com.example.goo.carrotmarket.View.Chat.ChatRoom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.goo.carrotmarket.Model.ChatMessage;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Chat.ChatRoom.Reserve.ReserveActivity;
import com.example.goo.carrotmarket.View.Detail.DetailActivity;
import com.example.goo.carrotmarket.View.Hoogi.HoogiActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.socket.emitter.Emitter;

import static com.example.goo.carrotmarket.View.Home.HomeActivity2.socket;

/**
 * Created by Goo on 2019-05-23.
 */

public class ChatRoomActivity extends AppCompatActivity implements View.OnClickListener, ChatRoomView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.relative_product)
    RelativeLayout relative_product;
    @BindView(R.id.cardview_hoogi)
    CardView cardview_hoogi;
    @BindView(R.id.cardview_see_my_hoogi)
    CardView cardview_see_my_hoogi;


    @BindView(R.id.relative_reserve)
    RelativeLayout relative_reserve;
    @BindView(R.id.txt_date)
    TextView txt_date;
    @BindView(R.id.txt_delete)
    TextView txt_delete;

    @BindView(R.id.productThumb)
    RoundedImageView productThumb;
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

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Intent intent;
    int product_id;

    //Socket socket;
    List<ChatMessage> chat_message;
    ArrayList<String> users;
    List<Product> product;

    ChatRoomPresenter presenter;
    ChatRoomAdapter adapter;


    SessionManager sessionManager;
    String nick;
    HashMap<String, String> user;

    InputMethodManager imm;


    String roomNum, seller, buyer,partner,date,roomNumExist;

    int message_state;

    long mNow;
    Date mDate;

    SimpleDateFormat mFormat = new SimpleDateFormat("a hh:mm ");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);

        presenter = new ChatRoomPresenter(this);
        intent = getIntent();

        chat_message = new ArrayList<>();

        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();
        nick = user.get(sessionManager.NICK).toString();

        seller = intent.getStringExtra("seller");
        buyer = intent.getStringExtra("buyer");
        partner = intent.getStringExtra("partner");
        product_id = intent.getIntExtra("id", 0);
        roomNum = intent.getStringExtra("roomNum");


        System.out.println("바이어는 : " + buyer + "판매자는 : " + seller);
        //내가 판매자가 아니라면 거래후기 남기기 버튼 안보이게 처리
        if(!nick.equals(seller)){
            cardview_hoogi.setVisibility(View.GONE);
        }



        //툴바 셋팅
        setToolbar();

        //  chat_data = new ArrayList<>();
        users = new ArrayList<>();


        //툴바에 채팅하고 있는 상대 이름 적기
        presenter.setToolbar();
        presenter.getProduct(compositeDisposable, product_id);


        //소켓 통신 준비!

        presenter.prepareNetwork(socket, roomNum, handling_message, handling_first_chat_complete, handling_leave);


        if (roomNum.equals("firstChat")) {

            presenter.countCommentCharacterFirstBtn(edit_comment);
        } else {

            presenter.countCommentCharacter(edit_comment);
            //채팅메세지 불러오기
            presenter.getChatMessages(compositeDisposable, roomNum);
        }

        setButtonListener();


    }


    private Emitter.Listener handling_message = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String message;
                    String nick;
                    String message_date;
                    String message_state;
                    try {

                        message = data.getString("message").toString();
                        nick = data.getString("nick").toString();
                        message_date = data.getString("date").toString();
                        // message_state = data.getString("message_state").toString();

                    } catch (JSONException e) {
                        return;
                    }

                    presenter.addMessage(message, nick, message_date, "message");

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
                        presenter.countCommentCharacter(edit_comment);

                    } catch (JSONException e) {
                        return;
                    }
                }
            });
        }
    };

    private Emitter.Listener handling_leave = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String message;
                    String nick;
                    String message_date;
                    String message_state;
                    try {

                        message = data.getString("message").toString();
                        nick = data.getString("nick").toString();
                        message_date = data.getString("date").toString();
                        message_state = data.getString("message_state").toString();

                    } catch (JSONException e) {
                        return;
                    }

                    presenter.addMessage(message, nick, message_date, message_state);
                }
            });
        }
    };


    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();

        socket.disconnect();
        socket.off("message", handling_message);
        socket.off("firstChat", handling_first_chat_complete);
        socket.off("leave", handling_leave);

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
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void onGetResultMessages(List<ChatMessage> messages) {

        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        chat_message = messages;
        adapter = new ChatRoomAdapter(this, chat_message);
        adapter.notifyItemChanged(chat_message.size() - 1);
        recyclerViewChat.setAdapter(adapter);
        recyclerViewChat.scrollToPosition(chat_message.size() - 1);

    }

    @Override
    public void onGetResultProduct(List<Product> products) {

        product = products;
        Glide.with(this).load(products.get(0).getImage0().toString()).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.shirt).into(productThumb);
        title.setText(products.get(0).getTitle());
        location.setText(products.get(0).getDong());
        price.setText(products.get(0).getPrice() + "원");

    }

    @Override
    public void setEditTextEmpty() {
        edit_comment.setText("");
    }

    @Override
    public void setAdapter(String message, String user, String message_date, String message_state) {

        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        chat_message.add(new ChatMessage(user, message, message_date, message_state));
        adapter = new ChatRoomAdapter(this, chat_message);
        adapter.notifyItemChanged(chat_message.size() - 1);
        recyclerViewChat.setAdapter(adapter);
        recyclerViewChat.scrollToPosition(chat_message.size() - 1);

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

            case R.id.calendar:

                intent = new Intent(this, ReserveActivity.class);
                startActivity(intent);

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

                presenter.sendMessage(edit_comment, nick, getTime(), getCurrentTime("yyyyMMddHHmmssSSS"), roomNum, socket);
                break;

            case R.id.txt_delete:

                break;

            case R.id.btn_send_first_chat:
                roomNum = getCurrentTime("yyyyMMddHHmmssSSS");
                presenter.sendFirstMessage(roomNum, edit_comment, nick, seller, socket, product_id, seller, nick, getTime(), getCurrentTime("yyyyMMddHHmmssSSS"));
                break;

            case R.id.relative_product:
                intent = new Intent(this, DetailActivity.class);
                intent.putExtra("id", Integer.toString(product_id));
                intent.putExtra("hide", product.get(0).getHide());
                intent.putExtra("seller", product.get(0).getSeller());
                startActivity(intent);
                break;

            case R.id.cardview_hoogi:
                intent = new Intent(this, HoogiActivity.class);
                intent.putExtra("id", product_id);
                intent.putExtra("title", product.get(0).getTitle());
                intent.putExtra("buyer", partner);
                intent.putExtra("seller", seller);
                startActivity(intent);
                break;

            case R.id.cardview_see_my_hoogi:

                break;
        }
    }


    //버튼 리스너
    public void setButtonListener() {
        relative_product.setOnClickListener(this);
        btn_send_active.setOnClickListener(this);
        btn_send_first_chat.setOnClickListener(this);
        txt_delete.setOnClickListener(this);
        recyclerViewChat.setOnClickListener(this);
        cardview_hoogi.setOnClickListener(this);
        cardview_see_my_hoogi.setOnClickListener(this);
    }


    //화면 터치시 키보드 내리기
    public void downKeyboard() {

        imm.hideSoftInputFromWindow(edit_comment.getWindowToken(), 0);
    }


    public static String getCurrentTime(String timeFormat) {
        return new SimpleDateFormat(timeFormat).format(System.currentTimeMillis());
    }


    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }


}
