package com.example.goo.carrotmarket.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Goo on 2019-05-02.
 */

public class UserInfo {
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("nick")
    private String nick;
    @Expose
    @SerializedName("profileImg")
    private String profileImg;
    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("city1")
    private String city1;
    @Expose
    @SerializedName("gu1")
    private String gu1;
    @Expose
    @SerializedName("dong1")
    private String dong1;
    @Expose
    @SerializedName("city2")
    private String city2;
    @Expose
    @SerializedName("gu2")
    private String gu2;
    @Expose
    @SerializedName("dong2")
    private String dong2;
    @Expose
    @SerializedName("collect_state")
    private int collect_state;
    @Expose
    @SerializedName("success")
    private String success;
    @Expose
    @SerializedName("message")
    private String message;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getProfileImage() {
        return profileImg;
    }

    public void setProfileImage(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getGu1() {
        return gu1;
    }

    public void setGu1(String gu1) {
        this.gu1 = gu1;
    }

    public String getDong1() {
        return dong1;
    }

    public void setDong1(String dong1) {
        this.dong1 = dong1;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    public String getGu2() {
        return gu2;
    }

    public void setGu2(String gu2) {
        this.gu2 = gu2;
    }

    public String getDong2() {
        return dong2;
    }

    public void setDong2(String dong2) {
        this.dong2 = dong2;
    }


    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public int getCollect_state() {
        return collect_state;
    }

    public void setCollect_state(int collect_state) {
        this.collect_state = collect_state;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
