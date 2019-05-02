package com.example.goo.carrotmarket.View.Authentication;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.goo.carrotmarket.Activity.Success;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.UserInfo;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Detail.DetailPresenter;
import com.example.goo.carrotmarket.View.LoginRegister.RegisterActivity;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-04-14.
 */

public class AuthenticationActivity extends AppCompatActivity {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private final static int REQUEST_CODE = 999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        ButterKnife.bind(this);


        startLoginPage(LoginType.PHONE);

    }

    private void startLoginPage(LoginType loginType) {
        if (loginType == LoginType.EMAIL) {

            Intent intent = new Intent(AuthenticationActivity.this, AccountKitActivity.class);
            AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                    new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.EMAIL, AccountKitActivity.ResponseType.TOKEN); // Use Token When 'Enable Client Access Token Flow' is Yes

            intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
            startActivityForResult(intent, REQUEST_CODE);

        } else if (loginType == LoginType.PHONE) {

            Intent intent = new Intent(AuthenticationActivity.this, AccountKitActivity.class);
            AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                    new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE, AccountKitActivity.ResponseType.TOKEN); // Use Token When 'Enable Client Access Token Flow' is Yes

            intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
            startActivityForResult(intent, REQUEST_CODE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {

            AccountKitLoginResult result = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);

            if (result.getError() != null) {

                Toast.makeText(this, "" + result.getError().getErrorType().getMessage(), Toast.LENGTH_SHORT).show();
                finish();
                //return;

            } else if (result.wasCancelled()) {

                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
                finish();
                //return;

            } else {

                if (result.getAccessToken() != null) {
                    Toast.makeText(this, "Success ! " + result.getAccessToken().getAccountId(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Success ! " + result.getAuthorizationCode().substring(0, 10), Toast.LENGTH_SHORT).show();
                }


                AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {

                    @Override
                    public void onSuccess(Account account) {
                        String phone = account.getPhoneNumber().toString();

                        Intent intent = new Intent(AuthenticationActivity.this, EmptyActivity.class);
                        intent.putExtra("phone", phone);
                        startActivity(intent);
                        AccountKit.logOut();
                        finish();


                    }

                    @Override
                    public void onError(AccountKitError accountKitError) {

                    }
                });

            }
        }
    }

    private void getHashKey() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }
}