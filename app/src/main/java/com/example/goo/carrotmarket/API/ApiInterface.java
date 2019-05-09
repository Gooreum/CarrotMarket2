package com.example.goo.carrotmarket.API;

import com.example.goo.carrotmarket.Model.Category;
import com.example.goo.carrotmarket.Model.Location;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.UserInfo;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
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

    //상품검색
    @GET("searchProduct.php")
    Call<List<Product>> getSearchProduct(@Query("key") String keyword);

    //사람검색
    @GET("searchUser.php")
    Call<List<UserInfo>> getSearchUser(@Query("key") String keyword);


    //상품정보 관련
    @FormUrlEncoded
    @POST("uploadProduct.php")
    Call<Product> saveProduct(
            @Field("seller") String seller,
            @Field("category") String category,
            @Field("title") String title,
            @Field("price") String price,
            @Field("dealable") int dealable,
            @Field("description") String description,
            @Field("city") String city,
            @Field("gu") String gu,
            @Field("dong") String dong,
            @Field("date") String date

    );

    //글쓰고 내 글 id값 받아오기
    @GET("getMyWritingId.php")
    Call<List<Product>> getMyWritingId(@Query("date") String date);


    @Multipart
    @POST("uploadImages.php")
    Call<ResponseBody> uploadMultiple(
            @Part("size") RequestBody size,
            @Part("date") RequestBody date,
            @Part List<MultipartBody.Part> parts);


    @GET("product.php")
    Call<List<Product>> getProduct(@Query("nick") String nick);

    @GET("product.php")
    Call<List<Product>> getProduct();

    @GET("getProductLike.php")
    Call<List<Product>> getProductLike(@Query("nick") String nick);

    @GET("getProductMySelling.php")
    Call<List<Product>> getProductMySelling(@Query("nick") String nick, @Query("state") int state);

    @GET("detail.php")
    Call<List<Product>> getDetail(@Query("id") int id);

    @GET("sellerProfile.php")
    Call<List<UserInfo>> getSellerProfile(@Query("seller") String seller);

    @GET("sellerProducts.php")
    Call<List<Product>> getSellerProducts(@Query("seller") String seller, @Query("id") int id);

    @GET("sellerProducts.php")
    Call<List<Product>> getSellerProducts(@Query("nick") String seller);

    @GET("sellerProducts.php")
    Call<List<Product>> getSellerProductsTrading(@Query("nick_1") String seller, @Query("state") int state);

    @GET("sellerProducts.php")
    Call<List<Product>> getSellerProductsTraded(@Query("nick_2") String seller, @Query("state") int state);

    @GET("categoryProduct.php")
    Call<List<Product>> getCategoryProduct(@Query("category") String category);

    @GET("getLikeState.php")
    Call<List<Product>> getLikeState(@Query("nick") String category, @Query("id") int id);

    @GET("getProductCollection.php")
    Call<List<Product>> getProductCollection(@Query("nick") String nick);

    @FormUrlEncoded
    @POST("delete.php")
    Call<Product> deleteProduct(
            @Field("id") int id);

    //관심목록 담기, 취소
    @FormUrlEncoded
    @POST("setProductLike.php")
    Call<Product> productLike(
            @Field("id") int id,
            @Field("state") int state,
            @Field("nick") String nick);

    //게시글 끌올하기
    @FormUrlEncoded
    @POST("updateWritingDate.php")
    Call<Product> updateWritingDate(
            @Field("id") int id,
            @Field("date") String date);

    //게시글 상태 변경 : 판매중, 예약중, 거래완료, 숨기기,숨기기 취소
    @FormUrlEncoded
    @POST("updateWritingState.php")
    Call<Product> updateWritingState(
            @Field("id") int id,
            @Field("state") int state);


    //회원정보 관련
    @GET("checkUserIs.php")
    Call<List<UserInfo>> checkUserIs(@Query("phone") String phone);


    //모아보기 클릭
    @FormUrlEncoded
    @POST("CollectingUsers.php")
    Call<UserInfo> collect(
            @Field("seller") String seller,
            @Field("follower") String follower,
            @Field("state") int state
    );

    //상대방 프로필 화면에서 모아보기 상태 불러오기
    @GET("CollectingUsers.php")
    Call<List<UserInfo>> checkCollectingState(
            @Query("seller") String seller,
            @Query("follower") String follower
    );



    @GET("productSeller.php")
    Call<List<UserInfo>> bringSellerInfo(@Query("id") int id);

    @FormUrlEncoded
    @POST("register.php")
    Call<UserInfo> register(
            @Field("nick") String nick,
            @Field("city1") String city1,
            @Field("gu1") String gu1,
            @Field("dong1") String dong1,
            @Field("phone") String phone
    );

    //카테고리 선택
    @FormUrlEncoded
    @POST("selectedCategory.php")
    Call<Category> sendCategory(
            @Field("nick") String nick,
            @Field("category") String category,
            @Field("state") String state);


    @GET("getSelectedCategory.php")
    Call<List<Category>> bringCategory(@Query("nick") String nick);
}
