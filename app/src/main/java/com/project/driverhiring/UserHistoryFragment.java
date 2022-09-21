package com.project.driverhiring;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.driverhiring.adpters.DriversListAdapter;
import com.project.driverhiring.adpters.UserHistoryAdapter;


public class UserHistoryFragment extends Fragment {


    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_history, container, false);
        initView(view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        UserHistoryAdapter userHistoryAdapter = new UserHistoryAdapter(getContext());
        recyclerView.setAdapter(userHistoryAdapter);

        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
    }
}