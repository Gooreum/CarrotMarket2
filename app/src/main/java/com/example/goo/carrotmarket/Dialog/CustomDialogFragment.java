package com.example.goo.carrotmarket.Dialog;

import android.app.DialogFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.View.CustomDialogView;

/**
 * Created by Goo on 2019-04-14.
 */

public class CustomDialogFragment extends DialogFragment implements CustomDialogView {

    private static final String TAG = "MyCustomDialog";
    private Button btnConfirm, btnCancel;
    private TextView textLocation,textLocation_confirm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialog_set_location, container, false);

        btnConfirm = rootView.findViewById(R.id.btnConfirm);
        btnCancel = rootView.findViewById(R.id.btnCancel);
        textLocation = rootView.findViewById(R.id.textLocation);
        textLocation_confirm = rootView.findViewById(R.id.textLocation_confirm);


        //textLocation.setText(setText)

        btnConfirm(btnConfirm);
        btnCancel(btnCancel);


        return rootView;

    }

    public String setText(String location){
        Log.d(TAG, location);

       return location;
    }


    @Override
    public void btnConfirm(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");

                getDialog().dismiss();
            }
        });
    }

    @Override
    public void btnCancel(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();


            }
        });
    }
}
