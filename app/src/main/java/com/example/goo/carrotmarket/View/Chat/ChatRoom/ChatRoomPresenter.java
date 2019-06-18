package com.example.goo.carrotmarket.View.Chat.ChatRoom;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.goo.carrotmarket.API.ApiClientNodeJs;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.ChatMessage;
import com.example.goo.carrotmarket.Model.Hoogi;
import com.example.goo.carrotmarket.Model.Product;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by Goo on 2019-05-23.
 */

public class ChatRoomPresenter {

    ChatRoomView view;

    ChatRoomPresenter(ChatRoomView view) {
        this.view = view;
    }

    public void setToolbar() {
        view.setToolbar();
    }

    /* //소켓 생성
     public Socket setSocket(Socket socket) {
         try {

             socket = IO.socket("http://54.180.32.57:3000/chat");

         } catch (URISyntaxException e) {
             Log.d("error", "onCreate : " + e.toString());
         }

         return socket;
     }
 */
    //생성된 소켓으로 nodejs socket.io 서버와 이벤트 주고받기
    public void prepareNetwork(Socket socket, String room_id, Emitter.Listener handling_message, Emitter.Listener handling_first_chat_complete, Emitter.Listener handling_leave) {

        socket.connect();
        if (room_id.equals("firstChat")) {
            //socket.emit("firstChat", room_id);
        } else {
            socket.emit("roomNum", room_id);
        }

        socket.on("message", handling_message);
        socket.on("firstChatComplete", handling_first_chat_complete);
        socket.on("leave", handling_leave);

    }

    //전송버튼을 누르면 소켓을 통해 닉네임,프로젝트 아이디 전송
    public void sendMessage(EditText edit_comment, String nick, String user_partner, String date, String date2, String room_num, Socket socket) {
        String message = edit_comment.getText().toString().trim();


        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("message", message);
            jsonObject.put("nick", nick);
            jsonObject.put("partner", user_partner);

            jsonObject.put("roomNum", room_num);
            jsonObject.put("date", date);
            jsonObject.put("date2", date2);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        socket.emit("message", jsonObject);
        //전송 후 view에서는 채팅 전송 에디트 텍스트를 초기화 시켜줌.
        view.setEditTextEmpty();
    }

    //전송버튼을 누르면 소켓을 통해 닉네임,프로젝트 아이디 전송
    public void sendFirstMessage(String roomNum, EditText edit_comment, String nick, String user_partner, Socket socket, int product_id, String nick_seller, String nick_buyer, String date, String date2) {
        String message = edit_comment.getText().toString().trim();


        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("roomNum", roomNum);
            jsonObject.put("message", message);
            jsonObject.put("nick", nick);
            jsonObject.put("user_partner", user_partner);
            jsonObject.put("product_id", product_id);
            jsonObject.put("nick_seller", nick_seller);
            jsonObject.put("nick_buyer", nick_buyer);
            jsonObject.put("date", date);
            jsonObject.put("date2", date2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        socket.emit("firstChat", jsonObject);
        //전송 후 view에서는 채팅 전송 에디트 텍스트를 초기화 시켜줌.
        view.setEditTextEmpty();
        view.showInactiveButton();
    }

    //전송버튼을 누르면 소켓을 통해 닉네임,프로젝트 아이디 전송
    public void leaveRoom(String room_num, String nick, String message, String date, String date2, Socket socket) {

        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("roomNum", room_num);
            jsonObject.put("nick", nick);
            jsonObject.put("message", nick + "님이 나갔습니다.");
            jsonObject.put("date", date);
            jsonObject.put("date2", date2);
            jsonObject.put("message_state", "leave");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        socket.emit("leave", jsonObject);

    }

    //메세지가 정상적으로 전송 및 받기가 되면 어댑터에 그 값들을 답아준다.
    public void addMessage(String message, String user, String message_date, String message_state) {
        view.setAdapter(message, user, message_date, message_state);
    }

    //
    public void countCommentCharacter(EditText edit_comment) {
        edit_comment.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력되는 텍스트에 변화가 있을 때 호출된다.
                if (s.length() == 0) {
                    view.showInactiveButton();
                } else {

                    view.showActiveButton();
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


    public void countCommentCharacterFirstBtn(EditText edit_comment) {
        edit_comment.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력되는 텍스트에 변화가 있을 때 호출된다.
                if (s.length() == 0) {
                    view.showInactiveButton();
                } else {

                    view.showActiveFirstButton();


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

    //채팅메세지 불러오기

    void getChatMessages(CompositeDisposable compositeDisposable, String room_id) {
        view.showProgress();
        ApiInterface apiInterface = ApiClientNodeJs.getApiLocation().create(ApiInterface.class);
        compositeDisposable.add(apiInterface.getChatMessages(room_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ChatMessage>>() {
                    @Override
                    public void accept(List<ChatMessage> chat) throws Exception {
                        view.hideProgress();
                        view.onGetResultMessages(chat);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.hideProgress();
                        view.onErrorLoading(throwable.getMessage());
                    }
                })
        );
    }

    //거래중인 상품 채팅창에 가져오기
    void getProduct(CompositeDisposable compositeDisposable, int product_id) {
        view.showProgress();
        ApiInterface apiInterface = ApiClientNodeJs.getApiLocation().create(ApiInterface.class);
        compositeDisposable.add(apiInterface.getProduct(product_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Product>>() {
                    @Override
                    public void accept(List<Product> products) throws Exception {
                        view.hideProgress();
                        view.onGetResultProduct(products);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.hideProgress();
                        view.onErrorLoading(throwable.getMessage());
                    }
                })
        );
    }

    //판매자의 경우 거래후기를 남겼는지 안남겼는지 확인하기
    void getHoogiState(CompositeDisposable compositeDisposable, int product_id, String seller, String buyer) {
        view.showProgress();
        ApiInterface apiInterface = ApiClientNodeJs.getApiLocation().create(ApiInterface.class);
        compositeDisposable.add(apiInterface.getHoogiState(product_id, seller, buyer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Hoogi>>() {
                    @Override
                    public void accept(List<Hoogi> hoogiList) throws Exception {
                        view.hideProgress();
                        view.onGetResultHoogi(hoogiList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.hideProgress();
                        view.onErrorLoading(throwable.getMessage());
                    }
                })
        );
    }


}
