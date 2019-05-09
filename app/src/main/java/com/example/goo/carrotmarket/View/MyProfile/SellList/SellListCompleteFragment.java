package com.example.goo.carrotmarket.View.MyProfile.SellList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Detail.DetailActivity;
import com.example.goo.carrotmarket.View.MyProfile.ConcernList.ConcernListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-03.
 */

public class SellListCompleteFragment extends Fragment implements SellListView {
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    SellListPresenter presenter;
    SellListAdapter adapter;
    SellListAdapter.ItemClickListener itemClickListener;

    List<Product> product;
    SessionManager sessionManager;
    HashMap<String, String> user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable  ViewGroup container,@Nullable  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sell_complete, container, false);
        ButterKnife.bind(this, view);

        //로그인 세션
        sessionManager = new SessionManager(getContext());
        user = sessionManager.getUserDetail();

        //리사이클러뷰 메니저
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        product = new ArrayList<>();

        //프레젠터
        presenter = new SellListPresenter(this);
        String nick = user.get(sessionManager.NICK).toString();
        presenter.getProducts(nick,3);

        //새로고침
        swipe_refresh.setOnRefreshListener(
                () -> presenter.getProducts(nick,3)
        );


        //리사이클러뷰 아이템 클릭 리스너
        itemClickListener = ((view1, position) -> {

            String id = String.valueOf(product.get(position).getId());
            String seller = product.get(position).getSeller();
            int hide = product.get(position).getHide();
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("seller", seller);
            intent.putExtra("hide",hide);
            getContext().startActivity(intent);
            Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();

        });
        return view;
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
        adapter = new SellListAdapter(getContext(), products, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        product = products;
    }
}
