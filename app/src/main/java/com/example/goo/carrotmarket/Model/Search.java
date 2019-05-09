package com.example.goo.carrotmarket.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Goo on 2019-05-08.
 */

public class Search {


    @Expose
    @SerializedName("result")
    private String result;


    public Search(String result) {
        this.result = result;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
