package com.example.goo.carrotmarket.View.Write;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.goo.carrotmarket.API.ApiClient;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Util.FileUtils;
import com.example.goo.carrotmarket.View.Authentication.AuthenticationActivity;
import com.example.goo.carrotmarket.View.Detail.DetailView;
import com.example.goo.carrotmarket.View.Home.HomeActivity2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Goo on 2019-04-26.
 */

public class WritingPresenter {

    private WritingView view;

    public WritingPresenter(WritingView view) {
        this.view = view;
    }

    //사진 없는 게시글 작성
    public void uploadProdcut(final String seller, final String category, final String title, final String price, final int dealable, final String description, final String city, final String gu, final String dong, final String date) {
        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<Product> call = apiInterface.saveProduct(seller, category, title, price, dealable, description, city, gu, dong, date);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResult(response.body().getMessage());

                } else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }

    //사진 있는 게시글 작성
    public void uploadProdcut(final String seller, final String category, final String title, final String price, final int dealable, final String description, final String city, final String gu, final String dong, final String date, final ArrayList<Uri> arrayList, final String date2, final Context context) {
        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<Product> call = apiInterface.saveProduct(seller, category, title, price, dealable, description, city, gu, dong, date);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {

                    // view.onGetResult(response.message());
                    uploadImages(arrayList, date2, context);

                } else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }


    public void uploadImages(final ArrayList<Uri> arrayList, String date2, Context context) {
        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);

        List<MultipartBody.Part> parts = new ArrayList<>();

        if (arrayList != null) {
            // create part for file (photo, video, ...)
            for (int i = 0; i < arrayList.size(); i++) {
                parts.add(prepareFilePart("image" + i, arrayList.get(i), context));
            }
        }

        RequestBody size = createPartFromString("" + parts.size());
        RequestBody date = createPartFromString("" + date2);
        Call<ResponseBody> call = apiInterface.uploadMultiple(size, date, parts);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResult(response.toString());
                    getDataId(date2);

                } else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }

    //글쓰고 나서 나의 글 id 값 받아오기
    //검색 결과 받아오기
    void getDataId(String date) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<List<Product>> call = apiInterface.getMyWritingId(date);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    System.out.println("eeeeeeeeeeeeeeeeeeeeeee"+ date);
                    view.onGetResultId(response.body());

                }else{
                    System.out.print("안보내졌엉~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }
    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }


    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri, Context context) {
        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(context, fileUri);

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(context.getContentResolver().getType(fileUri)),
                        file
                );
        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }


    //다이얼로그 띄우기
    void showDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //builder.setTitle("'" + dong + "'" + "으로 입장 할까요?");
        builder.setMessage("회원가입 또는 로그인후 이용할 수 있습니다.");
        //builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("로그인/가입", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(context, AuthenticationActivity.class);
                //intent.putExtra("Location", dong);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Snackbar.make(textView, "아니오 버튼이 눌렸습니다.", Snackbar.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
