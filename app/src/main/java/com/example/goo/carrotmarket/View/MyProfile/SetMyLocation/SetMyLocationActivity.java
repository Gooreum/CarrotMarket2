package com.example.goo.carrotmarket.View.MyProfile.SetMyLocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goo.carrotmarket.Model.UserInfo;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.GlobalBus.Events;
import com.example.goo.carrotmarket.Util.GlobalBus.GlobalBus;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.SelectingLocation.FindMyLocationActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Goo on 2019-05-05.
 */

public class SetMyLocationActivity extends AppCompatActivity implements View.OnClickListener, SetMyLocationView {
    public static int REQUEST_CODE_LOCATION1 = 1111;
    public static int REQUEST_CODE_LOCATION2 = 2222;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.relative_location1)
    RelativeLayout relative_location1;
    @BindView(R.id.txt_location1)
    TextView txt_location1;


    @BindView(R.id.relative_location1_checked)
    RelativeLayout relative_location1_checked;
    @BindView(R.id.txt_location1_checked)
    TextView txt_location1_checked;

    @BindView(R.id.relative_location2)
    RelativeLayout relative_location2;
    @BindView(R.id.txt_location2)
    TextView txt_location2;

    @BindView(R.id.relative_location2_checked)
    RelativeLayout relative_location2_checked;
    @BindView(R.id.txt_location2_checked)
    TextView txt_location2_checked;

    @BindView(R.id.relative_add_location)
    RelativeLayout relative_add_location;

    @BindView(R.id.relative_add_location2)
    RelativeLayout relative_add_location2;

    boolean flag1, flag2;
    Intent intent;

    SetMyLocationPresenter presenter;
    SetMyLocationView view;


    SessionManager sessionManager;
    HashMap<String, String> user;
    UserInfo userInfo;
    String nick, city1, gu1, dong1, city2, gu2, dong2, location1_state, location2_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_my_location);

        ButterKnife.bind(this);

        intent = getIntent();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        presenter = new SetMyLocationPresenter(this, this);
        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();

        //내 동네 설정 값에 따라 버튼 UI 설정
        setButtonState();

        //버튼 클릭 리스너
        setClickListener();
    }

    //버튼 이벤트 리스너
    public void setClickListener() {
        relative_location1.setOnClickListener(this);
        relative_location1_checked.setOnClickListener(this);
        relative_location2.setOnClickListener(this);
        relative_location2_checked.setOnClickListener(this);
        relative_add_location.setOnClickListener(this);
        relative_add_location2.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_back, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                //뒤로가기 했을 때, '홈화면'으로 돌아가는 경우라면 버스 이벤트 발생 시키기
                Events.BackToHomeFromSetMyLocation backToHome = new Events.BackToHomeFromSetMyLocation();
                GlobalBus.getBus().post(backToHome);

                finish();

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.relative_location1:

                presenter.updateMyLocation(nick, city1, gu1, dong1, 1, 1);


                break;
            case R.id.relative_location1_checked:
                presenter.updateMyLocation(nick, city1, gu1, dong1, 2, 1);


                break;
            case R.id.relative_location2:
                presenter.updateMyLocation(nick, city2, gu2, dong2, 1, 2);

                break;
            case R.id.relative_location2_checked:
                presenter.updateMyLocation(nick, city2, gu2, dong2, 2, 2);


                break;
            case R.id.relative_add_location:
                intent = new Intent(this, FindMyLocationActivity.class);
                intent.putExtra("intent", 1);
                startActivityForResult(intent, REQUEST_CODE_LOCATION1);
                break;

            case R.id.relative_add_location2:

                intent = new Intent(this, FindMyLocationActivity.class);
                intent.putExtra("intent", 1);
                startActivityForResult(intent, REQUEST_CODE_LOCATION2);

                break;
        }

    }

    //내 동네 설정에 따른 버튼 UI 설정
    public void setButtonState() {
        nick = user.get(sessionManager.NICK).toString();
        city1 = user.get(sessionManager.CITY).toString();
        gu1 = user.get(sessionManager.GU).toString();
        dong1 = user.get(sessionManager.DONG).toString();
        city2 = user.get(sessionManager.CITY2).toString();
        gu2 = user.get(sessionManager.GU2).toString();
        dong2 = user.get(sessionManager.DONG2).toString();
        location1_state = user.get(sessionManager.LOCATION1_STATE).toString();
        location2_state = user.get(sessionManager.LOCATION2_STATE).toString();


        txt_location1.setText(dong1);
        txt_location1_checked.setText(dong1);
        txt_location2.setText(dong2);
        txt_location2_checked.setText(dong2);

        if (location1_state.equals("1") && location2_state.equals("0") && dong2.equals("empty")) {
            relative_location1.setVisibility(View.GONE);
            relative_location1_checked.setVisibility(View.VISIBLE);

            relative_location2.setVisibility(View.GONE);
            relative_location2_checked.setVisibility(View.GONE);

            relative_add_location.setVisibility(View.GONE);
            relative_add_location2.setVisibility(View.VISIBLE);
        } else if (location1_state.equals("1") && location2_state.equals("0") && !dong2.equals("empty")) {
            relative_location1.setVisibility(View.GONE);
            relative_location1_checked.setVisibility(View.VISIBLE);

            relative_location2.setVisibility(View.VISIBLE);
            relative_location2_checked.setVisibility(View.GONE);

            relative_add_location.setVisibility(View.GONE);
            relative_add_location2.setVisibility(View.GONE);
        } else if (location1_state.equals("0") && location2_state.equals("1") && dong1.equals("empty")) {
            relative_location1.setVisibility(View.GONE);
            relative_location1_checked.setVisibility(View.GONE);

            relative_location2.setVisibility(View.GONE);
            relative_location2_checked.setVisibility(View.VISIBLE);

            relative_add_location.setVisibility(View.VISIBLE);
            relative_add_location2.setVisibility(View.GONE);
        } else if (location1_state.equals("0") && location2_state.equals("1") && !dong1.equals("empty")) {
            relative_location1.setVisibility(View.VISIBLE);
            relative_location1_checked.setVisibility(View.GONE);

            relative_location2.setVisibility(View.GONE);
            relative_location2_checked.setVisibility(View.VISIBLE);

            relative_add_location.setVisibility(View.GONE);
            relative_add_location2.setVisibility(View.GONE);
        }

    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, "네트워크 오류", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        if (message.equals("1")) {
            relative_location1.setVisibility(View.GONE);
            relative_location1_checked.setVisibility(View.VISIBLE);
            relative_location2.setVisibility(View.VISIBLE);
            relative_location2_checked.setVisibility(View.GONE);
            sessionManager.updateLocation1(city1, gu1, dong1, "1", "0");
        } else if (message.equals("2")) {
            relative_location1.setVisibility(View.VISIBLE);
            relative_location1_checked.setVisibility(View.GONE);
            relative_location2.setVisibility(View.GONE);
            relative_location2_checked.setVisibility(View.VISIBLE);
            sessionManager.updateLocation2(city2, gu2, dong2, "0", "1");
        } else if (message.equals("3")) {
            relative_location1.setVisibility(View.GONE);
            relative_location1_checked.setVisibility(View.GONE);
            relative_location2.setVisibility(View.GONE);
            relative_location2_checked.setVisibility(View.VISIBLE);
            relative_add_location.setVisibility(View.VISIBLE);
            sessionManager.updateLocation1("empty", "empty", "empty", "0", "1");
        } else if (message.equals("4")) {
            relative_location1.setVisibility(View.GONE);
            relative_location1_checked.setVisibility(View.VISIBLE);
            relative_location2.setVisibility(View.GONE);
            relative_location2_checked.setVisibility(View.GONE);
            relative_add_location2.setVisibility(View.VISIBLE);
            sessionManager.updateLocation2("empty", "empty", "empty", "1", "0");
        } else if (message.equals("0") || message.equals("-1")) {
            Toast.makeText(this, "네트워크 오류", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onGetResultFromSelection(String message) {

        if (message.equals("1")) {
            relative_location1.setVisibility(View.GONE);
            relative_location1_checked.setVisibility(View.VISIBLE);
            relative_location2.setVisibility(View.VISIBLE);
            relative_location2_checked.setVisibility(View.GONE);
            relative_add_location.setVisibility(View.GONE);
           // sessionManager.updateLocation1(city1, gu1, dong1, "1", "0");
            Snackbar.make(getWindow().getDecorView().getRootView(), "동네 설정이 완료되었습니다.", Snackbar.LENGTH_LONG).show();
        } else if (message.equals("2")) {
            relative_location1.setVisibility(View.VISIBLE);
            relative_location1_checked.setVisibility(View.GONE);
            relative_location2.setVisibility(View.GONE);
            relative_location2_checked.setVisibility(View.VISIBLE);
            relative_add_location2.setVisibility(View.GONE);
          //  sessionManager.updateLocation2(city2, gu2, dong2, "0", "1");
            Snackbar.make(getWindow().getDecorView().getRootView(), "동네 설정이 완료되었습니다.", Snackbar.LENGTH_LONG).show();

        } else if (message.equals("0") || message.equals("-1")) {
            Toast.makeText(this, "네트워크 오류", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void warning() {

    }

    @Override
    public void onGetUserInfo() {

        nick = user.get(sessionManager.NICK).toString();
        city1 = user.get(sessionManager.CITY).toString();
        gu1 = user.get(sessionManager.GU).toString();
        dong1 = user.get(sessionManager.DONG).toString();
        city2 = user.get(sessionManager.CITY2).toString();
        gu2 = user.get(sessionManager.GU2).toString();
        dong2 = user.get(sessionManager.DONG2).toString();
        location1_state = user.get(sessionManager.LOCATION1_STATE).toString();
        location2_state = user.get(sessionManager.LOCATION2_STATE).toString();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_LOCATION1 && resultCode == RESULT_OK) {
            nick = user.get(sessionManager.NICK).toString();
            String city1 = data.getStringExtra("city").toString();
            String gu1 = data.getStringExtra("gu").toString();
            String dong1 = data.getStringExtra("dong").toString();


            sessionManager.updateLocation1(city1, gu1, dong1, "1", "0");
            txt_location1_checked.setText(dong1);
            txt_location1.setText(dong1);


            presenter.updateMyLocationFromSelection(nick,city1,gu1,dong1,1,1);


            //setButtonState();
        } else if (requestCode == REQUEST_CODE_LOCATION2 && resultCode == RESULT_OK) {
            nick = user.get(sessionManager.NICK).toString();
            String city2 = data.getStringExtra("city").toString();
            String gu2 = data.getStringExtra("gu").toString();
            String dong2 = data.getStringExtra("dong").toString();
            sessionManager.updateLocation2(city2, gu2, dong2, "0", "1");
            txt_location2_checked.setText(dong2);
            txt_location2.setText(dong2);

            presenter.updateMyLocationFromSelection(nick,city2,gu2,dong2,1,2);
            //   setButtonState();
        }
    }
}
