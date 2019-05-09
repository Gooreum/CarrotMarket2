package com.example.goo.carrotmarket.View.Category;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.View.Category.Collection.CollectionActivity;
import com.example.goo.carrotmarket.View.Home.Search.SearchActivity;
import com.example.goo.carrotmarket.View.Write.WritingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-04-24.
 */

public class CategoryFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    @BindView(R.id.relative_hot)
    RelativeLayout relative_hot;
    @BindView(R.id.relative_collection)
    RelativeLayout relative_collection;
    @BindView(R.id.relative_company)
    RelativeLayout relative_company;


    Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);


        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        setHasOptionsMenu(true);


        initOnclick();
        return view;

    }

    public void initOnclick() {
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
        relative_hot.setOnClickListener(this);
        relative_collection.setOnClickListener(this);
        relative_company.setOnClickListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.appbar_category, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                return true;
            case R.id.search:
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                return true;

            case R.id.alarm:


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Button.OnclickListener를 implements하므로 onClick() 함수를 오버라이딩.
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.relative_digital:
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category", "디지털/가전");
                startActivity(intent);
                break;
            case R.id.relative_child:
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category", "유아동/유아도서");
                startActivity(intent);
                break;
            case R.id.relative_woman_fashion:
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category", "여성의류");
                startActivity(intent);
                break;
            case R.id.relative_beauty:
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category", "뷰티/미용");
                startActivity(intent);
                break;
            case R.id.relative_sports:
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category", "스포츠/레저");
                startActivity(intent);
                break;
            case R.id.relative_ticket:
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category", "도서/티켓/음반");
                startActivity(intent);
                break;
            case R.id.relative_etc:
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category", "기타 중고물품");
                startActivity(intent);
                break;
            case R.id.relative_furniture:
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category", "가구/인테리어");
                startActivity(intent);
                break;
            case R.id.relative_life:
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category", "생활/가공식품");
                startActivity(intent);
                break;
            case R.id.relative_woman_etc:
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category", "가구/인테리어");
                startActivity(intent);
                break;
            case R.id.relative_man:
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category", "남성패션/잡화");
                startActivity(intent);
                break;
            case R.id.relative_game:
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category", "게임/취미");
                startActivity(intent);
                break;
            case R.id.relative_pet:
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category", "반려동물용품");
                startActivity(intent);
                break;
            case R.id.relative_buying:
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category", "삽니다.");
                startActivity(intent);
                break;
            case R.id.relative_hot:

                break;

            case R.id.relative_collection:
                intent = new Intent(getActivity(), CollectionActivity.class);

                startActivity(intent);

                break;

            case R.id.relative_company:

                break;

        }
    }
}