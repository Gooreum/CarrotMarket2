package com.example.goo.carrotmarket.API;

import com.example.goo.carrotmarket.Model.Category;
import com.example.goo.carrotmarket.Model.Chat;
import com.example.goo.carrotmarket.Model.ChatMessage;
import com.example.goo.carrotmarket.Model.Keyword;
import com.example.goo.carrotmarket.Model.Location;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.UserInfo;

import java.util.List;

import io.reactivex.Observable;
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

    //상품검색
    @GET("searchProduct.php")
    Call<List<Product>> getSearchProduct(@Query("key") String keyword);

    //사람검색
    @GET("searchUser.php")
    Call<List<UserInfo>> getSearchUser(@Query("key") String keyword);

    //동네설정변경(추가,수정,삭제) state = 1 : 수정 state = 2 : 삭제
    @FormUrlEncoded
    @POST("updateMyLocation.php")
    Call<UserInfo> updateMyLocation(
            @Field("nick") String nick,
            @Field("city") String city,
            @Field("gu") String gu,
            @Field("dong") String dong,
            @Field("state") int state,
            @Field("location_state") int location_state
    );


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

    //이미지 업로드하기
    @Multipart
    @POST("uploadImages.php")
    Call<ResponseBody> uploadMultiple(
            @Part("size") RequestBody size,
            @Part("date") RequestBody date,
            @Part List<MultipartBody.Part> parts);


    @GET("product.php")
    Call<List<Product>> getProduct(@Query("nick") String nick, @Query("city") String city, @Query("gu") String gu, @Query("dong") String dong);

    @GET("product.php")
    Call<List<Product>> getProduct();

    //홈 화면에서 특정 게시글에 대한 값 받아오기
    @GET("specificProduct.php")
    Call<List<Product>> getSpecificProduct(@Query("product_id") int product_id);

    @GET("getProductLike.php")
    Call<List<Product>> getProductLike(@Query("nick") String nick);

    @GET("getProductMySelling.php")
    Call<List<Product>> getProductMySelling(@Query("nick") String nick, @Query("state") int state);

    @GET("detail.php")
    Call<List<Product>> getDetail(@Query("id") int id);


    //판매자의 프로필 가져오기
    @GET("sellerProfile.php")
    Call<List<UserInfo>> getSellerProfile(@Query("seller") String seller);


    @GET("sellerProducts.php")
    Call<List<Product>> getSellerProducts(@Query("seller") String seller, @Query("id") int id);

    //판매자의 상품들 중 거래중인 상품 보기
    @GET("sellerProducts.php")
    Call<List<Product>> getSellerProducts(@Query("nick") String seller);


    //판매자의 상품들 중 거래중인 상품 보기
    @GET("sellerProducts.php")
    Call<List<Product>> getSellerProductsTrading(@Query("nick_1") String seller, @Query("state") int state);


    //판매자의 상품들중 거래완료된 상품 보기
    @GET("sellerProducts.php")
    Call<List<Product>> getSellerProductsTraded(@Query("nick_2") String seller, @Query("state") int state);

    //카테고리별 상품 보기
    @GET("categoryProduct.php")
    Call<List<Product>> getCategoryProduct(@Query("category") String category, @Query("city") String city, @Query("gu") String gu);

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

    //모아보는 사람 리스트 가지고오기
    @GET("CollectingUsers.php")
    Call<List<UserInfo>> getCollectionUserList(
            @Query("nick") String nick

    );

    //상대방 프로필 화면에서 모아보기 상태 불러오기
    @GET("CollectingUsers.php")
    Call<List<UserInfo>> checkCollectingState(
            @Query("seller") String seller,
            @Query("follower") String follower
    );

    //회원가입하기
    @FormUrlEncoded
    @POST("register.php")
    Call<UserInfo> register(
            @Field("profileImg") String profileImg,
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

    //게시글 받아올 카테고리 값 가져오기
    @GET("getSelectedCategory.php")
    Call<List<Category>> bringCategory(@Query("nick") String nick);


    //<-------------------채팅 관련--------------------->

    //상세보기에서 기존의 채팅창이 있는지 없는지 확인하기
    @GET("detail.php")
    Call<List<Chat>> isChatRoom(@Query("nick") String nick, @Query("product_id") int product_id);

    //채팅 회원 목록 불러오기

    @GET("chatRoomList")
    Observable<List<Chat>> getChatList(@Query("nick") String nick);

    //상품 상세보기 화면에서 채팅목록 불러오기
    @GET("chatRoomList/id")
    Observable<List<Chat>> getChatListFromDetail(@Query("nick") String nick, @Query("product_id") int product_id);

    @GET("chat/messages")
    Observable<List<ChatMessage>> getChatMessages(@Query("roomNum") String roomNum);


    @GET("chat/product")
    Observable<List<Product>> getProduct(@Query("product_id") int product_id);


    //구매자 선택 화면에서 상품 거래를 위해 채팅하고 있는 사람들



    //<-------------------키워드 알림 관련--------------------->
    //등록한 키워드 리스트 가지고 오기
    @GET("keywords")
    Observable<List<Keyword>> getKeywords(@Query("nick") String nick);

}
