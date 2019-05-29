package com.example.goo.carrotmarket.View.Detail.ChatList;

import com.example.goo.carrotmarket.Model.Chat;

import java.util.List;

/**
 * Created by Goo on 2019-05-29.
 */

public interface ChatListView  {


    void showProgress();
    void hideProgress();
    void onGetResult(List<Chat> chat);
    void onErrorLoading(String message);
}
