package com.example.unityhealth.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unityhealth.Adapter.ClinicAdapter;
import com.example.unityhealth.Models.ClinicModel;
import com.example.unityhealth.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class ClinicsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<ClinicModel> clinicList;
    private ClinicAdapter adapter;
    private FirebaseFirestore db;

    public ClinicsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clinics, container, false);

        recyclerView = view.findViewById(R.id.clinic_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        clinicList = new ArrayList<>();
        adapter = new ClinicAdapter(clinicList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        loadClinics();

        return view;
    }

    private void loadClinics() {
        db.collection("Clinics")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    clinicList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        ClinicModel clinic = doc.toObject(ClinicModel.class);
                        clinicList.add(clinic);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Failed to load clinics: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
