package com.example.goo.carrotmarket.View.Home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.example.goo.carrotmarket.API.ApiClient;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.Category;
import com.example.goo.carrotmarket.Model.Location;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Authentication.AuthenticationActivity;
import com.example.goo.carrotmarket.View.SelectingLocation.FindMyLocationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Goo on 2019-04-24.
 */

public class HomePresenter {

    private HomeView view;
    SessionManager sessionManager;

    public HomePresenter(HomeView view) {
        this.view = view;

    }


    public HomePresenter(HomeView view, Context context) {
        this.view = view;
        this.sessionManager = new SessionManager(context);
    }

    //홈화면에서 상품 받아오기
    void getProducts(String nick) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<List<Product>> call = apiInterface.getProduct(nick);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull  Call<List<Product>> call,@NonNull  Response<List<Product>> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull  Call<List<Product>> call, @NonNull  Throwable t) {
                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }

    //검색 결과 받아오기
    void getProducts() {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<List<Product>> call = apiInterface.getProduct();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull  Call<List<Product>> call,@NonNull  Response<List<Product>> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull  Call<List<Product>> call, @NonNull  Throwable t) {
                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }


    //버튼 클릭시 로그인 상태 확인을 하고, 다음 화면으로 넘겨주기
    public void nextActivityIsLogin(Context context, Class activity) {
        if (sessionManager.isLoggIn() == true) {
            view.moveActivity(activity);
        } else {
            showDialog(context);
        }
    }

    //버튼 클릭시 로그인 상태 확인을 하고, 다음 화면으로 넘겨주기
    public void nextActivityIsLoginForResult(Context context, Class activity) {
        if (sessionManager.isLoggIn() == true) {
            view.moveActivityForResult(activity);
        } else {
            showDialog(context);
        }
    }

    //버튼 클릭시 로그인 상태확인을 안하고 다음 화면으로 넘겨주기
    public void nextActivityWithoutLogin( Class activity) {

        view.moveActivity(activity);

    }


    //다이얼로그 띄우기
    void showDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("회원가입 또는 로그인후 이용할 수 있습니다.");

        builder.setPositiveButton("로그인/가입", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(context, AuthenticationActivity.class);

                context.startActivity(intent);
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    //선택한 카테고리 결과 받아오기
    void getCategory(String nick) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<List<Category>> call = apiInterface.bringCategory(nick);

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull  Call<List<Category>> call,@NonNull  Response<List<Category>> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResultCategory(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull  Call<List<Category>> call, @NonNull  Throwable t) {
                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }


    //카테고리 서버에 보내기
    void sendCategorytoServer(String nick, String category,String state) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<Category> call = apiInterface.sendCategory(nick,category,state);

        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(@NonNull  Call<Category> call,@NonNull  Response<Category> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResultCategory(response.body().toString());
                }
            }

            @Override
            public void onFailure(@NonNull  Call<Category> call, @NonNull  Throwable t) {
                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }

}
