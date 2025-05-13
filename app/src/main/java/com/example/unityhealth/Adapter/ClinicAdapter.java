package com.example.unityhealth.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unityhealth.Models.ClinicModel;
import com.example.unityhealth.R;

import java.util.List;

public class ClinicAdapter extends RecyclerView.Adapter<ClinicAdapter.ViewHolder> {

    private final List<ClinicModel> clinics;

    public ClinicAdapter(List<ClinicModel> clinics) {
        this.clinics = clinics;
    }

    @NonNull
    @Override
    public ClinicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_clinic_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClinicAdapter.ViewHolder holder, int position) {
        ClinicModel clinic = clinics.get(position);
        holder.name.setText(clinic.getName());
        holder.address.setText("Address: " + clinic.getAddress());
        holder.phone.setText("Phone: " + clinic.getPhone());
        holder.hours.setText("Hours: " + clinic.getOpenHours());
        holder.status.setText(clinic.isDoctorAvailable() ? "Doctor Available" : "Doctor Unavailable");
        holder.status.setTextColor(clinic.isDoctorAvailable() ? Color.GREEN : Color.RED);
    }

    @Override
    public int getItemCount() {
        return clinics.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, address, phone, hours, status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.clinic_name);
            address = itemView.findViewById(R.id.clinic_address);
            phone = itemView.findViewById(R.id.clinic_phone);
            hours = itemView.findViewById(R.id.clinic_hours);
            status = itemView.findViewById(R.id.clinic_status);
        }
    }
}
