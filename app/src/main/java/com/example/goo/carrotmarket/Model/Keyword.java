package com.example.goo.carrotmarket.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Goo on 2019-05-26.
 */

public class Keyword {


    @SerializedName("nick")
    private String nick;
    @SerializedName("keyword")
    private String keyword;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
