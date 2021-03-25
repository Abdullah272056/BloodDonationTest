package com.example.blooddonationbd;

public class AdminModelClass {
    String email,phone,id;

    public AdminModelClass() {
    }

    public AdminModelClass(String email, String phone, String id) {
        this.email = email;
        this.phone = phone;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
