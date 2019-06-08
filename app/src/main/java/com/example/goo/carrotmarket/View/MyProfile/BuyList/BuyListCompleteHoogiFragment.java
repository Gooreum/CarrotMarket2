package com.example.goo.carrotmarket.View.MyProfile.BuyList;

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
import com.example.goo.carrotmarket.View.Hoogi.SeeMyHoogi.SeeMyHoogiActivity;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-03.
 */

public class BuyListCompleteHoogiFragment extends Fragment implements BuyListView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;


    BuyListPresenter presenter;
    SessionManager sessionManager;
    String nick;
    BuyListCompletedHoogiAdapter adapter;
    BuyListCompletedHoogiAdapter.ItemClickListener productListener, hoogiListener;
    HashMap<String, String> user;
    List<Product> products;
    private Parcelable recyclerViewState;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_complete_reply, container, false);
        ButterKnife.bind(this, view);

        presenter = new BuyListPresenter(this);
        sessionManager = new SessionManager(getContext());
        user = sessionManager.getUserDetail();
        nick = user.get(sessionManager.NICK).toString();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       // products = new ArrayList<>();
        presenter.getBuyList(nick, 1);


        //리사이클러뷰 아이템 클릭 리스너
        productListener = ((view1, position) -> {

            recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();

            String id = String.valueOf(products.get(position).getId());
            String seller = products.get(position).getSeller();
            int hide = products.get(position).getHide();
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("seller", seller);
            intent.putExtra("hide", hide);
            intent.putExtra("position", position);
            intent.putExtra("fragment", "home");
            getContext().startActivity(intent);
            Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();

        });

        //리사이클러뷰 아이템 클릭 리스너
        hoogiListener = ((view1, position) -> {


            recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
            int id = products.get(position).getId();
            String seller = products.get(position).getSeller();

            Intent intent = new Intent(getContext(), SeeMyHoogiActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("title", products.get(position).getTitle());
            intent.putExtra("seller", seller);
            intent.putExtra("buyer", nick);
            Toast.makeText(getContext(), "바이어는 " + nick + "판매자는 : " + seller+ "아이디 값은 : " + id, Toast.LENGTH_SHORT).show();
            startActivity(intent);

        });

        //새로고침
        swipe_refresh.setOnRefreshListener(
                () -> presenter.getBuyList(nick, 1)
        );
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
    public void onGetResult(List<Product> productList) {
        products = productList;

        adapter = new BuyListCompletedHoogiAdapter(getContext(), products, productListener, hoogiListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onGetResultResume(List<Product> productList) {

        products = productList;

        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        adapter = new BuyListCompletedHoogiAdapter(getContext(), products, productListener, hoogiListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getContext(), message.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume", "----------------------_onResume------------------");
        presenter.getRefreshBuyList(nick, 1);

    }
}
