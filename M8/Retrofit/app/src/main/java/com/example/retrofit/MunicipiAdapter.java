package com.example.retrofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MunicipiAdapter extends RecyclerView.Adapter<MunicipiAdapter.CustomViewHolder> {
    private Municipi municipi;

    public MunicipiAdapter(Municipi municipi) {
        this.municipi = municipi;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View myView;
        TextView tvMunicipi;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
            tvMunicipi = myView.findViewById(R.id.user);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.tvMunicipi.setText(municipi.getElements().get(position).getMunicipiNom());
    }

    @Override
    public int getItemCount() {
        return municipi.getElements().size();
    }
}

