package com.example.goo.carrotmarket.View.Chat.ChatList;

import com.example.goo.carrotmarket.Model.UserInfo;

import java.util.List;

/**
 * Created by Goo on 2019-05-22.
 */

public interface ChatListView {
    void showProgress();

    void hideProgress();

    void onErrorLoading(String message);

    void onGetResult(List<UserInfo> userinfo);
}
