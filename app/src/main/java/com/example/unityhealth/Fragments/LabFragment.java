package com.example.unityhealth.Fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.unityhealth.Adapter.LabTestAdapter;
import com.example.unityhealth.Models.LabTestModel;
import com.example.unityhealth.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

public class LabFragment extends Fragment {

    private RecyclerView recyclerView;
    private LabTestAdapter adapter;
    private ArrayList<LabTestModel> labTestList;
    private FirebaseFirestore db;

    public LabFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lab, container, false);

        recyclerView = view.findViewById(R.id.lab_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        labTestList = new ArrayList<>();
        adapter = new LabTestAdapter(labTestList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        loadLabTests();

        return view;
    }

    private void loadLabTests() {
        db.collection("LabTests")
                .orderBy("timestamp")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        labTestList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String name = document.getString("testName");
                            String description = document.getString("testDescription");
                            labTestList.add(new LabTestModel(name, description));
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "Failed to load lab tests.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
