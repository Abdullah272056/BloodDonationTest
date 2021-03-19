package com.example.blooddonationbd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CustomAdapter extends FirebaseRecyclerAdapter<UserInformation, CustomAdapter.MyViewHolder>{

    public CustomAdapter(@NonNull FirebaseRecyclerOptions<UserInformation> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull UserInformation model) {
        holder.nameTextView.setText("Name : "+model.getUserName());
        holder.bloodGroupTextView.setText("Blood group : "+model.getBloodGroup());
        holder.locationTextView.setText("Location : "+model.getThanaName() +", "+
                model.getDistrictName()+", "+model.getCountryName());
        holder.lastDateTextView.setText("Last donate : "+model.getLastDate());

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.recyclerview_item,parent,false);
        return new MyViewHolder(view);
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
