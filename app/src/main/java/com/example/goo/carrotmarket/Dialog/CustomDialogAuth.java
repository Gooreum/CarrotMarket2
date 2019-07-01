package com.example.goo.carrotmarket.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.View.MyProfile.AuthenticateMyLocation.AuthenticateMyLocationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-07-01.
 */

public class CustomDialogAuth extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "CustomDialogAuth";

    @BindView(R.id.dong)
    TextView dong;
    @BindView(R.id.txt_auth)
    TextView txt_auth;
    @BindView(R.id.close)
    TextView close;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_dialog_auth_location, container, false);
        ButterKnife.bind(this, view);
        setButtonListener();
        return view;
    }

    public void setButtonListener() {
        txt_auth.setOnClickListener(this);
        close.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.txt_auth:
                Intent intent = new Intent(getActivity(), AuthenticateMyLocationActivity.class);
               
                startActivity(intent);
                break;

            case R.id.close:
                getDialog().dismiss();
                break;
        }

    }
}
