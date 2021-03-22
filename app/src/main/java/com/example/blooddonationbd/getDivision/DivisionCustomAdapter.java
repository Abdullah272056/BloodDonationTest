package com.example.blooddonationbd.getDivision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.blooddonationbd.R;

import java.util.List;

public class DivisionCustomAdapter extends RecyclerView.Adapter<DivisionCustomAdapter.MyViewHolder> {
    Context context;
    List<GetDivisionData> divisionDataList;

    DivisionCustomAdapter.OnContactClickListener1 onContactClickListener;
    public DivisionCustomAdapter(Context context, List<GetDivisionData> divisionDataList, DivisionCustomAdapter.OnContactClickListener1 onContactClickListener) {
        this.context = context;
        this.divisionDataList = divisionDataList;
        this.onContactClickListener = onContactClickListener;


    }
    @NonNull
    @Override
    public DivisionCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        return new DivisionCustomAdapter.MyViewHolder(view,onContactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DivisionCustomAdapter.MyViewHolder holder, final int position) {
        holder.nameTextView.setText(String.valueOf(divisionDataList.get(position).getDivision()));



    }

    @Override
    public int getItemCount(){
        return divisionDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        DivisionCustomAdapter.OnContactClickListener1 onContactClickListener;
        public MyViewHolder(@NonNull View itemView, DivisionCustomAdapter.OnContactClickListener1 onContactClickListener){
            super(itemView);

            nameTextView=itemView.findViewById(R.id.textViewId);
            this.onContactClickListener=onContactClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContactClickListener.onContactClick1(getAdapterPosition());

        }
    }

    public  interface  OnContactClickListener1{
        void onContactClick1(int position);
    }

}
