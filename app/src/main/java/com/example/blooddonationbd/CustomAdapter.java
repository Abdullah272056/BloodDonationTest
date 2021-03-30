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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends FirebaseRecyclerAdapter<UserInformation, CustomAdapter.MyViewHolder>{
    Context context;
    String memberType;
    List<UserInformation> userInformationList;
    String adminNumber,adminName;


    public CustomAdapter(@NonNull FirebaseRecyclerOptions<UserInformation> options, Context context,String memberType,String adminNumber,String adminName) {
        super(options);
        this.context=context;
        this.memberType=memberType;
        this.adminNumber=adminNumber;
        this.adminName=adminName;
        userInformationList=new ArrayList<>();




    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull final UserInformation model) {

                holder.nameTextView.setText("Name : "+model.getUserName());
                holder.bloodGroupTextView.setText("Blood group : "+model.getBloodGroup());
                holder.locationTextView.setText("Location : "+model.getThanaName() +", "+
                        model.getDistrictName()+", "+model.getDivisionName());

                holder.lastDateTextView.setText("Last donate : "+model.getLastDate());
                holder.phoneNumberTextView.setText("Phone : "+model.getUserPhone());
                holder.readyForBdTextView.setText("Ready for donation : "+model.getReadyForBD());
                if (memberType.equals("admin")){
                    holder.phoneNumberTextView.setVisibility(View.VISIBLE);
                }

        holder.callImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (memberType.equals("admin")){

                    String phoneNumber=model.getUserPhone();

                    Toast.makeText(context, phoneNumber ,Toast.LENGTH_SHORT).show();

                    String s="tel:"+phoneNumber;
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(s));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);


                }
                else {



                    AlertDialog.Builder builder     =new AlertDialog.Builder(context);
                    LayoutInflater layoutInflater   =LayoutInflater.from(context);
                    View view                       =layoutInflater.inflate(R.layout.admin_phone,null);
                   TextView cancelTextView=view.findViewById(R.id.cancelTextViewId);
                   TextView callTextView=view.findViewById(R.id.callTextViewId);
                    TextView adminPhoneTextView=view.findViewById(R.id.adminPhoneTextViewId);
                    TextView adminNameTextView=view.findViewById(R.id.adminNameTextViewId);


                    builder.setView(view);
                    final AlertDialog   alertDialog   = builder.create();
                    alertDialog.setCancelable(false);


                    adminPhoneTextView.setText("Phone : "+adminNumber);
                    adminNameTextView.setText("Name : "+adminName +("admin"));
                    cancelTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });

                    callTextView.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();

                            String s="tel:"+adminNumber;
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
        TextView nameTextView,bloodGroupTextView,locationTextView,lastDateTextView,phoneNumberTextView,readyForBdTextView;
        ImageView callImageView;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView=itemView.findViewById(R.id.nameTextViewId);
            bloodGroupTextView=itemView.findViewById(R.id.bloodGroupTextViewId);
            locationTextView=itemView.findViewById(R.id.locationTextViewId);
            lastDateTextView=itemView.findViewById(R.id.lastDateTextViewId);
            phoneNumberTextView=itemView.findViewById(R.id.phoneNumberTextViewId);
            callImageView=itemView.findViewById(R.id.callImageViewId);
            readyForBdTextView=itemView.findViewById(R.id.readyForBdTextViewId);
            cardView=itemView.findViewById(R.id.cardViewId);



        }
    }
}
