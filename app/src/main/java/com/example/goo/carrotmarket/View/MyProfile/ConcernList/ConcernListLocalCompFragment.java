package com.example.goo.carrotmarket.View.MyProfile.ConcernList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.goo.carrotmarket.R;

import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-03.
 */

public class ConcernListLocalCompFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_concern_local_company, container, false);
        ButterKnife.bind(this, view);


        return view;
    }
}