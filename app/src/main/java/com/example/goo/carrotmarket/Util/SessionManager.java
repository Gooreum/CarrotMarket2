package com.example.goo.carrotmarket.Util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.goo.carrotmarket.View.Authentication.AuthenticationActivity;
import com.example.goo.carrotmarket.View.Intro.IntroActivity;

import java.util.HashMap;

/**
 * Created by Goo on 2019-05-02.
 */

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "LOGIN";
    public static final String NICK = "NICK";
    public static final String CITY = "CITY";
    public static final String GU = "GU";
    public static final String DONG = "DONG";
    public static final String PROFILEIMAGE = "PROFILEIMAGE";
    public static final String CATEGORY = "CATEGORY";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("LOGIN", PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(Boolean login, String nick, String profileImage, String city, String gu, String dong) {
        editor.putBoolean("LOGIN", login);
        editor.putString("NICK", nick);
        editor.putString("PROFILEIMAGE", profileImage);
        editor.putString("CITY", city);
        editor.putString("GU", gu);
        editor.putString("DONG", dong);

        editor.apply();
    }

    public boolean isLoggIn() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin() {
        if (!this.isLoggIn()) {
            Intent i = new Intent(context, AuthenticationActivity.class);
            context.startActivity(i);
            //context.finish();
        }
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();

        user.put(NICK, sharedPreferences.getString(NICK,null));
        user.put(CITY, sharedPreferences.getString(CITY,null));
        user.put(GU, sharedPreferences.getString(GU,null));
        user.put(DONG, sharedPreferences.getString(DONG,null));
        user.put(PROFILEIMAGE, sharedPreferences.getString(PROFILEIMAGE,null));
        user.put(CATEGORY, sharedPreferences.getString(CATEGORY,null));
        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, IntroActivity.class);
        context.startActivity(i);
    }
}
