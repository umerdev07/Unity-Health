package com.example.unityhealth.Models;

import java.util.List;

public class PharmacyModel {
    private String name, contact, location, openHours;
    private boolean homeDelivery;
    private List<String> medicines;

    public PharmacyModel() {}

    public PharmacyModel(String name, String contact, String location, String openHours, boolean homeDelivery, List<String> medicines) {
        this.name = name;
        this.contact = contact;
        this.location = location;
        this.openHours = openHours;
        this.homeDelivery = homeDelivery;
        this.medicines = medicines;
    }

    public String getName() { return name; }
    public String getContact() { return contact; }
    public String getLocation() { return location; }
    public String getOpenHours() { return openHours; }
    public boolean isHomeDelivery() { return homeDelivery; }
    public List<String> getMedicines() { return medicines; }
}
