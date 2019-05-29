package com.example.goo.carrotmarket.View.Detail.ChatList;

import com.example.goo.carrotmarket.API.ApiClientNodeJs;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.Chat;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Goo on 2019-05-29.
 */

public class ChatListPresenter {

    ChatListView view;

    ChatListPresenter(ChatListView view) {
        this.view = view;
    }

    //회원 목록 불러오기
    void getChatListFromDetail(CompositeDisposable compositeDisposable, String nick, int product_id) {
        view.showProgress();
        ApiInterface apiInterface = ApiClientNodeJs.getApiLocation().create(ApiInterface.class);
        compositeDisposable.add(apiInterface.getChatListFromDetail(nick, product_id)
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

}
