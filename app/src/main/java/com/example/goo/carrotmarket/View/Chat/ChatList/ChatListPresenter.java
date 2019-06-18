package com.example.goo.carrotmarket.View.Chat.ChatList;

import com.example.goo.carrotmarket.API.ApiClientNodeJs;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.Chat;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by Goo on 2019-05-22.
 */

public class ChatListPresenter {

    ChatListView view;


    ChatListPresenter(ChatListView view) {

        this.view = view;
    }

    //회원 목록 불러오기
    void getChatList(CompositeDisposable compositeDisposable, String nick) {
        view.showProgress();
        ApiInterface apiInterface = ApiClientNodeJs.getApiLocation().create(ApiInterface.class);
        compositeDisposable.add(apiInterface.getChatList(nick)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Chat>>() {
                    @Override
                    public void accept(List<Chat> chat) throws Exception {
                        view.hideProgress();
                        view.onGetResult(chat);
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

    //생성된 소켓으로 nodejs socket.io 서버와 이벤트 주고받기
    public void prepareNetwork(Socket socket, Emitter.Listener handling, String nick) {

        socket.connect();
        socket.emit("myChatList", nick);
        socket.on("myChatList", handling);

    }


}
