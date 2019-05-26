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
    public static final String PROFILEIMAGE = "PROFILEIMAGE";
    public static final String CITY = "CITY";
    public static final String GU = "GU";
    public static final String DONG = "DONG";
    public static final String LOCATION1_STATE = "LOCATION1_STATE";
    public static final String CITY2 = "CITY2";
    public static final String GU2 = "GU2";
    public static final String DONG2 = "DONG2";
    public static final String LOCATION2_STATE = "LOCATION2_STATE";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("LOGIN", PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(Boolean login, String nick, String profileImage, String city, String gu, String dong, String location1_state, String city2, String gu2, String dong2, String location2_state) {
        editor.putBoolean("LOGIN", login);
        editor.putString("NICK", nick);
        editor.putString("PROFILEIMAGE", profileImage);
        editor.putString("CITY", city);
        editor.putString("GU", gu);
        editor.putString("DONG", dong);
        editor.putString("LOCATION1_STATE", location1_state);
        editor.putString("CITY2", city2);
        editor.putString("GU2", gu2);
        editor.putString("DONG2", dong2);
        editor.putString("LOCATION2_STATE", location2_state);
        editor.apply();
    }


    public void updateLocation1(String city, String gu, String dong, String location1_state, String location2_state) {
        editor.putString("CITY", city);
        editor.putString("GU", gu);
        editor.putString("DONG", dong);
        editor.putString("LOCATION1_STATE",location1_state);
        editor.putString("LOCATION2_STATE",location2_state);
        editor.apply();
    }

    public void updateLocation2(String city2, String gu2, String dong2, String location1_state, String location2_state) {
        editor.putString("CITY2", city2);
        editor.putString("GU2", gu2);
        editor.putString("DONG2", dong2);
        editor.putString("LOCATION1_STATE",location1_state);
        editor.putString("LOCATION2_STATE",location2_state);
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

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();

        user.put(NICK, sharedPreferences.getString(NICK, "empty"));
        user.put(CITY, sharedPreferences.getString(CITY, "empty"));
        user.put(GU, sharedPreferences.getString(GU, "empty"));
        user.put(DONG, sharedPreferences.getString(DONG, "empty"));
        user.put(LOCATION1_STATE, sharedPreferences.getString(LOCATION1_STATE, "1"));
        user.put(CITY2, sharedPreferences.getString(CITY2, "empty"));
        user.put(GU2, sharedPreferences.getString(GU2, "empty"));
        user.put(DONG2, sharedPreferences.getString(DONG2, "empty"));
        user.put(LOCATION2_STATE, sharedPreferences.getString(LOCATION2_STATE, "0"));
        user.put(PROFILEIMAGE, sharedPreferences.getString(PROFILEIMAGE, null));

        return user;
    }

    public void logout() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, IntroActivity.class);
        context.startActivity(i);
    }
}
