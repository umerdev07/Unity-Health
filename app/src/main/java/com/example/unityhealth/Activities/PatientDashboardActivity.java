package com.example.unityhealth.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.unityhealth.Fragments.ClinicsFragment;
import com.example.unityhealth.Fragments.LabFragment;
import com.example.unityhealth.Fragments.NursingFragment;
import com.example.unityhealth.Fragments.PharmacyFragment;
import com.example.unityhealth.Fragments.TokensFragment;
import com.example.unityhealth.R;

public class PatientDashboardActivity extends AppCompatActivity {
    TextView navLab, navTokens, navClinics, navNursing, navPharmacy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);
        navLab = findViewById(R.id.nav_lab);
        navTokens = findViewById(R.id.nav_tokens);
        navClinics = findViewById(R.id.nav_clinics);
        navNursing = findViewById(R.id.nav_nursing);
        navPharmacy = findViewById(R.id.nav_pharmacy);

        loadFragment(new LabFragment()); // default on load

        navLab.setOnClickListener(v -> loadFragment(new LabFragment()));
        navTokens.setOnClickListener(v -> loadFragment(new TokensFragment()));

        navClinics.setOnClickListener(v -> loadFragment(new ClinicsFragment()));
        navNursing.setOnClickListener(v -> loadFragment(new NursingFragment()));
        navPharmacy.setOnClickListener(v -> loadFragment(new PharmacyFragment()));
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}