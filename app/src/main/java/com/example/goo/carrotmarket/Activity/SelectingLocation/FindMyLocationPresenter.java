package com.example.goo.carrotmarket.Activity.SelectingLocation;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.API.LocationAPI;
import com.example.goo.carrotmarket.Model.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Goo on 2019-04-14.
 */

public class FindMyLocationPresenter {

    private FindMyLocationView view;

    public FindMyLocationPresenter(FindMyLocationView view) {
        this.view = view;
    }

    void getLocation(CharSequence key) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = LocationAPI.getApiLocation().create(ApiInterface.class);
        Call<List<Location>> call = apiInterface.getLocation(key);

        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                view.hideProgress();
                view.failToGetLocation(t.getLocalizedMessage());

            }
        });
    }

    void search(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getLocation(editable.toString());
            }
        });
    }

}

