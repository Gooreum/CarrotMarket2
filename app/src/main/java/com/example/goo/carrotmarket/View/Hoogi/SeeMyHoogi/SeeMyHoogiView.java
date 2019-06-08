package com.example.goo.carrotmarket.View.Hoogi.SeeMyHoogi;

import com.example.goo.carrotmarket.Model.Hoogi;

import java.util.List;

/**
 * Created by Goo on 2019-06-08.
 */

public interface SeeMyHoogiView {

    void onGetResult(List<Hoogi> hoogiList);

    void onErrorLoading(String message);
}
