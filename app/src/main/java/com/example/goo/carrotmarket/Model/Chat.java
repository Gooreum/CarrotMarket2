package com.example.goo.carrotmarket.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Goo on 2019-05-28.
 */

public class Chat {

    @Expose
    @SerializedName("room_id")
    private String room_id;

    @Expose
    @SerializedName("user")
    private String user;

    @Expose
    @SerializedName("user_partner")
    private String user_partner;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("date")
    private String date;

    @Expose
    @SerializedName("product_id")
    private int product_id;

    @Expose
    @SerializedName("nick_seller")
    private String nick_seller;

    @Expose
    @SerializedName("nick_buyer")
    private String nick_buyer;

    @Expose
    @SerializedName("is_selling")
    private int is_selling;

    @Expose
    @SerializedName("is_buying")
    private int is_buying;

    @Expose
    @SerializedName("is_complete")
    private int is_complete;

    Chat(){

    }

    public Chat(String room_id, String user_partner, String message, String date, int product_id) {
        this.room_id = room_id;
        this.user_partner = user_partner;
        this.message = message;
        this.date = date;
        this.product_id = product_id;
    }


    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser_partner() {
        return user_partner;
    }

    public void setUser_partner(String user) {
        this.user_partner = user_partner;
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

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getNick_seller() {
        return nick_seller;
    }

    public void setNick_seller(String nick_seller) {
        this.nick_seller = nick_seller;
    }

    public String getNick_buyer() {
        return nick_buyer;
    }

    public void setNick_buyer(String nick_buyer) {
        this.nick_buyer = nick_buyer;
    }

    public int getIs_selling() {
        return is_selling;
    }

    public void setIs_selling(int is_selling) {
        this.is_selling = is_selling;
    }

    public int getIs_buying() {
        return is_buying;
    }

    public void setIs_buying(int is_buying) {
        this.is_buying = is_buying;
    }

    public int getIs_complete() {
        return is_complete;
    }

    public void setIs_complete(int is_complete) {
        this.is_complete = is_complete;
    }
}
