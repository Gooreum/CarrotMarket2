package com.example.goo.carrotmarket.View.Chat.ChatRoom;

/**
 * Created by Goo on 2019-05-23.
 */

public interface ChatRoomView {

    void setEditTextEmpty();

    void setToolbar();

    void setAdapter(String message, String user);

    void showActiveButton();

    void showInactiveButton();
}
