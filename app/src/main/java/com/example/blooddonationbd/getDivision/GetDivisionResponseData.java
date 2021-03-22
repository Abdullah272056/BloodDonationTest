package com.example.blooddonationbd.getDivision;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDivisionResponseData {

    @SerializedName("status")
    @Expose
    private GetDivisionStatusData getDivisionStatusData;
    @SerializedName("data")
    @Expose
    private List<GetDivisionData> divisionDataList = null;

    public GetDivisionStatusData getGetDivisionStatusData() {
        return getDivisionStatusData;
    }

    public void setGetDivisionStatusData(GetDivisionStatusData getDivisionStatusData) {
        this.getDivisionStatusData = getDivisionStatusData;
    }

    public List<GetDivisionData> getDivisionDataList() {
        return divisionDataList;
    }

    public void setDivisionDataList(List<GetDivisionData> divisionDataList) {
        this.divisionDataList = divisionDataList;
    }
}
