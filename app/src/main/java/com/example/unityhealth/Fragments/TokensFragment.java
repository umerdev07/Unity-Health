package com.example.unityhealth.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.unityhealth.Adapter.TokenAdapter;

import com.example.unityhealth.Models.TokenModel;
import com.example.unityhealth.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class TokensFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<TokenModel> tokenList;
    private TokenAdapter adapter;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    public TokensFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tokens, container, false);

        recyclerView = view.findViewById(R.id.token_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tokenList = new ArrayList<>();
        adapter = new TokenAdapter(tokenList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        loadUserTokens();

        return view;
    }

    private void loadUserTokens() {
        String currentUserId = auth.getCurrentUser().getUid();

        db.collection("Tokens")
                .whereEqualTo("patientId", currentUserId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    tokenList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        TokenModel token = doc.toObject(TokenModel.class);
                        tokenList.add(token);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Error loading tokens: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
