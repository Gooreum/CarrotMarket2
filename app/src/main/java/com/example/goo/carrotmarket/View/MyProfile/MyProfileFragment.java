package com.example.goo.carrotmarket.View.MyProfile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.goo.carrotmarket.Model.Category;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Authentication.AuthenticationActivity;
import com.example.goo.carrotmarket.View.Authentication.EmptyActivity;
import com.example.goo.carrotmarket.View.Category.CategoryActivity;
import com.example.goo.carrotmarket.View.Home.HomeActivity2;
import com.example.goo.carrotmarket.View.Home.HomeManagerActivity;
import com.example.goo.carrotmarket.View.MyProfile.BuyList.BuyListActivity;
import com.example.goo.carrotmarket.View.MyProfile.ConcernList.ConcernListActivity;
import com.example.goo.carrotmarket.View.MyProfile.SellList.SellListActivity;
import com.example.goo.carrotmarket.View.MyProfile.SetMyLocation.SetMyLocationActivity;
import com.example.goo.carrotmarket.View.MyProfile.Setting.Logout.LogoutView;
import com.example.goo.carrotmarket.View.MyProfile.Setting.SettingActivity;

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
    @BindView(R.id.relative_profile)
    RelativeLayout relative_profile;
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
    String profileImage, nick, dong;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myprofile, container, false);

        ButterKnife.bind(this, view);

        sessionManager = new SessionManager(getContext());
        user = sessionManager.getUserDetail();
        presenter = new MyProfilePresenter(this, getContext());

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        setHasOptionsMenu(true);
        if(sessionManager.isLoggIn() == true){
            profileImage = user.get(sessionManager.PROFILEIMAGE).toString();
            nick = user.get(sessionManager.NICK).toString();
            dong = user.get(sessionManager.DONG).toString();
            presenter.setProfile(profileImage, nick, dong);
        }


        initButton();

        return view;
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
    public void setting() {
        Glide.with(getContext()).load(profileImage).diskCacheStrategy(DiskCacheStrategy.ALL).into(view_profileImg);
        login.setVisibility(View.GONE);
        txt_nick.setVisibility(View.VISIBLE);
        txt_nick.setText(nick);
        txt_dong.setVisibility(View.VISIBLE);
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
           /* case R.id.relative_my_list:
                presenter.nextActivityIsLogin(getContext(), CategoryActivity.class);
                break;*/
            case R.id.relative_set_location:
                presenter.nextActivityWithoutLogin(SetMyLocationActivity.class);
                break;
            case R.id.relative_authentication_location:
                presenter.nextActivityIsLogin(getContext(), HomeActivity2.class);
                break;
            case R.id.relative_keyword_alarm:
                presenter.nextActivityIsLogin(getContext(), HomeActivity2.class);
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
        }
    }


}