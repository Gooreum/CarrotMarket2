package com.example.goo.carrotmarket.View.SelectingLocation;

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
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.goo.carrotmarket.API.ApiInterface;

import com.example.goo.carrotmarket.View.Intro.IntroActivity;
import com.example.goo.carrotmarket.Dialog.CustomDialogFragment;
import com.example.goo.carrotmarket.Dialog.CustomDialogPresenter;
import com.example.goo.carrotmarket.Model.Location;
import com.example.goo.carrotmarket.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-04-13.
 */

public class FindMyLocationActivity extends AppCompatActivity implements FindMyLocationView, View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar tb;
    @BindView(R.id.recyclerview_location_list)
    RecyclerView recyclerView;
    @BindView(R.id.edit_search)
    EditText edit_search;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    Intent intent;
    @BindView(R.id.back)
    ImageButton back;


    private ApiInterface apiInterface;

    private List<Location> list;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView_LocationList adapter;
    private RecyclerView_LocationList.ItemClickListener itemClickListener;

    private FindMyLocationPresenter presenter;
    private CustomDialogPresenter presenterDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_my_location);

        ButterKnife.bind(this);

        setSupportActionBar(tb);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기


        initRecyclerView();
        initBack();
        initPresenter();
        initItemClickListener();

    }

    public void initRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

    }

    public void initBack() {
        back.setOnClickListener(this);
    }

    public void initPresenter() {
        presenter = new FindMyLocationPresenter(this,this);
        presenter.search(edit_search);
    }

    public void initItemClickListener() {
        itemClickListener = ((view, position) -> {

            presenter.showDialog( this,list.get(position).getCity(),list.get(position).getGu(),list.get(position).getDong());

        });
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
        adapter = new RecyclerView_LocationList(FindMyLocationActivity.this, list, itemClickListener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDialog(String location) {
        CustomDialogFragment dialog = new CustomDialogFragment();

        dialog.setText(location);
        dialog.show(getFragmentManager(), "MyCustomDialog");
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.back:
                finish();

                break;

        }
    }
}

