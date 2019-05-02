package com.example.goo.carrotmarket.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.goo.carrotmarket.R;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;

/**
 * Created by Goo on 2019-04-14.
 */

public class Success extends AppCompatActivity {

    private Button btnLogOut;
  //  private EditText editEmail, editPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        btnLogOut = findViewById(R.id.btnLogOut);


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountKit.logOut();
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(Account account) {
                EditText editUserId, editPhone, editEmail;
                editUserId = findViewById(R.id.editUserId);
                editEmail = findViewById(R.id.editEmail);
                editPhone = findViewById(R.id.editPhone);

                editUserId.setText(String.format("User ID %s",account.getId()));
                editEmail.setText(String.format("User Email %s",account.getEmail()));
                editPhone.setText(String.format("User PhoneNumber %s",account.getPhoneNumber()));
            }

            @Override
            public void onError(AccountKitError accountKitError) {

            }
        });
    }
}
