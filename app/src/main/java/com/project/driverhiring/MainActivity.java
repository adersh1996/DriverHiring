package com.project.driverhiring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private int SPLASH_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // showLoadingIndicator(true);
        delayFlow();
    }
    private void delayFlow() {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {


                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                startActivity(intent);
                finish();
                //showLoadingIndicator(false);
                overridePendingTransition(0, 0);
            }
        }, SPLASH_DELAY);
    }
}