package com.example.goo.carrotmarket.View.Detail.Reply;

import android.view.View;
import android.widget.TextView;

import com.example.goo.carrotmarket.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by Goo on 2019-05-18.
 */

public class ProductViewHolder extends ChildViewHolder {
    private TextView mTextView;

    public ProductViewHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.textView);
    }

    public void bind(Product product) {
        mTextView.setText(product.name);
    }
}
