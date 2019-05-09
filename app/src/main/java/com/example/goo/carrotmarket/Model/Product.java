package com.example.goo.carrotmarket.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Goo on 2019-04-25.
 */

public class Product {

    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("updateWritingCnt")
    private int updateWritingCnt;
    @Expose
    @SerializedName("seller")
    private String seller;
    @Expose
    @SerializedName("category")
    private String category;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("price")
    private String price;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("city")
    private String city;
    @Expose
    @SerializedName("gu")
    private String gu;
    @Expose
    @SerializedName("dong")
    private String dong;
    @Expose
    @SerializedName("dealable")
    private int dealable;
    @Expose
    @SerializedName("state")
    private int state;
    @Expose
    @SerializedName("hide")
    private int hide;
    @Expose
    @SerializedName("like_count")
    private int like_count;
    @Expose
    @SerializedName("chat_count")
    private int chat_count;
    @Expose
    @SerializedName("like_state")
    private int like_state;

    @Expose
    @SerializedName("success")
    private Boolean success;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("imageCnt")
    private int imageCnt;
    @Expose
    @SerializedName("image0")
    private String image0;
    @Expose
    @SerializedName("image1")
    private String image1;
    @Expose
    @SerializedName("image2")
    private String image2;
    @Expose
    @SerializedName("image3")
    private String image3;
    @Expose
    @SerializedName("image4")
    private String image4;
    @Expose
    @SerializedName("image5")
    private String image5;
    @Expose
    @SerializedName("image6")
    private String image6;
    @Expose
    @SerializedName("image7")
    private String image7;
    @Expose
    @SerializedName("image8")
    private String image8;
    @Expose
    @SerializedName("image9")
    private String image9;

    private List<String> arrayImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUpdateWritingCnt() {
        return updateWritingCnt;
    }

    public void setUpdateWritingCnt(int updateWritingCnt) {
        this.updateWritingCnt = updateWritingCnt;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGu() {
        return gu;
    }

    public void setGu(String gu) {
        this.gu = gu;
    }

    public String getDong() {
        return dong;
    }

    public void setDong(String dong) {
        this.dong = dong;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageCnt() {
        return imageCnt;
    }

    public void setImageCnt(int imageCnt) {
        this.imageCnt = imageCnt;
    }

    public int getDealable() {
        return dealable;
    }

    public void setDealable(int dealable) {
        this.dealable = dealable;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getHide() {
        return hide;
    }

    public void setHide(int hide) {
        this.hide = hide;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getChat_count() {
        return chat_count;
    }

    public void setChat_count(int chat_count) {
        this.chat_count = chat_count;
    }

    public int getLike_state() {
        return like_state;
    }

    public void setLike_state(int like_state) {
        this.like_state = like_state;
    }

    public String getImage0() {
        return image0;
    }

    public void setImage0(String image0) {
        this.image0 = image0;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getImage5() {
        return image5;
    }

    public void setImage5(String image5) {
        this.image5 = image5;
    }

    public String getImage6() {
        return image6;
    }

    public void setImage6(String image6) {
        this.image6 = image6;
    }

    public String getImage7() {
        return image7;
    }

    public void setImage7(String image7) {
        this.image7 = image7;
    }

    public String getImage8() {
        return image8;
    }

    public void setImage8(String image8) {
        this.image8 = image8;
    }

    public String getImage9() {
        return image9;
    }

    public void setImage9(String image9) {
        this.image9 = image9;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> imageList() {
        arrayImage = new ArrayList<>();

        arrayImage.add(getImage0());
        arrayImage.add(getImage1());
        arrayImage.add(getImage2());
        arrayImage.add(getImage3());
        arrayImage.add(getImage4());
        arrayImage.add(getImage5());
        arrayImage.add(getImage6());
        arrayImage.add(getImage7());
        arrayImage.add(getImage8());
        arrayImage.add(getImage9());

        return arrayImage;
    }
}
