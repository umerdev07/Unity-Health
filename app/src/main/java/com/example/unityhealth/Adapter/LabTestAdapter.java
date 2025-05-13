package com.example.unityhealth.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unityhealth.Models.LabTestModel;
import com.example.unityhealth.R;

import java.util.ArrayList;

public class LabTestAdapter extends RecyclerView.Adapter<LabTestAdapter.ViewHolder> {

    private final ArrayList<LabTestModel> labTests;

    public LabTestAdapter(ArrayList<LabTestModel> labTests) {
        this.labTests = labTests;
    }

    @NonNull
    @Override
    public LabTestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lab_test, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LabTestAdapter.ViewHolder holder, int position) {
        LabTestModel test = labTests.get(position);
        holder.testName.setText(test.getTestName());
        holder.testDescription.setText(test.getTestDescription());
    }

    @Override
    public int getItemCount() {
        return labTests.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView testName, testDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            testName = itemView.findViewById(R.id.lab_test_name);
            testDescription = itemView.findViewById(R.id.lab_test_description);
        }
    }
}
