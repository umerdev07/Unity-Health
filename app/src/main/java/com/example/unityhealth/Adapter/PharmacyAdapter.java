package com.example.unityhealth.Adapter;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unityhealth.Models.PharmacyModel;
import com.example.unityhealth.R;

import java.util.List;

public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.ViewHolder> {

    private final List<PharmacyModel> pharmacyList;

    public PharmacyAdapter(List<PharmacyModel> list) {
        this.pharmacyList = list;
    }

    @NonNull
    @Override
    public PharmacyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pharmacy_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyAdapter.ViewHolder holder, int position) {
        PharmacyModel pharmacy = pharmacyList.get(position);
        holder.name.setText(pharmacy.getName());
        holder.contact.setText("Contact: " + pharmacy.getContact());
        holder.location.setText("Location: " + pharmacy.getLocation());
        holder.hours.setText("Open: " + pharmacy.getOpenHours());
        holder.delivery.setText(pharmacy.isHomeDelivery() ? "Home Delivery: Available" : "Home Delivery: Not Available");
        holder.delivery.setTextColor(pharmacy.isHomeDelivery() ? Color.GREEN : Color.RED);
        holder.medicines.setText("Medicines: " + TextUtils.join(", ", pharmacy.getMedicines()));
    }

    @Override
    public int getItemCount() {
        return pharmacyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, contact, location, hours, delivery, medicines;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.pharmacy_name);
            contact = itemView.findViewById(R.id.pharmacy_contact);
            location = itemView.findViewById(R.id.pharmacy_location);
            hours = itemView.findViewById(R.id.pharmacy_hours);
            delivery = itemView.findViewById(R.id.pharmacy_delivery);
            medicines = itemView.findViewById(R.id.pharmacy_medicines);
        }
    }
}

