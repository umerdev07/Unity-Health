package com.example.unityhealth.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unityhealth.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AddPharmacyFragment extends Fragment {

    private EditText nameEt, contactEt, locationEt, hoursEt, medicinesEt;
    private CheckBox deliveryCb;
    private Button submitBtn;
    private FirebaseFirestore db;

    public AddPharmacyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_pharmacy, container, false);

        // Initialize UI
        nameEt = view.findViewById(R.id.et_pharmacy_name);
        contactEt = view.findViewById(R.id.et_pharmacy_contact);
        locationEt = view.findViewById(R.id.et_pharmacy_location);
        hoursEt = view.findViewById(R.id.et_pharmacy_hours);
        medicinesEt = view.findViewById(R.id.et_pharmacy_medicines);
        deliveryCb = view.findViewById(R.id.cb_home_delivery);
        submitBtn = view.findViewById(R.id.btn_submit);

        // Firestore instance
        db = FirebaseFirestore.getInstance();

        // Button listener
        submitBtn.setOnClickListener(v -> submitPharmacy());

        return view;
    }

    private void submitPharmacy() {
        String name = nameEt.getText().toString().trim();
        String contact = contactEt.getText().toString().trim();
        String location = locationEt.getText().toString().trim();
        String hours = hoursEt.getText().toString().trim();
        String medicineList = medicinesEt.getText().toString().trim();
        boolean delivery = deliveryCb.isChecked();

        if (name.isEmpty() || contact.isEmpty() || location.isEmpty() || hours.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> pharmacy = new HashMap<>();
        pharmacy.put("name", name);
        pharmacy.put("contact", contact);
        pharmacy.put("location", location);
        pharmacy.put("openHours", hours);
        pharmacy.put("homeDelivery", delivery);
        pharmacy.put("medicines", Arrays.asList(medicineList.split(",")));

        db.collection("Pharmacies")
                .add(pharmacy)
                .addOnSuccessListener(docRef -> {
                    Toast.makeText(getContext(), "Pharmacy Added Successfully", Toast.LENGTH_SHORT).show();
                    nameEt.setText("");
                    contactEt.setText("");
                    locationEt.setText("");
                    hoursEt.setText("");
                    medicinesEt.setText("");
                    deliveryCb.setChecked(false);
                })
                .addOnFailureListener(e ->
                        Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show());
    }
}
