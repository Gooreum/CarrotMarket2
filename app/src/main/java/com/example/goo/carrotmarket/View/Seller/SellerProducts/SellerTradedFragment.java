package com.example.goo.carrotmarket.View.Seller.SellerProducts;

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
import com.example.goo.carrotmarket.View.Detail.DetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-05.
 */

public class SellerTradedFragment extends Fragment implements SellerView{

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    private SellerPresenter presenter;
    private SellerAdapter adapter;
    private SellerAdapter.ItemClickListener itemClickListener;

    private List<Product> product;



    private String nick ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seller_products_traded, container, false);
        ButterKnife.bind(this, view);

        nick = SellerActivity.nick;
        //리사이클러뷰 메니저
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //프레젠터
        presenter = new SellerPresenter(this);
        presenter.getProductsTraded(nick,2);

        //새로고침
        swipe_refresh.setOnRefreshListener(
                () -> presenter.getProductsTraded(nick,2)
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
        adapter = new SellerAdapter(getContext(), products, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        product = products;
    }
}