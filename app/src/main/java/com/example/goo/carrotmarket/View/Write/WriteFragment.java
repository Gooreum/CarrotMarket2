package com.example.goo.carrotmarket.View.Write;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.goo.carrotmarket.Dialog.CustomDialogAuth;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-04-24.
 */

public class WriteFragment extends Fragment implements View.OnClickListener, WritingView {

    @BindView(R.id.relative_digital)
    RelativeLayout relative_digital;
    @BindView(R.id.relative_furniture)
    RelativeLayout relative_furniture;
    @BindView(R.id.relative_child)
    RelativeLayout relative_child;
    @BindView(R.id.relative_life)
    RelativeLayout relative_life;
    @BindView(R.id.relative_woman_fashion)
    RelativeLayout relative_woman_fashion;
    @BindView(R.id.relative_woman_etc)
    RelativeLayout relative_woman_etc;
    @BindView(R.id.relative_beauty)
    RelativeLayout relative_beauty;
    @BindView(R.id.relative_man)
    RelativeLayout relative_man;
    @BindView(R.id.relative_sports)
    RelativeLayout relative_sports;
    @BindView(R.id.relative_game)
    RelativeLayout relative_game;
    @BindView(R.id.relative_ticket)
    RelativeLayout relative_ticket;
    @BindView(R.id.relative_pet)
    RelativeLayout relative_pet;
    @BindView(R.id.relative_etc)
    RelativeLayout relative_etc;
    @BindView(R.id.relative_buying)
    RelativeLayout relative_buying;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Intent intent;

    SessionManager sessionManager;
    HashMap<String, String> user;
    WritingPresenter presenter;

    boolean isAuth = false;
    String location1, location2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_write, container, false);
        ButterKnife.bind(this, view);


        //로그인 세션
        sessionManager = new SessionManager(getContext());
        user = sessionManager.getUserDetail();

        setIsAuth();


        //dialog띄우기 위한 프레젠터
        presenter = new WritingPresenter((WritingView) this);

        //툴바
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        setHasOptionsMenu(true);

        //클릭 이벤트
        initOnClick();


        return view;
    }

    public void setIsAuth() {
        if (user.get(sessionManager.LOCATION1_STATE).equals("1") && user.get(sessionManager.LOCATION1_AUTH).equals("1")) {
            isAuth = true;
        } else if (user.get(sessionManager.LOCATION2_STATE).equals("1") && user.get(sessionManager.LOCATION2_AUTH).equals("1")) {
            isAuth = true;
        } else {
            isAuth = false;
        }
    }

    public void initOnClick() {
        relative_digital.setOnClickListener(this);
        relative_child.setOnClickListener(this);
        relative_woman_fashion.setOnClickListener(this);
        relative_beauty.setOnClickListener(this);
        relative_sports.setOnClickListener(this);
        relative_life.setOnClickListener(this);
        relative_etc.setOnClickListener(this);
        relative_furniture.setOnClickListener(this);
        relative_woman_etc.setOnClickListener(this);
        relative_man.setOnClickListener(this);
        relative_game.setOnClickListener(this);
        relative_pet.setOnClickListener(this);
        relative_buying.setOnClickListener(this);
        relative_ticket.setOnClickListener(this);

    }

    // Button.OnclickListener를 implements하므로 onClick() 함수를 오버라이딩.
    @Override
    public void onClick(View view) {
        //로그인 상태 검사
        if (sessionManager.isLoggIn() == true && isAuth == true) {
            switch (view.getId()) {
                case R.id.relative_digital:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "디지털/가전");
                    startActivity(intent);
                    break;
                case R.id.relative_child:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "유아동/유아도서");
                    startActivity(intent);
                    break;
                case R.id.relative_woman_fashion:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "여성의류");
                    startActivity(intent);
                    break;
                case R.id.relative_beauty:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "뷰티/미용");
                    startActivity(intent);
                    break;
                case R.id.relative_sports:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "스포츠/레저");
                    startActivity(intent);
                    break;
                case R.id.relative_ticket:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "도서/티켓/음반");
                    startActivity(intent);
                    break;
                case R.id.relative_etc:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "기타 중고물품");
                    startActivity(intent);
                    break;
                case R.id.relative_furniture:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "가구/인테리어");
                    startActivity(intent);
                    break;
                case R.id.relative_life:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "생활/가공식품");
                    startActivity(intent);
                    break;
                case R.id.relative_woman_etc:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "가구/인테리어");
                    startActivity(intent);
                    break;
                case R.id.relative_man:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "남성패션/잡화");
                    startActivity(intent);
                    break;
                case R.id.relative_game:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "게임/취미");
                    startActivity(intent);
                    break;
                case R.id.relative_pet:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "반려동물용품");
                    startActivity(intent);
                    break;
                case R.id.relative_buying:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "삽니다");
                    startActivity(intent);
                    break;

            }
            //로그인 되어 있지 않은 상황인 경우 다이얼로그 창 띄움
        } else if (sessionManager.isLoggIn() == false) {
            presenter.showDialog(getContext());
        } else if (sessionManager.isLoggIn() == true && isAuth == false) {
            CustomDialogAuth customDialogAuth = new CustomDialogAuth();
            customDialogAuth.show(getFragmentManager(),"CustomDialogAuth");

            Toast.makeText(getContext(), "동네를 인증해주세요!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void onGetResult(String message) {

    }

    @Override
    public void onGetResultId(List<Product> product) {

    }
}