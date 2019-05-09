package com.example.goo.carrotmarket.View.Write;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.example.goo.carrotmarket.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-04-28.
 */

public class WriteCategoryActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView_Category)
    RecyclerView recyclerView_Category;


    List<String> arrayList;


    WriteCategoryAdapter adapter;
    WriteCategoryAdapter.ItemClickListener itemClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_category_choose);


        ButterKnife.bind(this);
        recyclerView_Category.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);


        init();
    }

    public void init() {

        arrayList = new ArrayList<>();
        arrayList.add(0, "디지털/가전");
        arrayList.add(1, "유아동/유아도서");
        arrayList.add(2, "여성의류");
        arrayList.add(3, "여성잡화");
        arrayList.add(4, "뷰티/미용");
        arrayList.add(5, "도서/티켓/음반");
        arrayList.add(6, "가구/인테리어");
        arrayList.add(7, "생활/가공식품");
        arrayList.add(8, "남성패션/잡화");
        arrayList.add(9, "스포츠/레저");
        arrayList.add(10, "게임/취미");
        arrayList.add(11, "반려동물용품");
        arrayList.add(12, "기타 중고물품");
        arrayList.add(13, "삽니다");

        itemClickListener = ((view, position) -> {

            String category = arrayList.get(position).toString();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("category", category);
            setResult(2, resultIntent);
            finish();
        });


        adapter = new WriteCategoryAdapter(WriteCategoryActivity.this, arrayList, itemClickListener);
        recyclerView_Category.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_back, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
