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
    @SerializedName("buyer_hoogi_state")
    private int buyer_hoogi_state;

    @Expose
    @SerializedName("buyer_to_seller")
    private String buyer_to_seller;

    @Expose
    @SerializedName("seller_to_buyer")
    private String seller_to_buyer;


    @Expose
    @SerializedName("success")
    private String success;

    @Expose
    @SerializedName("message")
    private String message;


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

    public int getBuyer_hoogi_state() {
        return buyer_hoogi_state;
    }

    public void setBuyer_hoogi_state(int buyer_hoogi_state) {
        this.buyer_hoogi_state = buyer_hoogi_state;
    }

    public String getBuyer_to_seller() {
        return buyer_to_seller;
    }

    public void setBuyer_to_seller(String buyer_to_seller) {
        this.buyer_to_seller = buyer_to_seller;
    }

    public String getSeller_to_buyer() {
        return seller_to_buyer;
    }

    public void setSeller_to_buyer(String seller_to_buyer) {
        this.seller_to_buyer = seller_to_buyer;
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
