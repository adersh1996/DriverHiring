package com.project.driverhiring;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.io.File;

public class DriverDetailsActivity extends AppCompatActivity {

    private TextView tvOne;
    private ShapeableImageView ivDriverProPic;
    private TextView tvDriverName;
    private LinearLayout layoutOne;
    private TextView tvDriverOverallRating;
    private TextView tvNoOfRatingsDriver;
    private LinearLayout layoutTwo;
    private RelativeLayout callBtn;
    private RelativeLayout emailBt;
    private LinearLayout layoutThree;
    private TextView tvDriverAddress;
    private TextView tvDriverDistrict;
    private TextView tvDriverState;
    private LinearLayout layoutFour;
    private RecyclerView rvDriverReview;
    private RelativeLayout layoutFive;
    private EditText etMsgToDriver;
    private View viewOne;
    private LinearLayout rcbookLayout;
    private EditText etChooseRc;
    private ImageView chooseIdRcImg;
    private View viewNewOne;
    private View viewTwo;
    private LinearLayout insuranceLayout;
    private EditText etChooseInsurance;
    private ImageView chooseIdInsuranceImg;
    private View viewThree;
    private View viewFour;
    private LinearLayout pollutionLayout;
    private EditText etChoosePollution;
    private ImageView chooseIdPollutionImg;
    private View viewFive;
    private RelativeLayout btBookDriver;

    private static final int STORAGE_PERMISSION_CODE = 101;
    private static final int RESULT_LOAD_RC_IMAGE = 102;
    private static final int RESULT_LOAD_POLLUTION_IMAGE = 103;
    private static final int RESULT_LOAD_INSURANCE_IMAGE = 104;
    private File rcImageFile, pollutionImageFile, insuranceImageFile;
    String rcImagePicturePath, pollutionImagePicturePath, insuranceImagePicturePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_details);
        initView();

        SharedPreferences sharedPreferences = getSharedPreferences("userPref", MODE_PRIVATE);
        String vehicleType = sharedPreferences.getString("vehicleType", "");
        String drivePref = sharedPreferences.getString("drivePref", "");
        String destinationLat = sharedPreferences.getString("destination_lat", "");
        String destinationLong = sharedPreferences.getString("destination_longitude", "");
        String userLatitude = sharedPreferences.getString("user_latitude", "");
        String userLongitude = sharedPreferences.getString("user_longitude", "");
        String userId = sharedPreferences.getString("userId", "");
        String driverId = getIntent().getStringExtra("driver_id");

        tvDriverName.setText(getIntent().getStringExtra("driver_name"));
        tvDriverOverallRating.setText(getIntent().getStringExtra("driver_rating"));
        tvNoOfRatingsDriver.setText(getIntent().getStringExtra("driver_no_of_rating"));
        tvDriverAddress.setText(getIntent().getStringExtra("driver_address"));
        tvDriverDistrict.setText(getIntent().getStringExtra("driver_district"));
        tvDriverState.setText(getIntent().getStringExtra("driver_state"));

        //tvDriverOverallRating.setText(getIntent().getStringExtra("driver_rating"));

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + getIntent().getStringExtra("driver_phone")));//change the number
                startActivity(callIntent);
            }
        });

        emailBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("*/*");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, getIntent().getStringExtra("driver_mail"));
                startActivity(emailIntent);
            }
        });

        etChooseRc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(DriverDetailsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
                } else {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_RC_IMAGE);
                }
            }
        });
        etChooseInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(DriverDetailsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
                } else {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_INSURANCE_IMAGE);
                }
            }
        });
        etChoosePollution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(DriverDetailsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
                } else {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_POLLUTION_IMAGE);
                }
            }
        });


        btBookDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etChooseRc.getText().toString().isEmpty() ||
                        etChooseInsurance.getText().toString().isEmpty() ||
                        etChoosePollution.getText().toString().isEmpty()) {
                    etChooseRc.setError("chooseFile");
                    etChooseInsurance.setError("chooseFile");
                    etChoosePollution.setError("chooseFile");
                } else {
                    if (etMsgToDriver.getText().toString().isEmpty()) {
                        etMsgToDriver.setError("please enter something");
                    } else {
                        apiCall();
                    }
                }

            }
        });


    }

    private void apiCall() {

    }

    private void initView() {
        tvOne = findViewById(R.id.tv_one);
        ivDriverProPic = findViewById(R.id.iv_driver_pro_pic);
        tvDriverName = findViewById(R.id.tv_driver_name);
        layoutOne = findViewById(R.id.layout_one);
        tvDriverOverallRating = findViewById(R.id.tv_driver_overall_rating);
        tvNoOfRatingsDriver = findViewById(R.id.tv_no_of_ratings_driver);
        layoutTwo = findViewById(R.id.layout_two);
        callBtn = findViewById(R.id.call_btn);
        emailBt = findViewById(R.id.email_bt);
        layoutThree = findViewById(R.id.layout_three);
        tvDriverAddress = findViewById(R.id.tv_driver_address);
        tvDriverDistrict = findViewById(R.id.tv_driver_district);
        tvDriverState = findViewById(R.id.tv_driver_state);
        layoutFour = findViewById(R.id.layout_four);
        rvDriverReview = findViewById(R.id.rv_driver_review);
        layoutFive = findViewById(R.id.layout_five);
        etMsgToDriver = findViewById(R.id.et_msg_to_driver);
        viewOne = findViewById(R.id.view_one);
        rcbookLayout = findViewById(R.id.rcbook_layout);
        etChooseRc = findViewById(R.id.et_choose_rc);
        chooseIdRcImg = findViewById(R.id.choose_id_rc_img);
        viewNewOne = findViewById(R.id.view_new_one);
        viewTwo = findViewById(R.id.view_two);
        insuranceLayout = findViewById(R.id.insurance_layout);
        etChooseInsurance = findViewById(R.id.et_choose_insurance);
        chooseIdInsuranceImg = findViewById(R.id.choose_id_insurance_img);
        viewThree = findViewById(R.id.view_three);
        viewFour = findViewById(R.id.view_four);
        pollutionLayout = findViewById(R.id.pollution_layout);
        etChoosePollution = findViewById(R.id.et_choose_pollution);
        chooseIdPollutionImg = findViewById(R.id.choose_id_pollution_img);
        viewFive = findViewById(R.id.view_five);
        btBookDriver = findViewById(R.id.bt_book_driver);
    }

    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), permission) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(DriverDetailsActivity.this, new String[]{permission}, requestCode);
        } else {
            Toast.makeText(getApplicationContext(), "Permission Already granted", Toast.LENGTH_SHORT).show();
        }

    }

    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Storage Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_RC_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            rcImagePicturePath = cursor.getString(columnIndex);
            cursor.close();
            rcImageFile = new File(rcImagePicturePath);
            etChooseRc.setText(rcImagePicturePath);
        }
        if (requestCode == RESULT_LOAD_INSURANCE_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            insuranceImagePicturePath = cursor.getString(columnIndex);
            cursor.close();
            insuranceImageFile = new File(insuranceImagePicturePath);
            etChooseInsurance.setText(insuranceImagePicturePath);
        }
        if (requestCode == RESULT_LOAD_POLLUTION_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            pollutionImagePicturePath = cursor.getString(columnIndex);
            cursor.close();
            pollutionImageFile = new File(pollutionImagePicturePath);
            etChoosePollution.setText(pollutionImagePicturePath);
        }
    }
}