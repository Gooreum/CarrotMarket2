package com.example.goo.carrotmarket.Dialog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Home.HomeActivity;
import com.example.goo.carrotmarket.View.CustomDialogView;

/**
 * Created by Goo on 2019-04-14.
 */

public class CustomDialogPresenter {

    private CustomDialogView view;
    SessionManager sessionManager;
    public CustomDialogPresenter(CustomDialogView view,AppCompatActivity context) {
        this.view = view;
        this.sessionManager = new SessionManager(context);
    }



    void confirm(String city, String gu, String dong, Context context) {


        sessionManager.createSession(false,null,null,city,gu,dong);
        Intent intent = new Intent(context, HomeActivity.class);
        //intent.putExtra("Location", location);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

    }

}



