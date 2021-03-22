package com.example.blooddonationbd.getDistrict;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDistrictResponseData{
    @SerializedName("status")
    @Expose
    private GetDistrictStatusData getDistrictStatusData;
    @SerializedName("data")
    @Expose
    private List<GetDistrictData> getDistrictData = null;

    public GetDistrictStatusData getGetDistrictStatusData() {
        return getDistrictStatusData;
    }

    public void setGetDistrictStatusData(GetDistrictStatusData getDistrictStatusData) {
        this.getDistrictStatusData = getDistrictStatusData;
    }

    public List<GetDistrictData> getGetDistrictData() {
        return getDistrictData;
    }

    public void setGetDistrictData(List<GetDistrictData> getDistrictData) {
        this.getDistrictData = getDistrictData;
    }
}
