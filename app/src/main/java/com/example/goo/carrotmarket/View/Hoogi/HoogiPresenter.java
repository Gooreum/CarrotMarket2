package com.example.goo.carrotmarket.View.Hoogi;

import com.example.goo.carrotmarket.API.ApiClient;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.Hoogi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Goo on 2019-06-06.
 */

public class HoogiPresenter {

    HoogiView view;

    HoogiPresenter(HoogiView view) {
        this.view = view;
    }


    void setValues(String title, String seller, String buyer, String nick) {
        view.setValues(title, seller, buyer, nick);
    }

    //판매자가 구매자에게 후기를 남긴다.
    public void sellerToBuyerHoogi(String seller, String buyer, String hoogi, int product_id) {
        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<Hoogi> call = apiInterface.leaveSellerToBuyerHoogi(seller, buyer, hoogi, product_id);

        call.enqueue(new Callback<Hoogi>() {
            @Override
            public void onResponse(Call<Hoogi> call, Response<Hoogi> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResult(response.body().getMessage());

                } else {
                    view.onErrorLoading(response.message());
                }
            }


            @Override
            public void onFailure(Call<Hoogi> call, Throwable t) {

                view.hideProgress();
                view.onErrorLoading(t.getMessage());

            }
        });
    }

    //구매자가 판매자에게 후기를 남긴다.
    public void buyerToSellerHoogi(String seller, String buyer, String hoogi, int product_id) {
        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<Hoogi> call = apiInterface.leaveBuyerToSellerHoogi(seller, buyer, hoogi, product_id);

        call.enqueue(new Callback<Hoogi>() {
            @Override
            public void onResponse(Call<Hoogi> call, Response<Hoogi> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResult(response.body().getMessage());

                } else {
                    view.onErrorLoading(response.message());
                }
            }


            @Override
            public void onFailure(Call<Hoogi> call, Throwable t) {

                view.hideProgress();
                view.onErrorLoading(t.getMessage());

            }
        });
    }
}
