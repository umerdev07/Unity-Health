package com.example.unityhealth.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.unityhealth.Adapter.NursingAdapter;
import com.example.unityhealth.Models.NursingModel;
import com.example.unityhealth.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class NursingFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<NursingModel> nursingList;
    private NursingAdapter adapter;
    private FirebaseFirestore db;

    public NursingFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nursing, container, false);

        recyclerView = view.findViewById(R.id.nursing_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        nursingList = new ArrayList<>();
        adapter = new NursingAdapter(nursingList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        loadNursingData();

        return view;
    }

    private void loadNursingData() {
        db.collection("HomeNursing")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    nursingList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        NursingModel model = doc.toObject(NursingModel.class);
                        nursingList.add(model);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Error loading nursing data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
