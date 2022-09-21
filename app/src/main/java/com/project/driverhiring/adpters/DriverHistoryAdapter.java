package com.project.driverhiring.adpters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.driverhiring.DriverTripDetailsActivity;
import com.project.driverhiring.R;
import com.project.driverhiring.UserTripDetailsActivity;

public class DriverHistoryAdapter extends RecyclerView.Adapter<DriverHistoryAdapter.MyViewHolder> {

    Context context;

    public DriverHistoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout_driver_history, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

//        holder.workshopName.setText(root.workshopDetails.get(position).workshop_name);
//        holder.workshopDistanceTv.setText(root.workshopDetails.get(position).distance);
//        Glide.with(context).load(root.workshopDetails.get(position).file).into(holder.workshopImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DriverTripDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
