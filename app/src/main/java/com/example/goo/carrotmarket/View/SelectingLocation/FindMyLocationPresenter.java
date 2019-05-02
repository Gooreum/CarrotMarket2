package com.example.goo.carrotmarket.View.SelectingLocation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.API.ApiClient;
import com.example.goo.carrotmarket.Model.Location;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Home.HomeActivity2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Goo on 2019-04-14.
 */

public class FindMyLocationPresenter {

    private FindMyLocationView view;
    SessionManager sessionManager;

    public FindMyLocationPresenter(FindMyLocationView view, AppCompatActivity context) {
        this.view = view;
        this.sessionManager = new SessionManager(context);
    }


    //검색 결과 받아오기
    void getData(CharSequence key) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
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

    //검색할 때 키워드들 서버로 보내주기
    void search(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getData(editable.toString());
            }
        });
    }

    //다이얼로그 띄우기
    void showDialog(Context context, String city, String gu, String dong) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("'" + dong + "'" + "으로 입장 할까요?");
        builder.setMessage("선택하신 " + "'" + dong + "'" + "마켓으로 입장합니다.");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("입장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sessionManager.createSession(false,null, null, city, gu, dong);
                Intent intent = new Intent(context, HomeActivity2.class);
                intent.putExtra("Location", dong);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Snackbar.make(textView, "아니오 버튼이 눌렸습니다.", Snackbar.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}



