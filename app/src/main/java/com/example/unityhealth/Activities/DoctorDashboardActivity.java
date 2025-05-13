package com.example.unityhealth.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.unityhealth.Fragments.AddPharmacyFragment;
import com.example.unityhealth.Fragments.AddTokenFragment;
import com.example.unityhealth.R;

public class DoctorDashboardActivity extends AppCompatActivity {

    TextView navPharmacy, navToken, navNursing, navLab, navClinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);

        navPharmacy = findViewById(R.id.nav_pharmacy);
        navToken = findViewById(R.id.nav_token);
        navNursing = findViewById(R.id.nav_nursing);
        navLab = findViewById(R.id.nav_lab);
        navClinic = findViewById(R.id.nav_clinic);

        // Default fragment
        loadFragment(new AddPharmacyFragment());

        navPharmacy.setOnClickListener(v -> loadFragment(new AddPharmacyFragment()));
        navToken.setOnClickListener(v -> loadFragment(new AddTokenFragment()));
//        navNursing.setOnClickListener(v -> loadFragment(new AddNursingFragment()));
//        navLab.setOnClickListener(v -> loadFragment(new AddLabTestFragment()));
//        navClinic.setOnClickListener(v -> loadFragment(new AddClinicFragment()));
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_doctor, fragment)
                .commit();
    }
}
