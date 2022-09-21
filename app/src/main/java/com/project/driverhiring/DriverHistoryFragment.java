package com.project.driverhiring;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.driverhiring.adpters.DriverHistoryAdapter;
import com.project.driverhiring.adpters.UserHistoryAdapter;


public class DriverHistoryFragment extends Fragment {

    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_history, container, false);
        initView(view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DriverHistoryAdapter driverHistoryAdapter = new DriverHistoryAdapter(getContext());
        recyclerView.setAdapter(driverHistoryAdapter);




        return view;
    }
    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
    }
}