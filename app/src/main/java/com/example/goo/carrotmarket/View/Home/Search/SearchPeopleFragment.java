package com.example.goo.carrotmarket.View.Home.Search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.UserInfo;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.GlobalBus.Events;
import com.example.goo.carrotmarket.Util.GlobalBus.GlobalBus;
import com.example.goo.carrotmarket.View.Seller.SellerProfile.SellerProfileActivity;
import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-07.
 */

public class SearchPeopleFragment extends Fragment implements SearchView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    SearchPresenter presenter;
    SearchPeopleAdapter adapter;
    SearchPeopleAdapter.ItemClickListener itemClickListener;

    List<UserInfo> userInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_selling, container, false);
        ButterKnife.bind(this, view);
        GlobalBus.getBus().register(this);

        //프레젠터
        presenter = new SearchPresenter(this);
        //  presenter.searchUser(SearchActivity.search_view);


        //리사이클러뷰 아이템 클릭 리스너
        itemClickListener = ((view1, position) -> {

            String id = String.valueOf(userInfo.get(position).getId());

            String nick = userInfo.get(position).getNick();
            Intent intent = new Intent(getContext(), SellerProfileActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("nick", nick);
            getContext().startActivity(intent);

        });

        return view;
    }

    //SearchActivity로부터 쿼리 값을 가져와서 사람 이름 검색 결과 받아오기
    @Subscribe
    public void connectEvent1(Events.Event1 event1) {
        Log.i("MyTag", event1.getMessage());
        presenter.getDataUser(event1.getMessage());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        GlobalBus.getBus().unregister(this);

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(List<Product> products) {

    }

    @Override
    public void onGetResultUserInfo(List<UserInfo> userInfos) {

        userInfo = userInfos;

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SearchPeopleAdapter(getContext(), userInfos, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);


    }


}
