package com.example.goo.carrotmarket.View.Chat.ChatListRealTime;

import android.util.Log;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by Goo on 2019-05-30.
 */

public class ChatListRealTimePresenter {

    ChatListRealTimeView view;

    ChatListRealTimePresenter(ChatListRealTimeView view) {
        this.view = view;
    }


    //소켓 생성
    public Socket setSocket(Socket socket) {
        try {

            socket = IO.socket("http://54.180.32.57:3000/chat");

        } catch (URISyntaxException e) {
            Log.d("error", "onCreate : " + e.toString());
        }

        return socket;
    }

    //생성된 소켓으로 nodejs socket.io 서버와 이벤트 주고받기
    public void prepareNetwork(Socket socket, Emitter.Listener handling, String nick) {

        socket.connect();
        socket.emit("myChatList", nick);
        socket.on("myChatList", handling);

    }

    //메세지가 정상적으로 전송 및 받기가 되면 어댑터에 그 값들을 답아준다.

    public void addMessage( String room_id, String user_partner, String message,String date, int product_id, String nick_buyer, String nick_seller ) {
        view.setAdapter(room_id,user_partner, message, date, product_id,nick_buyer,nick_seller);

    }

}
