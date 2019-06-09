package com.example.goo.carrotmarket.View.Chat.ChatRoom;

import com.example.goo.carrotmarket.Model.ChatMessage;
import com.example.goo.carrotmarket.Model.Hoogi;
import com.example.goo.carrotmarket.Model.Product;

import java.util.List;

/**
 * Created by Goo on 2019-05-23.
 */

public interface ChatRoomView {

    void showProgress();

    void hideProgress();

    void onErrorLoading(String message);

    void onGetResultMessages(List<ChatMessage> messageList);

    void onGetResultProduct(List<Product> productList);

    void onGetResultHoogi(List<Hoogi> hoogiList);

    void setEditTextEmpty();

    void setToolbar();

    void setAdapter(String message, String user, String message_date, String message_state);

    void showActiveButton();

    void showInactiveButton();

    void showActiveFirstButton();

    //void hideBtnFirstChat();
}
