package com.example.goo.carrotmarket.View.Write;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.goo.carrotmarket.Dialog.BottomSheet.BottomSheetDialog;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Authentication.AuthenticationActivity;
import com.example.goo.carrotmarket.View.Category.CategoryActivity;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-04-24.
 */

public class WriteFragment extends Fragment implements View.OnClickListener,WritingView {

    /*    @BindView(R.id.digital)
        TextView digital;
        @BindView(R.id.child)
        TextView child;
        @BindView(R.id.female)
        TextView female;
        @BindView(R.id.beauty)
        TextView beauty;
        @BindView(R.id.sports)
        TextView sports;
        @BindView(R.id.entertain)
        TextView entertain;
        @BindView(R.id.furniture)
        TextView furniture;
        @BindView(R.id.etc)
        TextView etc;
        @BindView(R.id.life)
        TextView life;
        @BindView(R.id.female_etc)
        TextView female_etc;
        @BindView(R.id.male)
        TextView male;
        @BindView(R.id.game)
        TextView game;
        @BindView(R.id.pet)
        TextView pet;
        @BindView(R.id.buying)
        TextView buying;*/
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_write, container, false);
        ButterKnife.bind(this, view);


        //로그인 세션
        sessionManager = new SessionManager(getContext());
        user = sessionManager.getUserDetail();

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
/*        digital.setOnClickListener(this);
        child.setOnClickListener(this);
        female.setOnClickListener(this);
        beauty.setOnClickListener(this);
        sports.setOnClickListener(this);
        entertain.setOnClickListener(this);
        etc.setOnClickListener(this);
        furniture.setOnClickListener(this);
        life.setOnClickListener(this);
        female_etc.setOnClickListener(this);
        male.setOnClickListener(this);
        game.setOnClickListener(this);
        pet.setOnClickListener(this);
        buying.setOnClickListener(this);*/

    }

    // Button.OnclickListener를 implements하므로 onClick() 함수를 오버라이딩.
    @Override
    public void onClick(View view) {
        //로그인 상태 검사
        if (sessionManager.isLoggIn() == true) {
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

              /*  case R.id.digital:

                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "디지털/가전");
                    startActivity(intent);


                    break;
                case R.id.child:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "유아동/유아도서");
                    startActivity(intent);
                    break;
                case R.id.female:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "여성의류");
                    startActivity(intent);
                    break;
                case R.id.beauty:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "뷰티/미용");
                    startActivity(intent);
                    break;
                case R.id.sports:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "스포츠/레저");
                    startActivity(intent);
                    break;
                case R.id.entertain:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "도서/티켓/음반");
                    startActivity(intent);
                    break;
                case R.id.etc:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "기타 중고물품");
                    startActivity(intent);
                    break;
                case R.id.furniture:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "가구/인테리어");
                    startActivity(intent);
                    break;
                case R.id.life:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "생활/가공식품");
                    startActivity(intent);
                    break;
                case R.id.female_etc:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "가구/인테리어");
                    startActivity(intent);
                    break;
                case R.id.male:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "남성패션/잡화");
                    startActivity(intent);
                    break;
                case R.id.game:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "게임/취미");
                    startActivity(intent);
                    break;
                case R.id.pet:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "반려동물용품");
                    startActivity(intent);
                    break;
                case R.id.buying:
                    intent = new Intent(getActivity(), WritingActivity.class);
                    intent.putExtra("category", "삽니다.");
                    startActivity(intent);
                    break;
*/
            }
            //로그인 되어 있지 않은 상황인 경우 다이얼로그 창 띄움
        } else {
            presenter.showDialog(getContext());
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