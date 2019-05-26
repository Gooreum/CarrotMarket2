package com.example.goo.carrotmarket.View.Detail.Reply;

import android.view.View;
import android.widget.TextView;

import com.example.goo.carrotmarket.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by Goo on 2019-05-18.
 */

public class CompanyViewHolder extends GroupViewHolder {
    private TextView mTextView;

    public CompanyViewHolder(View itemView) {
        super(itemView);

        mTextView = itemView.findViewById(R.id.textView);
    }

    public void bind(Company company){
        mTextView.setText(company.getTitle());
    }
}
