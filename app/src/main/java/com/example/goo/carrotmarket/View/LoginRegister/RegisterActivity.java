package com.example.goo.carrotmarket.View.LoginRegister;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Home.HomeActivity2;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Goo on 2019-05-02.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterView, View.OnClickListener {
    private int REQUEST_CODE = 100;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_profileImg)
    CircleImageView view_profileImg;
    @BindView(R.id.nick)
    EditText txt_nick;
    @BindView(R.id.done)
    TextView done;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    RegisterPresenter presenter;
    SessionManager sessionManager;
    HashMap<String, String> hashUser;

    String phone, nick, city1, gu1, dong1, location1_state, city2, gu2, dong2, location2_state, profileImg;

    Intent intent;

    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        intent = getIntent();
        //툴바 생성
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);


        //세션 매니저 생성
        sessionManager = new SessionManager(this);
        hashUser = sessionManager.getUserDetail();
        phone = getIntent().getStringExtra("phone");
        city1 = hashUser.get(sessionManager.CITY).toString();
        gu1 = hashUser.get(sessionManager.GU).toString();
        dong1 = hashUser.get(sessionManager.DONG).toString();
        location1_state = hashUser.get(sessionManager.LOCATION1_STATE).toString();
        city2 = hashUser.get(sessionManager.CITY2).toString();
        gu2 = hashUser.get(sessionManager.GU2).toString();
        dong2 = hashUser.get(sessionManager.DONG2).toString();
        location2_state = hashUser.get(sessionManager.LOCATION2_STATE).toString();


        //내 프로필 화면에서 닉네임 및 프로필 이미지를 변경하려고 할 경우!
        if (intent != null) {
            nick = intent.getStringExtra("nick");
            //profileImg = hashUser.get(sessionManager.PROFILEIMAGE).toString();
            // Glide.with(this).load(profileImg).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.profileimg).into(view_profileImg);
            txt_nick.setText(nick);
        } else {
            Toast.makeText(this, "null입니다.", Toast.LENGTH_SHORT).show();
        }

        selectImages();

        done.setOnClickListener((View.OnClickListener) this);
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
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void failToGetLocation(String message) {
        Toast.makeText(RegisterActivity.this, message.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(String message) {
        profileImg = message.toString();

        Toast.makeText(RegisterActivity.this, "회원가입이 완료 되었습니다." + profileImg, Toast.LENGTH_SHORT).show();
        sessionManager.createSession(true, nick, profileImg, city1, gu1, dong1, location1_state, city2, gu2, dong2, "0");
        Intent intent = new Intent(RegisterActivity.this, HomeActivity2.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.done:

                nick = txt_nick.getText().toString();
                //
                if (nick.isEmpty()) {
                    txt_nick.setError("닉네임을 정해주세요");
                } else if (bitmap == null) {
                    Toast.makeText(this, "프로필 사진을 정해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    String imageUrl = imageToString(bitmap);

                    presenter = new RegisterPresenter(this);
                    presenter.makeMember(imageUrl, nick, city1, gu1, dong1, phone);

                    break;
                }


        }
    }

    //사진첩에서 사진 선택하기
    public void selectImages() {

        view_profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showChooser();
            }
        });
    }

    private void showChooser() {
        // Use the GET_CONTENT intent from the utility class

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);

        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // The reason for the existence of aFileChooser
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                view_profileImg.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);

    }
}
