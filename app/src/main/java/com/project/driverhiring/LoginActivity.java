package com.project.driverhiring;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.project.driverhiring.model.Root;
import com.project.driverhiring.retrofit.APIInterface;
import com.project.driverhiring.retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener {


    private ImageView icon;
    @NotEmpty
    private EditText etEmailLogin;
    @NotEmpty
    private EditText etPassLogin;
    private TextView forgotPassword;
    private LinearLayout layoutBtn;
    private TextView btLogin;
    private LinearLayout signin;
    private RadioGroup radioGroup;
    private RadioButton rbUser;
    private RadioButton rbDriver;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        validator = new Validator(this);
        validator.setValidationListener(this);

        radioGroup.check(R.id.rb_user);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validator.validate();

//                int checkedId = radioGroup.getCheckedRadioButtonId();
//                switch (checkedId) {
//                    case R.id.rb_user:
//                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                        startActivity(intent);
//                        break;
//
//                    case R.id.rb_driver:
//                        Intent intentDriver = new Intent(LoginActivity.this, DriverHomeActivity.class);
//                        startActivity(intentDriver);
//                        break;
//                }

            }
        });
    }

    private void initView() {
        icon = findViewById(R.id.icon);
        etEmailLogin = findViewById(R.id.et_email_login);
        etPassLogin = findViewById(R.id.et_pass_login);
        forgotPassword = findViewById(R.id.forgotPassword);
        layoutBtn = findViewById(R.id.layout_btn);
        btLogin = findViewById(R.id.bt_login);
        signin = findViewById(R.id.signin);
        radioGroup = findViewById(R.id.radio_group);
        rbUser = findViewById(R.id.rb_user);
        rbDriver = findViewById(R.id.rb_driver);
    }

    @Override
    public void onValidationSucceeded() {

        int checkedId = radioGroup.getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.rb_user:
                userLoginApiCall(etEmailLogin.getText().toString(), etPassLogin.getText().toString());
                break;

            case R.id.rb_driver:
                driverLoginApiCall(etEmailLogin.getText().toString(), etPassLogin.getText().toString());
                break;
        }

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }

        }

    }

    public void driverLoginApiCall(String phoneNumber, String passWord) {
        APIInterface api = ApiClient.getClient().create(APIInterface.class);
        api.DRIVER_LOGIN(phoneNumber, passWord).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    Root root = response.body();
                    if (root.status) {
                        Intent intentDriver = new Intent(LoginActivity.this, DriverHomeActivity.class);
                        startActivity(intentDriver);
                    } else {
                        Toast.makeText(LoginActivity.this, root.message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void userLoginApiCall(String phoneNumber, String passWord) {
        APIInterface api = ApiClient.getClient().create(APIInterface.class);
        api.USER_LOGIN(phoneNumber, passWord).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    Root root = response.body();
                    if (root.status) {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}