package com.example.blooddonationbd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> implements Filterable {

    Context context;
    private List<UserInformation> userInformationList;

    // for search
    List<UserInformation> copyUserInformationList;

    public CustomAdapter(Context context, List<UserInformation> userInformationList) {
        this.context = context;
        this.userInformationList = userInformationList;
        //for searchView//dataList's copy
        copyUserInformationList = new ArrayList<>(copyUserInformationList);

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

        holder.nameTextView.setText("Name : "+userInformationList.get(position).getUserName());
        holder.bloodGroupTextView.setText("Blood group : "+userInformationList.get(position).getBloodGroup());
        holder.locationTextView.setText("Location : "+userInformationList.get(position).getThanaName() +", "+
                userInformationList.get(position).getDistrictName()+", "+userInformationList.get(position).getCountryName());
        holder.lastDateTextView.setText("Last donate : "+userInformationList.get(position).getLastDate());
    }

    @Override
    public int getItemCount() {
        return userInformationList.size();
    }


    //call getFilter method
    @Override
    public Filter getFilter() {
        return null;
    }

    Filter filterData=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<UserInformation> filterList=new ArrayList<>();
            //for filter data keeping
            if (charSequence==null||charSequence.length()==0){
                filterList.addAll(copyUserInformationList);
            }
            else{
                String value=charSequence.toString().toLowerCase().trim();
                for (UserInformation userInformation:copyUserInformationList){
                    if (userInformation.getBloodGroup().toLowerCase().trim().contains(value)||userInformation.getDistrictName().toLowerCase().trim().contains(value)){
                        filterList.add(userInformation);
                    }

                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            userInformationList.clear();
            userInformationList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };





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
