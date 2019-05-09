package com.example.goo.carrotmarket.View.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.goo.carrotmarket.Model.Category;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Detail.DetailActivity;
import com.example.goo.carrotmarket.View.Home.Search.SearchActivity;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Goo on 2019-04-24.
 */

public class HomeFragment extends Fragment implements HomeView {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.location)
    TextView location;


    HomePresenter presenter;
    HomeAdapter adapter;
    HomeAdapter.ItemClickListener itemClickListener;

    List<Product> product;

    SessionManager sessionManager;
    HashMap<String, String> user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        //로그인 세션
        sessionManager = new SessionManager(getContext());
        user = sessionManager.getUserDetail();


        String dong = user.get(sessionManager.DONG).toString();


        //툴바
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        setHasOptionsMenu(true);
        location.setText(dong);

        //리사이클러뷰 메니저
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //프레젠터
        presenter = new HomePresenter(this, getContext());

        if (sessionManager.isLoggIn() == true) {
            String nick = user.get(sessionManager.NICK).toString();
            System.out.println("닉네임은 : " + nick);
            presenter.getProducts(nick);

            //새로고침
            swipe_refresh.setOnRefreshListener(
                    () -> presenter.getProducts(nick)
            );

        } else {
            presenter.getProducts();

            //새로고침
            swipe_refresh.setOnRefreshListener(
                    () -> presenter.getProducts()
            );
        }


        //리사이클러뷰 아이템 클릭 리스너
        itemClickListener = ((view1, position) -> {

            String id = String.valueOf(product.get(position).getId());
            String seller = product.get(position).getSeller();
            int hide = product.get(position).getHide();
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("seller", seller);
            intent.putExtra("hide", hide);
            getContext().startActivity(intent);
            Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();

        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.appbar_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                return true;
            case R.id.search:
                presenter.nextActivityIsLoginForResult(getContext(), SearchActivity.class);

                return true;

            case R.id.filter:
                presenter.nextActivityIsLoginForResult(getContext(), HomeManagerActivity.class);

                return true;

            case R.id.alarm:


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress() {
        swipe_refresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipe_refresh.setRefreshing(false);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(List<Product> products) {
        adapter = new HomeAdapter(getContext(), products, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        product = products;
    }

    @Override
    public void onGetResultCategory(List<Category> products) {

    }

    @Override
    public void onGetResultCategory(String text) {

    }

    @Override
    public void moveActivity(Class activity) {
        Intent intent = new Intent(getActivity(), activity);
        getActivity().startActivity(intent);

    }

    @Override
    public void moveActivityForResult(Class activity) {
        Intent intent = new Intent(getActivity(), activity);

        getActivity().startActivityForResult(intent, 999);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //getActivity();
        if (requestCode == 999 && resultCode == Activity.RESULT_OK) {

            System.out.println("받아온 데이터 값은 : " + data.getIntExtra("managerDone", 1));
            //새로고침
            swipe_refresh.setOnRefreshListener(
                    () -> presenter.getProducts()
            );

        }
    }
}
