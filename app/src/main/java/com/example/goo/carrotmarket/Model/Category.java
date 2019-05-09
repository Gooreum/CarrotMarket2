package com.example.goo.carrotmarket.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Goo on 2019-04-28.
 */

public class Category {
    @Expose
    @SerializedName("digital")
    private String digital;

    @Expose
    @SerializedName("furniture")
    private String furniture;

    @Expose
    @SerializedName("child")
    private String child;

    @Expose
    @SerializedName("life")
    private String life;

    @Expose
    @SerializedName("female_fashion")
    private String female_fashion;

    @Expose
    @SerializedName("female_etc")
    private String female_etc;

    @Expose
    @SerializedName("beauty")
    private String beauty;

    @Expose
    @SerializedName("male")
    private String male;

    @Expose
    @SerializedName("sports")
    private String sports;

    @Expose
    @SerializedName("game")
    private String game;

    @Expose
    @SerializedName("ticket")
    private String ticket;

    @Expose
    @SerializedName("pet")
    private String pet;

    @Expose
    @SerializedName("etc")
    private String etc;

    @Expose
    @SerializedName("buying")
    private String buying;

    @Expose
    @SerializedName("count")
    private int count;

    public String getDigital() {
        return digital;
    }


    public void setDigital(String digital) {
        this.digital = digital;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getFemale_fashion() {
        return female_fashion;
    }

    public void setFemale_fashion(String female_fashion) {
        this.female_fashion = female_fashion;
    }

    public String getFemale_etc() {
        return female_etc;
    }

    public void setFemale_etc(String female_etc) {
        this.female_etc = female_etc;
    }

    public String getBeauty() {
        return beauty;
    }

    public void setBeauty(String beauty) {
        this.beauty = beauty;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public String getBuying() {
        return buying;
    }

    public void setBuying(String buying) {
        this.buying = buying;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
