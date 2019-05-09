package com.example.goo.carrotmarket.View.MyProfile.Setting.Logout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Home.HomeActivity2;
import com.example.goo.carrotmarket.View.Intro.IntroActivity;

/**
 * Created by Goo on 2019-05-05.
 */

public class LogoutPresenter {

    private LogoutView view;
    SessionManager sessionManager;

    public LogoutPresenter(LogoutView view, AppCompatActivity context) {
        this.view = view;
        this.sessionManager = new SessionManager(context);

    }

    //로그아웃 하기
    public void showDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("로그아웃");
        builder.setMessage("로그아웃 하시겠습니까?");


        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                view.showDialog();
                sessionManager.logout();
                Intent intent = new Intent(context, IntroActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                view.hideProgress();
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
