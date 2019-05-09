package com.example.goo.carrotmarket.View.MyProfile.ConcernList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-03.
 */

public class ConcernListLocalAdFragment extends Fragment implements ConcernListView{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_concern_local_ad, container, false);
        ButterKnife.bind(this, view);


        return view;
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
    public void onGetResult(List<Product> products) {

    }
}
