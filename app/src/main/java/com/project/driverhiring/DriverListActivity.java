package com.project.driverhiring;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.driverhiring.adpters.DriversListAdapter;

public class DriverListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_list);
        initView();


       // Toast.makeText(this, getIntent().getStringExtra("vehicleType"), Toast.LENGTH_SHORT).show();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DriversListAdapter driversListAdapter = new DriversListAdapter(getApplicationContext());
        recyclerView.setAdapter(driversListAdapter);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
    }
}