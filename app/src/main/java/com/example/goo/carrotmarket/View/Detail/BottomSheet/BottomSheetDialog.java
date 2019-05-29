package com.example.goo.carrotmarket.View.Detail.BottomSheet;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.goo.carrotmarket.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-02.
 */

public class BottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    private BottomSheetListener mListener;

    @BindView(R.id.selling)
    TextView selling;
    @BindView(R.id.reserving)
    TextView reserving;
    @BindView(R.id.deal_complete)
    TextView deal_complete;
    CardView cardView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);

        ButterKnife.bind(this, view);

        initClick();


        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selling:
                mListener.onButtonClicked(1);

                dismiss();
                break;

            case R.id.reserving:
                mListener.onButtonClicked(2);

                dismiss();
                break;

            case R.id.deal_complete:
                mListener.onButtonClicked(3);

                dismiss();
                break;

        }
    }

    public interface BottomSheetListener {

        void onButtonClicked( int state);
    }


    public void initClick() {
        selling.setOnClickListener(this);
        reserving.setOnClickListener(this);
        deal_complete.setOnClickListener(this);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement BottomSheetListener");
        }
    }


}
