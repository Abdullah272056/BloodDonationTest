package com.example.blooddonationbd;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
    protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull final UserInformation model) {
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

                    String phoneNumber=model.getUserPhone();

                    Toast.makeText(context, phoneNumber ,Toast.LENGTH_SHORT).show();

                    String s="tel:"+phoneNumber;
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(s));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);


                }else {

                    AlertDialog.Builder builder     =new AlertDialog.Builder(context);
                    LayoutInflater layoutInflater   =LayoutInflater.from(context);
                    View view                       =layoutInflater.inflate(R.layout.admin_phone,null);
                   TextView cancelTextView=view.findViewById(R.id.cancelTextViewId);
                   TextView callTextView=view.findViewById(R.id.callTextViewId);

                    builder.setView(view);
                    final AlertDialog   alertDialog   = builder.create();
                    alertDialog.setCancelable(false);



                    cancelTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });

                    callTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();

                            String s="tel:"+"01994215664";
                            Intent intent=new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse(s));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    });

                    alertDialog.show();


//                    Toast.makeText(context, "access not available! \n  " +
//                            "please contact admin number !", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.user_recyclerview_item,parent,false);
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
