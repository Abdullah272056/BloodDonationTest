package com.example.blooddonationbd.getDivision;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDivisionData {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("divisionbn")
    @Expose
    private String divisionbn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDivisionbn() {
        return divisionbn;
    }

    public void setDivisionbn(String divisionbn) {
        this.divisionbn = divisionbn;
    }
}
