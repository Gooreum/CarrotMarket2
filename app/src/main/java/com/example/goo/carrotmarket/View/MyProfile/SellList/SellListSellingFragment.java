package com.example.goo.carrotmarket.View.MyProfile.SellList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Detail.DetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-03.
 */

public class SellListSellingFragment extends Fragment implements SellListView{
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    SellListPresenter presenter;
    SellListAdapter adapter;
    SellListAdapter.ItemClickListener itemClickListener;

    List<Product> product_selling;
    SessionManager sessionManager;
    HashMap<String, String> user;

    String nick;
    int position, product_id;

    private Parcelable recyclerViewState;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sell_selling, container, false);
        ButterKnife.bind(this, view);


        //로그인 세션
        sessionManager = new SessionManager(getContext());
        user = sessionManager.getUserDetail();

        //리사이클러뷰 메니저
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        product_selling = new ArrayList<>();

        //프레젠터
        presenter = new SellListPresenter(this);
        nick = user.get(sessionManager.NICK).toString();
        presenter.getProducts(nick,1);

        //새로고침
        swipe_refresh.setOnRefreshListener(
                () -> presenter.getProducts(nick,1)
        );


        //리사이클러뷰 아이템 클릭 리스너
        itemClickListener = ((view1, position) -> {


            recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();


            String id = String.valueOf(product_selling.get(position).getId());
            String seller = product_selling.get(position).getSeller();
            int hide = product_selling.get(position).getHide();
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("seller", seller);
            intent.putExtra("hide",hide);
            intent.putExtra("position", position);
            intent.putExtra("fragment","selling");
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
        product_selling = products;

    }

    @Override
    public void onGetRefreshResult(List<Product> products) {
        product_selling = products;
        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        adapter = new SellListAdapter(getContext(), product_selling, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("onResume","----------------------SellListSellingFragment_onResume------------------");
        presenter.RefreshProducts(nick,1);

    }

}
