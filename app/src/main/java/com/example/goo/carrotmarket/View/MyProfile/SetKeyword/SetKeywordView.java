package com.example.goo.carrotmarket.View.MyProfile.SetKeyword;

import com.example.goo.carrotmarket.Model.Keyword;

import java.util.List;

/**
 * Created by Goo on 2019-05-26.
 */

public interface SetKeywordView {


    void showProgress();

    void hideProgress();

    void onErrorLoading(String message);

    void onGetResult(List<Keyword> keyword);

}
