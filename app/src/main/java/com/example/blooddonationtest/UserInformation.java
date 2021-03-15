package com.example.blooddonationtest;

public class UserInformation {
    String id, userName, userPhone,BloodGroup,
            lastDate, countryName,districtName,thanaName;

    public UserInformation() {
    }

    public UserInformation(String id, String userName, String userPhone, String bloodGroup, String lastDate, String countryName, String districtName, String thanaName) {
        this.id = id;
        this.userName = userName;
        this.userPhone = userPhone;
        BloodGroup = bloodGroup;
        this.lastDate = lastDate;
        this.countryName = countryName;
        this.districtName = districtName;
        this.thanaName = thanaName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getThanaName() {
        return thanaName;
    }

    public void setThanaName(String thanaName) {
        this.thanaName = thanaName;
    }
}