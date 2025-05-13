package com.example.unityhealth.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unityhealth.Models.TokenModel;
import com.example.unityhealth.R;

import java.util.List;

public class TokenAdapter extends RecyclerView.Adapter<TokenAdapter.ViewHolder> {
    private final List<TokenModel> tokenList;

    public TokenAdapter(List<TokenModel> tokenList) {
        this.tokenList = tokenList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_token_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TokenModel token = tokenList.get(position);
        holder.doctorName.setText(token.getDoctorName());
        holder.date.setText("Date: " + token.getDate());
        holder.time.setText("Time: " + token.getTime());
        holder.tokenNumber.setText("Token No: " + token.getTokenNumber());
        holder.status.setText("Status: " + token.getStatus());
    }

    @Override
    public int getItemCount() {
        return tokenList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView doctorName, date, time, tokenNumber, status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.token_doctor);
            date = itemView.findViewById(R.id.token_date);
            time = itemView.findViewById(R.id.token_time);
            tokenNumber = itemView.findViewById(R.id.token_number);
            status = itemView.findViewById(R.id.token_status);
        }
    }
}
