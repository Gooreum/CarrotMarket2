package com.example.goo.carrotmarket.View.Chat.ChatList;

import android.support.annotation.NonNull;

import com.example.goo.carrotmarket.API.ApiClientNodeJs;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.UserInfo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Goo on 2019-05-22.
 */

public class ChatListPresenter {

    ChatListView view;


    ChatListPresenter(ChatListView view) {

        this.view = view;
    }

    //회원 목록 불러오기
    void getUsers(CompositeDisposable compositeDisposable) {
        view.showProgress();
        ApiInterface apiInterface = ApiClientNodeJs.getApiLocation().create(ApiInterface.class);
        compositeDisposable.add(apiInterface.getUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<UserInfo>>() {
                    @Override
                    public void accept(List<UserInfo> userInfos) throws Exception {
                        view.hideProgress();
                        view.onGetResult(userInfos);
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


    //채팅방 번호 보내기
    void sendChatRoomNum(int room) {
        view.showProgress();
        //Request to Server

        ApiInterface apiInterface = ApiClientNodeJs.getApiLocation().create(ApiInterface.class);
        Call<Integer> call = apiInterface.sendChatRoomNum(room);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                }
            }

            @Override
            public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }
}
