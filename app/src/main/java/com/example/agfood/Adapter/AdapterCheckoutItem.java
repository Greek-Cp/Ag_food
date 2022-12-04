package com.example.agfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agfood.Model.ModelKeranjang;
import com.example.agfood.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterCheckoutItem extends RecyclerView.Adapter<AdapterCheckoutItem.ViewHolder> {
    List<ModelKeranjang> listKeranjang;

    @Override
    public AdapterCheckoutItem.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkout_adapter,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCheckoutItem.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
