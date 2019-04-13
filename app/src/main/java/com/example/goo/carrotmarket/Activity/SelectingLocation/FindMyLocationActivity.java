package com.example.goo.carrotmarket.Activity.SelectingLocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.goo.carrotmarket.API.ApiInterface;

import com.example.goo.carrotmarket.Activity.Intro.IntroActivity;
import com.example.goo.carrotmarket.Activity.Main.HomeActivity;
import com.example.goo.carrotmarket.Model.Location;
import com.example.goo.carrotmarket.R;

import java.util.List;

/**
 * Created by Goo on 2019-04-13.
 */

public class FindMyLocationActivity extends AppCompatActivity implements FindMyLocationView {
    Intent intent;
    Toolbar tb;
    //private SearchView searchView ;
    private EditText edit_search;
    ProgressBar progressBar;
    private ApiInterface apiInterface;

    private List<Location> list;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView_LocationList adapter;
    private RecyclerView_LocationList.ItemClickListener itemClickListener;

    private FindMyLocationPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_my_location);

        tb = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(tb);

        progressBar = findViewById(R.id.progress);

        recyclerView = findViewById(R.id.recyclerview_location_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);



        edit_search = findViewById(R.id.edit_search);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);


        presenter = new FindMyLocationPresenter(this);

        presenter.search(edit_search);

        itemClickListener = ((view, position) -> {
           String location = list.get(position).getLocation();
            intent = new Intent(FindMyLocationActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_action, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                intent = new Intent(FindMyLocationActivity.this, IntroActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void failToGetLocation(String message) {
        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(List<Location> location) {
        list = location;
        adapter = new RecyclerView_LocationList(FindMyLocationActivity.this, list,itemClickListener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

