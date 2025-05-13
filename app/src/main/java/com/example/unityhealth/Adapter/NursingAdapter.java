package com.example.unityhealth.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unityhealth.Models.NursingModel;
import com.example.unityhealth.R;

import java.util.List;

public class NursingAdapter extends RecyclerView.Adapter<NursingAdapter.ViewHolder> {

    private final List<NursingModel> list;

    public NursingAdapter(List<NursingModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NursingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nursing_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NursingAdapter.ViewHolder holder, int position) {
        NursingModel model = list.get(position);
        holder.serviceName.setText(model.getServiceName());
        holder.nurseName.setText("Nurse: " + model.getNurseName());
        holder.contact.setText("Contact: " + model.getContact());
        holder.timing.setText("Timing: " + model.getTiming());
        holder.location.setText("Location: " + model.getLocation());
        holder.status.setText(model.isAvailable() ? "Available" : "Unavailable");
        holder.status.setTextColor(model.isAvailable() ? Color.GREEN : Color.RED);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView serviceName, nurseName, contact, timing, location, status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.nursing_service_name);
            nurseName = itemView.findViewById(R.id.nursing_nurse_name);
            contact = itemView.findViewById(R.id.nursing_contact);
            timing = itemView.findViewById(R.id.nursing_timing);
            location = itemView.findViewById(R.id.nursing_location);
            status = itemView.findViewById(R.id.nursing_status);
        }
    }
}

