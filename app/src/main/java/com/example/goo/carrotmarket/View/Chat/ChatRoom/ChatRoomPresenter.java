package com.example.goo.carrotmarket.View.Chat.ChatRoom;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
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
    public void prepareNetwork(Socket socket, String project_id, Emitter.Listener handling) {

        socket.connect();
        socket.emit("roomNum", project_id);
        socket.on("message", handling);

    }

    //전송버튼을 누르면 소켓을 통해 닉네임,프로젝트 아이디 전송
    public void sendMessage(EditText edit_comment, String nick, String project_id, Socket socket) {
        String message = edit_comment.getText().toString().trim();


        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("message", message);
            jsonObject.put("nick", nick);
            jsonObject.put("roomNum", project_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        socket.emit("message", jsonObject);
        //전송 후 view에서는 채팅 전송 에디트 텍스트를 초기화 시켜줌.
        view.setEditTextEmpty();
    }


    //메세지가 정상적으로 전송 및 받기가 되면 어댑터에 그 값들을 답아준다.
    public void addMessage(String message, String user) {
        view.setAdapter(message, user);
    }


    //
    public void countCommentCharacter(EditText edit_comment, Button btn_send_active, Button btn_send_inactive) {
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

}
