package com.example.blooddonationbd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CustomAdapter extends FirebaseRecyclerAdapter<UserInformation, CustomAdapter.MyViewHolder>{
    Context context;
    public CustomAdapter(@NonNull FirebaseRecyclerOptions<UserInformation> options, Context context) {
        super(options);

        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull UserInformation model) {
        holder.nameTextView.setText("Name : "+model.getUserName());
        holder.bloodGroupTextView.setText("Blood group : "+model.getBloodGroup());
        holder.locationTextView.setText("Location : "+model.getThanaName() +", "+
                model.getDistrictName()+", "+model.getCountryName());
        holder.lastDateTextView.setText("Last donate : "+model.getLastDate());
        holder.phoneNumberTextView.setText("Phone : "+model.getUserPhone());

        if (new SharePref().loadId(context).equals("hIFyeCPR8gh9VghjneEr0l8xEJj1")){
            holder.phoneNumberTextView.setVisibility(View.VISIBLE);
        }


        holder.callImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new SharePref().loadId(context).equals("hIFyeCPR8gh9VghjneEr0l8xEJj1")){
                    // add code
                }else {
                    Toast.makeText(context, "access not available! \n  " +
                            "please contact admin number !", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.recyclerview_item,parent,false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView,bloodGroupTextView,locationTextView,lastDateTextView,phoneNumberTextView;
        ImageView callImageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView=itemView.findViewById(R.id.nameTextViewId);
            bloodGroupTextView=itemView.findViewById(R.id.bloodGroupTextViewId);
            locationTextView=itemView.findViewById(R.id.locationTextViewId);
            lastDateTextView=itemView.findViewById(R.id.lastDateTextViewId);
            phoneNumberTextView=itemView.findViewById(R.id.phoneNumberTextViewId);
            callImageView=itemView.findViewById(R.id.callImageViewId);



        }
    }
}
