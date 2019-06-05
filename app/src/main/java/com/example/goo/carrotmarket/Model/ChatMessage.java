package com.example.goo.carrotmarket.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Goo on 2019-05-29.
 */

public class ChatMessage {
    @Expose
    @SerializedName("room_id")
    String room_id;

    @Expose
    @SerializedName("nick")
    String nick;

    @Expose
    @SerializedName("profile_img")
    String profile_img;

    @Expose
    @SerializedName("message")
    String message;

    @Expose
    @SerializedName("date")
    String date;

    @Expose
    @SerializedName("read_state")
    int read_state;

    @Expose
    @SerializedName("message_state")
    String message_state;

    public ChatMessage(String nick, String message, String date, String message_state) {

        this.nick = nick;
        this.message = message;
        this.date = date;
        this.message_state = message_state;

    }


    ChatMessage(String room_id, String nick, String profile_img, String message, String date, int read_state) {
        this.room_id = room_id;
        this.nick = nick;
        this.profile_img = profile_img;
        this.message = message;
        this.date = date;
        this.read_state = read_state;
    }


    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRead_state() {
        return read_state;
    }

    public void setRead_state(int read_state) {
        this.read_state = read_state;
    }


    public String getMessage_state() {
        return message_state;
    }

    public void setMessage_state(String message_state) {
        this.message_state = message_state;
    }
}
