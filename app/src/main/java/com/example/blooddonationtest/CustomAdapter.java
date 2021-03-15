package com.example.blooddonationtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    private List<UserInformation> userInformationList;

    public CustomAdapter(Context context, List<UserInformation> userInformationList) {
        this.context = context;
        this.userInformationList = userInformationList;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.recyclerview_item,parent,false);
        return new CustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        holder.nameTextView.setText(userInformationList.get(position).getUserName());
        holder.bloodGroupTextView.setText(userInformationList.get(position).getBloodGroup());
        holder.locationTextView.setText(userInformationList.get(position).getThanaName() +", "+
                userInformationList.get(position).getDistrictName()+", "+userInformationList.get(position).getCountryName());
        holder.lastDateTextView.setText(userInformationList.get(position).getLastDate());
    }

    @Override
    public int getItemCount() {
        return userInformationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView,bloodGroupTextView,locationTextView,lastDateTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView=itemView.findViewById(R.id.nameTextViewId);
            bloodGroupTextView=itemView.findViewById(R.id.bloodGroupTextViewId);
            locationTextView=itemView.findViewById(R.id.locationTextViewId);
            lastDateTextView=itemView.findViewById(R.id.lastDateTextViewId);


        }
    }
}
