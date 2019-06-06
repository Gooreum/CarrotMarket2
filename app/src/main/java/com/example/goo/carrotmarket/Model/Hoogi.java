package com.example.goo.carrotmarket.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Goo on 2019-06-06.
 */

public class Hoogi {

    @Expose
    @SerializedName("product_id")
    private int product_id;

    @Expose
    @SerializedName("buyer")
    private String buyer;

    @Expose
    @SerializedName("seller")
    private String seller;

    @Expose
    @SerializedName("hoogi_state")
    private int hoogi_state;


    @Expose
    @SerializedName("success")
    private String success;

    @Expose
    @SerializedName("message")
    private String message;

    Hoogi(int product_id, String buyer, String seller, int hoogi_state) {
        this.product_id = product_id;
        this.buyer = buyer;
        this.seller = seller;
        this.hoogi_state = hoogi_state;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getHoogi_state() {
        return hoogi_state;
    }

    public void setHoogi_state(int hoogi_state) {
        this.hoogi_state = hoogi_state;
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
