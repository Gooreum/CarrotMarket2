package com.example.goo.carrotmarket.View.MyProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Authentication.AuthenticationActivity;
import com.example.goo.carrotmarket.View.Home.Filter.HomeManagerActivity;
import com.example.goo.carrotmarket.View.Home.HomeActivity2;
import com.example.goo.carrotmarket.View.LoginRegister.RegisterActivity;
import com.example.goo.carrotmarket.View.MyProfile.AuthenticateMyLocation.AuthenticateMyLocationActivity;
import com.example.goo.carrotmarket.View.MyProfile.BuyList.BuyListActivity;
import com.example.goo.carrotmarket.View.MyProfile.ConcernList.ConcernListActivity;
import com.example.goo.carrotmarket.View.MyProfile.SellList.SellListActivity;
import com.example.goo.carrotmarket.View.MyProfile.SetKeyword.SetKeywordActivity;
import com.example.goo.carrotmarket.View.MyProfile.SetMyLocation.SetMyLocationActivity;
import com.example.goo.carrotmarket.View.MyProfile.Setting.SettingActivity;
import com.example.goo.carrotmarket.View.Seller.SellerProfile.SellerProfileActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Goo on 2019-04-24.
 */

public class MyProfileFragment extends Fragment implements MyProfileView, View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.profileImg)
    CircleImageView view_profileImg;
    @BindView(R.id.profileImg_login)
    CircleImageView profileImg_login;
    @BindView(R.id.relative_profile)
    RelativeLayout relative_profile;
    @BindView(R.id.relative_login)
    RelativeLayout relative_login;

    @BindView(R.id.relative_change_profile)
    RelativeLayout relative_change_profile;


    @BindView(R.id.nick)
    TextView txt_nick;
    @BindView(R.id.dong)
    TextView txt_dong;
    @BindView(R.id.relative_sell_list)
    RelativeLayout relative_sell_list;
    @BindView(R.id.relative_buy_list)
    RelativeLayout relative_buy_list;
    @BindView(R.id.relative_concern_list)
    RelativeLayout relative_concern_list;
    /*    @BindView(R.id.relative_my_list)
        RelativeLayout relative_my_list;*/
    @BindView(R.id.relative_set_location)
    RelativeLayout relative_set_location;
    @BindView(R.id.relative_authentication_location)
    RelativeLayout relative_authentication_location;
    @BindView(R.id.relative_keyword_alarm)
    RelativeLayout relative_keyword_alarm;
    @BindView(R.id.relative_invite)
    RelativeLayout relative_invite;
    @BindView(R.id.manage_home)
    TextView manage_home;
    @BindView(R.id.clientCenter)
    TextView clientCenter;
    @BindView(R.id.notification)
    TextView notification;
    @BindView(R.id.shareApp)
    TextView shareApp;
    @BindView(R.id.setting)
    TextView setting;
    @BindView(R.id.login)
    TextView login;


    MyProfilePresenter presenter;
    SessionManager sessionManager;
    HashMap<String, String> user;
    String profileImage, nick, dong, dong2;

    Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myprofile, container, false);

        ButterKnife.bind(this, view);


        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        setHasOptionsMenu(true);

        //프로파일 설정
        isLogin();

        //버튼 이벤트 리스너 설정
        initButton();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("MyProfile_onResume", "onResume");
        isLogin();
    }

    public void isLogin() {
        sessionManager = new SessionManager(getContext());
        user = sessionManager.getUserDetail();
        presenter = new MyProfilePresenter(this, getContext());

        if (sessionManager.isLoggIn() == true) {
            profileImage = user.get(sessionManager.PROFILEIMAGE).toString();
            nick = user.get(sessionManager.NICK).toString();
            if (user.get(sessionManager.LOCATION1_STATE.toString()).equals("1")) {
                dong = user.get(sessionManager.DONG).toString();
                presenter.setProfile(profileImage, nick, dong);
            } else if (user.get(sessionManager.LOCATION2_STATE.toString()).equals("1")) {
                dong2 = user.get(sessionManager.DONG2).toString();
                presenter.setProfile(profileImage, nick, dong2);
            }
        }
    }

    public void initButton() {
        relative_sell_list.setOnClickListener((View.OnClickListener) this);
        relative_buy_list.setOnClickListener((View.OnClickListener) this);
        relative_concern_list.setOnClickListener((View.OnClickListener) this);
        // relative_my_list.setOnClickListener((View.OnClickListener) this);
        relative_set_location.setOnClickListener((View.OnClickListener) this);
        relative_authentication_location.setOnClickListener((View.OnClickListener) this);
        relative_keyword_alarm.setOnClickListener((View.OnClickListener) this);
        relative_invite.setOnClickListener((View.OnClickListener) this);
        manage_home.setOnClickListener((View.OnClickListener) this);
        clientCenter.setOnClickListener((View.OnClickListener) this);
        notification.setOnClickListener((View.OnClickListener) this);
        shareApp.setOnClickListener((View.OnClickListener) this);
        setting.setOnClickListener((View.OnClickListener) this);
        login.setOnClickListener((View.OnClickListener) this);
        relative_change_profile.setOnClickListener((View.OnClickListener) this);
        profileImg_login.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.appbar_myprofile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.setting:

                presenter.nextActivityIsLogin(getContext(), SettingActivity.class);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void moveActivity(Class activity) {
        Intent intent = new Intent(getActivity(), activity);
        getActivity().startActivity(intent);
    }

    @Override
    public void moveActivityWithValue(Class activity, String value) {
        Intent intent = new Intent(getActivity(), activity);
        intent.putExtra("nick", value);
        getActivity().startActivity(intent);
    }

    @Override
    public void setProfile(String profileImg, String nick, String dong) {
        Glide.with(getContext()).load(profileImg).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.profileimg).into(profileImg_login);
        relative_login.setVisibility(View.GONE);
        relative_change_profile.setVisibility(View.VISIBLE);
        txt_nick.setText(nick);
        txt_dong.setText(dong);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.relative_sell_list:
                presenter.nextActivityIsLogin(getContext(), SellListActivity.class);
                break;
            case R.id.relative_buy_list:
                presenter.nextActivityIsLogin(getContext(), BuyListActivity.class);
                break;
            case R.id.relative_concern_list:
                presenter.nextActivityIsLogin(getContext(), ConcernListActivity.class);
                break;

            case R.id.relative_set_location:
                presenter.nextActivityWithoutLogin(SetMyLocationActivity.class);
                break;
            case R.id.relative_authentication_location:
                presenter.nextActivityIsLogin(getContext(), AuthenticateMyLocationActivity.class);
                break;
            case R.id.relative_keyword_alarm:
                presenter.nextActivityIsLogin(getContext(), SetKeywordActivity.class);
                break;
            case R.id.relative_invite:
                presenter.nextActivityWithoutLogin(HomeActivity2.class);
                break;
            case R.id.manage_home:
                presenter.nextActivityIsLogin(getContext(), HomeManagerActivity.class);
                break;
            case R.id.clientCenter:
                presenter.nextActivityWithoutLogin(HomeActivity2.class);
                break;
            case R.id.notification:
                presenter.nextActivityWithoutLogin(HomeActivity2.class);
                break;
            case R.id.shareApp:
                presenter.nextActivityWithoutLogin(HomeActivity2.class);
                break;
            case R.id.setting:
                presenter.nextActivityIsLogin(getContext(), SettingActivity.class);
                break;
            case R.id.login:
                presenter.nextActivityWithoutLogin(AuthenticationActivity.class);
                break;
            case R.id.relative_change_profile:
                presenter.nextActivityIsLoginWithValue(getContext(), SellerProfileActivity.class, user.get(sessionManager.NICK).toString());
                break;

            case R.id.profileImg_login:
                presenter.nextActivityIsLoginWithValue(getContext(), RegisterActivity.class, user.get(sessionManager.NICK).toString());
                break;
        }
    }
}