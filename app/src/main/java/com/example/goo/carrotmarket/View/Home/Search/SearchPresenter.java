package com.example.goo.carrotmarket.View.Home.Search;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.goo.carrotmarket.API.ApiClient;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.Location;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Goo on 2019-05-07.
 */

public class SearchPresenter {

    SearchView view;

    public SearchPresenter(SearchView view) {
        this.view = view;
    }

    //검색 결과 받아오기
    void getData(String key) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<List<Product>> call = apiInterface.getSearchProduct(key);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }

    //검색할 때 키워드 서버로 보내주기
    void search(android.support.v7.widget.SearchView searchView) {

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                getData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // getData(newText);
                // searchAdapter.getFilter().filter(newText);

                return false;
            }
        });
    }


    //사람검색 결과

    //검색 결과 받아오기
    void getDataUser(String key) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<List<UserInfo>> call = apiInterface.getSearchUser(key);

        call.enqueue(new Callback<List<UserInfo>>() {
            @Override
            public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResultUserInfo(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<UserInfo>> call, Throwable t) {
                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }

    //검색할 때 키워드 서버로 보내주기
    void searchUser(android.support.v7.widget.SearchView searchView) {

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                getDataUser(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // getData(newText);
                // searchAdapter.getFilter().filter(newText);

                return false;
            }
        });
    }


}
