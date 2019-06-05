package com.example.goo.carrotmarket.View.MyProfile;

/**
 * Created by Goo on 2019-05-02.
 */

public interface MyProfileView {



    void moveActivity( Class activity);

    void moveActivityWithValue( Class activity, String value);

    void setProfile(String profileImg, String nick, String dong);


}
