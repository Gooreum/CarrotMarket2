package com.example.goo.carrotmarket.View.MyProfile.SetMyLocation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.goo.carrotmarket.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-05.
 */

public class SetMyLocationActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.relative_location1)
    RelativeLayout relative_location1;
    @BindView(R.id.txt_location1)
    TextView txt_location1;

    @BindView(R.id.relative_location1_checked)
    RelativeLayout relative_location1_checked;
    @BindView(R.id.txt_location1_checked)
    TextView txt_location1_checked;

    @BindView(R.id.relative_location2)
    RelativeLayout relative_location2;
    @BindView(R.id.txt_location2)
    TextView txt_location2;

    @BindView(R.id.relative_location2_checked)
    RelativeLayout relative_location2_checked;
    @BindView(R.id.txt_location2_checked)
    TextView txt_location2_checked;

    boolean flag1, flag2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_my_location);

        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        flag1 = true;
        flag2 = false;

        initClicked();
    }

    public void initClicked() {
        relative_location1.setOnClickListener(this);
        relative_location1_checked.setOnClickListener(this);
        relative_location2.setOnClickListener(this);
        relative_location2_checked.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.relative_location1:
                flag1 = true;
                flag2 = false;
                relative_location1.setVisibility(View.GONE);
                relative_location1_checked.setVisibility(View.VISIBLE);
                relative_location2.setVisibility(View.VISIBLE);
                relative_location2_checked.setVisibility(View.GONE);

                break;
            case R.id.relative_location1_checked:
                if (flag1 == true) {
                    relative_location1_checked.setEnabled(false);
                } else {
                    relative_location1.setVisibility(View.VISIBLE);
                    relative_location1_checked.setVisibility(View.GONE);
                    relative_location2.setVisibility(View.VISIBLE);
                    relative_location2_checked.setVisibility(View.GONE);
                }

                break;
            case R.id.relative_location2:
                flag1 = false;
                flag2 = true;
                relative_location1.setVisibility(View.VISIBLE);
                relative_location1_checked.setVisibility(View.GONE);
                relative_location2.setVisibility(View.GONE);
                relative_location2_checked.setVisibility(View.VISIBLE);
                break;
            case R.id.relative_location2_checked:
                if (flag2 == true) {
                    relative_location1_checked.setEnabled(false);
                } else {
                    relative_location1.setVisibility(View.VISIBLE);
                    relative_location1_checked.setVisibility(View.GONE);
                    relative_location2.setVisibility(View.VISIBLE);
                    relative_location2_checked.setVisibility(View.GONE);
                }

                break;



        }
        relative_location1_checked.setEnabled(true);
        relative_location2_checked.setEnabled(true);
    }
}
