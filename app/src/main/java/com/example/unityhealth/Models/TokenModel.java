package com.example.unityhealth.Models;

public class TokenModel {
    private String doctorName, date, time, status;
    private int tokenNumber;

    public TokenModel() {}

    public TokenModel(String doctorName, String date, String time, int tokenNumber, String status) {
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
        this.tokenNumber = tokenNumber;
        this.status = status;
    }

    public String getDoctorName() { return doctorName; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public int getTokenNumber() { return tokenNumber; }
    public String getStatus() { return status; }
}
