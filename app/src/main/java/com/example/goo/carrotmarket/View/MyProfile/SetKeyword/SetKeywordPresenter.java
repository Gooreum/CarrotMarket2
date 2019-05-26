package com.example.goo.carrotmarket.View.MyProfile.SetKeyword;

import com.example.goo.carrotmarket.API.ApiClientNodeJs;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.Keyword;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Goo on 2019-05-26.
 */

public class SetKeywordPresenter {

    SetKeywordView view;

    SetKeywordPresenter(SetKeywordView view) {
        this.view = view;
    }


    //등록된 키워드 보여주기
    public void getKeywords(CompositeDisposable compositeDisposable, String nick) {
        view.showProgress();
        ApiInterface apiInterface = ApiClientNodeJs.getApiLocation().create(ApiInterface.class);
        compositeDisposable.add(apiInterface.getKeywords(nick)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Keyword>>() {
                    @Override
                    public void accept(List<Keyword> keywords) throws Exception {
                        view.hideProgress();
                        view.onGetResult(keywords);
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

    //키워드 등록


    //키워드 등록 취소

}
