package com.example.blooddonationbd.getDistrict;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDistrictData {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("upazilla")
    @Expose
    private List<String> upazilla = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<String> getUpazilla() {
        return upazilla;
    }

    public void setUpazilla(List<String> upazilla) {
        this.upazilla = upazilla;
    }
}
