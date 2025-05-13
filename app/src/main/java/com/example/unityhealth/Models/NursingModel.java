package com.example.unityhealth.Models;

public class NursingModel {
    private String serviceName, nurseName, contact, timing, location;
    private boolean available;

    public NursingModel() {}

    public NursingModel(String serviceName, String nurseName, String contact, String timing, String location, boolean available) {
        this.serviceName = serviceName;
        this.nurseName = nurseName;
        this.contact = contact;
        this.timing = timing;
        this.location = location;
        this.available = available;
    }

    public String getServiceName() { return serviceName; }
    public String getNurseName() { return nurseName; }
    public String getContact() { return contact; }
    public String getTiming() { return timing; }
    public String getLocation() { return location; }
    public boolean isAvailable() { return available; }
}
