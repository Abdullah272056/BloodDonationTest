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

public class ThanaCustomAdapter extends RecyclerView.Adapter<ThanaCustomAdapter.MyViewHolder> {
    Context context;
    List<String> thanaDataList;

    ThanaCustomAdapter.OnContactClickListener3 onContactClickListener3;
    public ThanaCustomAdapter(Context context, List<String > thanaDataList, ThanaCustomAdapter.OnContactClickListener3 onContactClickListener3) {
        this.context = context;
        this.thanaDataList = thanaDataList;
        this.onContactClickListener3 = onContactClickListener3;



    }
    @NonNull
    @Override
    public ThanaCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        return new ThanaCustomAdapter.MyViewHolder(view,onContactClickListener3);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanaCustomAdapter.MyViewHolder holder, final int position) {
        holder.nameTextView.setText(String.valueOf(thanaDataList.get(position)));

    }

    @Override
    public int getItemCount(){
        return thanaDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        ThanaCustomAdapter.OnContactClickListener3 onContactClickListener3;
        public MyViewHolder(@NonNull View itemView, ThanaCustomAdapter.OnContactClickListener3 onContactClickListener3){
            super(itemView);

            nameTextView=itemView.findViewById(R.id.textViewId);
            this.onContactClickListener3=onContactClickListener3;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContactClickListener3.onContactClick3(getAdapterPosition());

        }
    }

    public  interface  OnContactClickListener3{
        void onContactClick3(int position);
    }

}

