package com.example.unityhealth.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.unityhealth.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddTokenFragment extends Fragment {

    private AutoCompleteTextView actvPatientName;
    private EditText etTokenNumber, etDate, etTime;
    private Button btnSubmit;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private List<String> patientNames = new ArrayList<>();
    private Map<String, String> patientNameToIdMap = new HashMap<>();

    public AddTokenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_token, container, false);

        actvPatientName = view.findViewById(R.id.actv_patient_name);
        etTokenNumber = view.findViewById(R.id.et_token_number);
        etDate = view.findViewById(R.id.et_date);
        etTime = view.findViewById(R.id.et_time);
        btnSubmit = view.findViewById(R.id.btn_submit_token);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        loadPatients();

        btnSubmit.setOnClickListener(v -> saveToken());

        return view;
    }

    private void loadPatients() {
        db.collection("Users")
                .whereEqualTo("role", "patient")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    patientNames.clear();
                    patientNameToIdMap.clear();
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        String name = doc.getString("username");
                        String id = doc.getId();
                        if (name != null) {
                            patientNames.add(name);
                            patientNameToIdMap.put(name, id);
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                            android.R.layout.simple_dropdown_item_1line, patientNames);
                    actvPatientName.setAdapter(adapter);
                    actvPatientName.setThreshold(1);
                })
                .addOnFailureListener(e ->
                        Toast.makeText(getContext(), "Failed to load patients: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }

    private void saveToken() {
        String patientName = actvPatientName.getText().toString().trim();
        String tokenNumber = etTokenNumber.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String time = etTime.getText().toString().trim();

        if (patientName.isEmpty() || tokenNumber.isEmpty() || date.isEmpty() || time.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String patientId = patientNameToIdMap.get(patientName);
        if (patientId == null) {
            Toast.makeText(getContext(), "Invalid patient name", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> token = new HashMap<>();
        token.put("patientName", patientName);
        token.put("patientId", patientId);
        token.put("tokenNumber", tokenNumber);
        token.put("date", date);
        token.put("time", time);
        token.put("doctorId", mAuth.getCurrentUser().getUid());

        db.collection("Tokens")
                .add(token)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getContext(), "Token issued successfully", Toast.LENGTH_SHORT).show();
                    actvPatientName.setText("");
                    etTokenNumber.setText("");
                    etDate.setText("");
                    etTime.setText("");
                })
                .addOnFailureListener(e ->
                        Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }
}
