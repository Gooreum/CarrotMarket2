package com.example.goo.carrotmarket.View.Seller.SellerProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.goo.carrotmarket.Model.UserInfo;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Seller.SellerProducts.SellerActivity;
import com.example.goo.carrotmarket.View.Seller.SellerProfile.SellerHoogi.SellerHoogiActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Goo on 2019-05-06.
 */

public class SellerProfileActivity extends AppCompatActivity implements SellerProfileView, View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.profileImg)
    CircleImageView profileImg;

    @BindView(R.id.nick)
    TextView txt_nick;
    @BindView(R.id.cardView_Manner)
    CardView cardView_Manner;
    @BindView(R.id.cardView_collection)
    CardView cardView_collection;
    @BindView(R.id.cardView_collecting)
    CardView cardView_collecting;
    @BindView(R.id.txt_authentication)
    TextView txt_authentication;
    @BindView(R.id.txt_register_date)
    TextView txt_register_date;
    @BindView(R.id.relative_products)
    RelativeLayout relative_products;
    @BindView(R.id.relative_manner_evaluation)
    RelativeLayout relative_manner_evaluation;
    @BindView(R.id.recyclerView_Evaluation)
    RecyclerView recyclerView_Evaluation;
    @BindView(R.id.relative_hoogi)
    RelativeLayout relative_hoogi;
    @BindView(R.id.recyclerView_after)
    RecyclerView recyclerView_after;
    @BindView(R.id.products)
    TextView products;


    SellerProfilePresenter presenter;
    List<UserInfo> userInfoList;
    //serInfo userInfo;
    String seller;

    String follower;
    SessionManager sessionManager;
    HashMap<String, String> user;
    com.example.goo.carrotmarket.Util.ToolBar toolBar = new com.example.goo.carrotmarket.Util.ToolBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_profile);

        ButterKnife.bind(this);

        //툴바 생성
        toolBar.setToolbar(toolbar, this);


        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();
        follower = user.get(sessionManager.NICK).toString();

        userInfoList = new ArrayList<>();
        //userInfo = new UserInfo();


        seller = getIntent().getStringExtra("nick").toString();

        presenter = new SellerProfilePresenter(this);
        presenter.getSellerProfile(seller);

        if (seller.equals(follower)) {
            cardView_Manner.setVisibility(View.GONE);
            cardView_collection.setVisibility(View.GONE);
        } else {
            presenter.getCollectingState(seller, follower);

        }
        //버튼 이벤트
        initButton();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_product_detail, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void setProfile() {
        Glide.with(this).load(userInfoList.get(0).getProfileImage().toString()).error(R.drawable.ic_person_outline_black_24dp).into(profileImg);
        txt_nick.setText(userInfoList.get(0).getNick().toString());
    }

    public void initButton() {

        relative_products.setOnClickListener(this);
        cardView_collection.setOnClickListener(this);
        cardView_collecting.setOnClickListener(this);
        relative_hoogi.setOnClickListener(this);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onGetResult(List<UserInfo> userinfos) {
        userInfoList = userinfos;
        setProfile();
    }

    @Override
    public void onGetCollectingState(List<UserInfo> userInfos) {
        // userInfoList = userInfos;
        if (userInfos.size() != 0) {
            if (userInfos.get(0).getCollect_state() == 1) {
                cardView_collection.setVisibility(View.GONE);
                cardView_collecting.setVisibility(View.VISIBLE);
            }
        } else {
            cardView_collection.setVisibility(View.VISIBLE);
            cardView_collecting.setVisibility(View.GONE);
        }

    }

    @Override
    public void onGetCollectResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relative_products:
                Intent intent = new Intent(SellerProfileActivity.this, SellerActivity.class);
                intent.putExtra("nick", seller);
                startActivity(intent);
                break;
            case R.id.cardView_collection:
                //follower = user.get(sessionManager.NICK).toString();

                presenter.changeCollectState(seller, follower, 1);
                cardView_collecting.setVisibility(View.VISIBLE);
                cardView_collection.setVisibility(View.GONE);

                break;

            case R.id.cardView_collecting:


                presenter.changeCollectState(seller, follower, 2);
                cardView_collecting.setVisibility(View.GONE);
                cardView_collection.setVisibility(View.VISIBLE);
                break;

            case R.id.relative_hoogi:
                Intent intent2 = new Intent(SellerProfileActivity.this, SellerHoogiActivity.class);
                intent2.putExtra("nick", seller);
                startActivity(intent2);

                break;
        }
    }
}