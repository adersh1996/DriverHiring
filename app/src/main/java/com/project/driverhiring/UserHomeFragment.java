package com.project.driverhiring;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;


public class UserHomeFragment extends Fragment {


    private RelativeLayout btChooseVehicle;
    private LinearLayout tvHomeUser;
    private RelativeLayout btChooseRidePref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);
        initView(view);

        btChooseVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(getContext(), btChooseVehicle);
                popupMenu.getMenuInflater().inflate(R.menu.vehicle_type_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.lmv:
                                Intent intentOne = new Intent(getActivity(), MapActivityChooseDestination.class);
                                intentOne.putExtra("vehicleType", "LMV");
                                startActivity(intentOne);
                                break;
                            case R.id.hgmv:
                                Intent intentTwo = new Intent(getActivity(), MapActivityChooseDestination.class);
                                intentTwo.putExtra("vehicleType", "HGMV");
                                startActivity(intentTwo);
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });


        return view;
    }

    private void initView(View view) {
        btChooseVehicle = view.findViewById(R.id.bt_choose_vehicle);
        tvHomeUser = view.findViewById(R.id.tv_home_user);
        btChooseRidePref = view.findViewById(R.id.bt_choose_ride_pref);
    }
}