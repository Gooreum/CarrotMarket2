package com.example.goo.carrotmarket.View.Chat.ChatRoom.Reserve;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.goo.carrotmarket.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-30.
 */

public class ReserveActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.calendar)
    TextView calendar;
    @BindView(R.id.alarm_time)
    TextView alarm_time;
    @BindView(R.id.switch_alarm)
    Switch switch_alarm;
    String day;
    String date, time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        ButterKnife.bind(this);

        setToolbar();

        setButtonListener();

    }

    public void setButtonListener() {
        calendar.setOnClickListener(this);
        alarm_time.setOnClickListener(this);
    }

    public void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_chat_reserve_alarm, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                finish();

                return true;

            case R.id.done:


                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.calendar:
                showDatePickerDialog();

                break;

            case R.id.txt_alarm:

                break;

        }
    }

    private void showTimePickerDialog() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, hour, min,DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }


    private void showDatePickerDialog() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                year, month, day);

        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek) {

            case 2:
                day = "(월)";
                break;

            case 3:
                day = "(화)";
                break;
            case 4:
                day = "(수)";
                break;
            case 5:
                day = "(목)";
                break;
            case 6:
                day = "(금)";
                break;
            case 7:
                day = "(토)";
                break;
            case 8:
                day = "(일)";
                break;
        }
        //String currentDateString = DateFormat.getDateInstance().format(c.getTime());
        date = year + "년 " + (month + 1) + "월 " + dayOfMonth + "일 " + day;
        showTimePickerDialog();
       // calendar.setText(date);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int min) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, hour);
        c.set(Calendar.MINUTE, min);

        time = hour + ":" + min;

        calendar.setText(date + " " + time);

    }
}