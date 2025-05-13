package com.example.unityhealth.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.unityhealth.Adapter.PharmacyAdapter;
import com.example.unityhealth.Models.PharmacyModel;
import com.example.unityhealth.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class PharmacyFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<PharmacyModel> pharmacyList;
    private PharmacyAdapter adapter;
    private FirebaseFirestore db;

    public PharmacyFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pharmacy, container, false);

        recyclerView = view.findViewById(R.id.pharmacy_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pharmacyList = new ArrayList<>();
        adapter = new PharmacyAdapter(pharmacyList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        loadPharmacies();

        return view;
    }

    private void loadPharmacies() {
        db.collection("Pharmacies")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    pharmacyList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        PharmacyModel model = doc.toObject(PharmacyModel.class);
                        pharmacyList.add(model);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Error loading pharmacies: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
