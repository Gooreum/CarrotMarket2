package com.example.goo.carrotmarket.View.MyProfile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Authentication.AuthenticationActivity;

/**
 * Created by Goo on 2019-05-02.
 */

public class MyProfilePresenter {

    private MyProfileView view;
    SessionManager sessionManager;

    public MyProfilePresenter(MyProfileView view, Context context) {
        this.view = view;
        this.sessionManager = new SessionManager(context);
    }

    //로그인 상태 확인하고 프로필 화면 설정해주기
    public void setProfile(String profileImg, String nick, String dong) {

        view.setProfile(profileImg, nick, dong);

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
    public void nextActivityIsLoginWithValue(Context context, Class activity, String value) {
        if (sessionManager.isLoggIn() == true) {

            view.moveActivityWithValue(activity, value);
        } else {
            showDialog(context);
        }
    }

    //버튼 클릭시 로그인 상태확인을 안하고 다음 화면으로 넘겨주기
    public void nextActivityWithoutLogin(Class activity) {

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


}
