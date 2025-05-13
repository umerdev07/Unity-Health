package com.example.unityhealth.Models;

public class ClinicModel {
    private String name, address, phone, openHours;
    private boolean doctorAvailable;

    public ClinicModel() {}

    public ClinicModel(String name, String address, String phone, String openHours, boolean doctorAvailable) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.openHours = openHours;
        this.doctorAvailable = doctorAvailable;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getOpenHours() { return openHours; }
    public boolean isDoctorAvailable() { return doctorAvailable; }
}
