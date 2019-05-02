package com.example.goo.carrotmarket.API;

import com.example.goo.carrotmarket.Model.Location;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.UserInfo;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Goo on 2019-04-14.
 */

public interface ApiInterface {

    //위치받아오기
    @GET("location.php")
    Call<List<Location>> getLocation(@Query("key") CharSequence keyword);


    //상품정보 받아오기
    @FormUrlEncoded
    @POST("uploadProduct.php")
    Call<Product> saveProduct(
            @Field("seller") String seller,
            @Field("category") String category,
            @Field("title") String title,
            @Field("price") String price,
            @Field("description") String description,
            @Field("city") String city,
            @Field("gu") String gu,
            @Field("dong") String dong,
            @Field("date") String date


    );


    @Multipart
    @POST("uploadImages.php")
    Call<ResponseBody> uploadMultiple(
            @Part("size") RequestBody size,
            @Part("date") RequestBody date,
            @Part List<MultipartBody.Part> parts);


    @GET("product.php")
    Call<List<Product>> getProduct();

    @GET("detail.php")
    Call<List<Product>> getDetail(@Query("id") int id);

    @GET("sellerProducts.php")
    Call<List<Product>> getSellerProducts(@Query("seller") String seller);

    @GET("categoryProduct.php")
    Call<List<Product>> getCategoryProduct(@Query("category") String category);


    //회원정보 관련
    @GET("checkUserIs.php")
    Call<List<UserInfo>> checkUserIs(@Query("phone") String phone);



    @Multipart
    @POST("register.php")
    Call<UserInfo> register(
            @Field("nick") String nick,
            @Field("city1") String city1,
            @Field("gu1")  String gu1,
            @Field("dong1")  String dong1,
            @Field("phone")  String phone
           );


}
