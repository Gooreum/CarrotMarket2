package com.example.goo.carrotmarket.View.Write;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.FileUtils;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Detail.DetailActivity;

import java.io.ByteArrayOutputStream;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-04-25.
 */

public class WritingActivity extends AppCompatActivity implements WritingView, View.OnClickListener {
    private static final int MY_PERMISSIONS_REQUEST = 100;
    private int PICK_IMAGE_FROM_GALLERY_REQUEST = 1;
    private int REQUEST_CODE = 100;

    @BindView(R.id.category)
    TextView editCategory;
    @BindView(R.id.toolbar)
    Toolbar tb;
    @BindView(R.id.relativeImg)
    RelativeLayout relativeImg;
    @BindView(R.id.countImg)
    TextView countImg;
    @BindView(R.id.relative_category)
    RelativeLayout relative_category;
    @BindView(R.id.title)
    EditText editTitle;
    @BindView(R.id.price)
    EditText editPrice;
    @BindView(R.id.description)
    EditText editDescription;
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.done)
    TextView done;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.recyclerView_images)
    RecyclerView recyclerView_images;
    @BindView(R.id.relative_deal_dim)
    RelativeLayout relative_deal_dim;
    @BindView(R.id.relative_deal_checked)
    RelativeLayout relative_deal_checked;

    private DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private String date;
    private String result;

    WritingPresenter presenter;

    ArrayList<Uri> arrayList;

    List<Bitmap> arrayBit;
    List<String> array;
    List<Product> listId;
    WritingAdapter.ItemClickListener itemClickListener;
    WritingAdapter mAdapter;
    Intent intent;

    SessionManager sessionManager;
    HashMap<String, String> user;

    private Bitmap bitmap;

    boolean flag;
    int dealable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);


        ButterKnife.bind(this);
        intent = getIntent();

        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();

        arrayList = new ArrayList<Uri>();
        arrayBit = new ArrayList<>();
        array = new ArrayList<>();
        listId = new ArrayList<>();


        setSupportActionBar(tb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        editCategory.setText(intent.getStringExtra("category"));
        editPrice.addTextChangedListener(price());

        flag = false;
        dealable = 0;

        presenter = new WritingPresenter(this);

        //toolbar back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        relative_deal_checked.setOnClickListener(this);
        relative_deal_dim.setOnClickListener(this);

        itemClickListener = ((view1, position) -> {

            arrayList.remove(position);

            mAdapter.notifyDataSetChanged();
            int size = arrayList.size();
            countImg.setText(size + "");
        });


        //글쓰기 완료버튼
        done.setOnClickListener(this);

        //카테고리 선택하기 화면으로 이동
        relative_category.setOnClickListener(this);

        selectImages();

    }


    //가격을 적으면 1,000 / 100,000/ 이런식으로 ','를 찍어주게 한다.
    public TextWatcher price() {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence.toString()) && !charSequence.toString().equals(result)) {
                    result = decimalFormat.format(Double.parseDouble(charSequence.toString().replaceAll(",", "")));
                    editPrice.setText(result);
                    editPrice.setSelection(result.length());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        return watcher;
    }


    //완료버튼
    public void done() {


        String title = editTitle.getText().toString().trim();
        String description = editDescription.getText().toString().trim();
        String category = editCategory.getText().toString().trim();
        String price = editPrice.getText().toString().trim();
        String city = user.get(sessionManager.CITY).toString();
        String gu = user.get(sessionManager.GU).toString();
        String dong = user.get(sessionManager.DONG).toString();
        String seller = user.get(sessionManager.NICK).toString();
        date = getCurrentTime("yyyyMMddHHmmssSSS");
        System.out.println("현재시간 : " + date);

        if (title.isEmpty()) {
            editTitle.setError("제목을 입력해주세요.");
        } else if (price.isEmpty()) {
            editPrice.setError("가격을 입력해주세요.");
        } else if (description.isEmpty()) {
            editDescription.setError("내용을 입력해주세요.");
        } else {

            //상품 서버에 올리기
            if (arrayList.size() == 0) { //이미지 선택하지 않은 경우
                presenter.uploadProdcut(seller, category, title, price, dealable, description, city, gu, dong, date);
            } else { //이미지를 선택한 경우
                presenter.uploadProdcut(seller, category, title, price, dealable, description, city, gu, dong, date, arrayList, date, this);

            }

            done.setEnabled(false);

        }


    }



    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(String message) {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        //presenter.getDataId(date);

    }

    @Override
    public void onGetResultId(List<Product> products) {

        listId = products;
        System.out.println("아이디 값은 --------------------: " + listId.get(0).getId());
        System.out.println("판매자 이름은 ------------------ : " + listId.get(0).getSeller());
        Intent intent2 = new Intent(WritingActivity.this, DetailActivity.class);
        intent2.putExtra("id", String.valueOf(listId.get(0).getId()));
        intent2.putExtra("seller", listId.get(0).getSeller());
        startActivity(intent2);
        finish();

    }

    //사진첩에서 사진 선택하기
    public void selectImages() {

        relativeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showChooser();
            }
        });
    }

    private void showChooser() {
        // Use the GET_CONTENT intent from the utility class

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");

        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // The reason for the existence of aFileChooser
        }
    }

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // If the file selection was successful
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();

                int currentItem = 0;
                while (currentItem < count) {
                    Uri imageUri = data.getClipData().getItemAt(currentItem).getUri();
                    //do something with the image (save it to some directory or whatever you need to do with it here)

                    currentItem = currentItem + 1;
                    Log.d("Uri Selected", imageUri.toString());

                    try {
                        //bitmap = MediaStore.SliderImages.Media.getBitmap(getContentResolver(), imageUri);
                        // Get the file path from the URI
                        final String path = FileUtils.getPath(this, imageUri);
                        Log.d("Multiple File Selected", path);
                        arrayList.add(imageUri);
                        //arrayBit.add(bitmap);
                        Log.d("ArrayList Print", arrayList.get(currentItem).toString());

                    } catch (Exception e) {
                        Log.e("File select error", e.getLocalizedMessage());
                    }
                }

                countImg.setText(arrayList.size() + "");
                mAdapter = new WritingAdapter(WritingActivity.this, arrayList, itemClickListener);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(WritingActivity.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerView_images.setLayoutManager(layoutManager);
                recyclerView_images.setAdapter(mAdapter);
            } else if (data.getData() != null) {
                //do something with the image (save it to some directory or whatever you need to do with it here)
                final Uri uri = data.getData();


                // Log.i(TAG, "Uri = " + uri.toString());
                try {

                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                    // Get the file path from the URI
                    final String path = FileUtils.getPath(this, uri);
                    Log.d("Single File Selected", path);
                    arrayList.add(uri);
                    countImg.setText(arrayList.size() + "");
                    mAdapter = new WritingAdapter(WritingActivity.this, arrayList, itemClickListener);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(WritingActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    recyclerView_images.setLayoutManager(layoutManager);
                    recyclerView_images.setAdapter(mAdapter);
                } catch (Exception e) {
                    // Log.e(TAG, "File select error", e);
                }
            }

            //카테고리 값 가져오기
        } else if (requestCode == 1111 && resultCode == 2) {
            editCategory.setText(data.getStringExtra("category").toString());
        }


        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.done:
                done();
                break;

            case R.id.relative_category:

                Intent intent = new Intent(WritingActivity.this, WriteCategoryActivity.class);
                startActivityForResult(intent, 1111);

                break;

            case R.id.relative_deal_checked:

                dealable = 0;
                relative_deal_dim.setVisibility(View.VISIBLE);
                relative_deal_checked.setVisibility(View.GONE);
                break;

            case R.id.relative_deal_dim:

                dealable = 1;
                relative_deal_dim.setVisibility(View.GONE);
                relative_deal_checked.setVisibility(View.VISIBLE);
                break;
        }
    }

    public static String getCurrentTime(String timeFormat) {
        return new SimpleDateFormat(timeFormat).format(System.currentTimeMillis());
    }
}