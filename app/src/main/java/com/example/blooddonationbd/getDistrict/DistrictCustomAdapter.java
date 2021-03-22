package com.example.blooddonationbd.getDistrict;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.blooddonationbd.R;

import java.util.List;

public class DistrictCustomAdapter extends RecyclerView.Adapter<DistrictCustomAdapter.MyViewHolder>{

    Context context;
    List<GetDistrictData> districtDataList;

    DistrictCustomAdapter.OnContactClickListener2 onContactClickListener2;
    public DistrictCustomAdapter(Context context, List<GetDistrictData> districtDataList, DistrictCustomAdapter.OnContactClickListener2 onContactClickListener2) {
        this.context = context;
        this.districtDataList = districtDataList;
        this.onContactClickListener2 = onContactClickListener2;


    }
    @NonNull
    @Override
    public DistrictCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        return new DistrictCustomAdapter.MyViewHolder(view,onContactClickListener2);
    }

    @Override
    public void onBindViewHolder(@NonNull DistrictCustomAdapter.MyViewHolder holder, final int position) {
        holder.nameTextView.setText(String.valueOf(districtDataList.get(position).getDistrict()));

    }

    @Override
    public int getItemCount(){
        return districtDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        DistrictCustomAdapter.OnContactClickListener2 onContactClickListener2;
        public MyViewHolder(@NonNull View itemView, DistrictCustomAdapter.OnContactClickListener2 onContactClickListener2){
            super(itemView);

            nameTextView=itemView.findViewById(R.id.textViewId);
            this.onContactClickListener2=onContactClickListener2;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContactClickListener2.onContactClick2(getAdapterPosition());

        }
    }

    public  interface  OnContactClickListener2{
        void onContactClick2(int position);
    }

}


