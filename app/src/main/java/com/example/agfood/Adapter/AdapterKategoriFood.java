package com.example.agfood.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.example.agfood.Model.ModelKategory;
import com.example.agfood.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class AdapterKategoriFood extends RecyclerView.Adapter{

    List<ModelKategory> listKategoryButton;
    AdapterKategoriFood.AdapterKategoriFoodListener adapterKategoriFoodListener;
    Context context;
    public AdapterKategoriFood(List<ModelKategory> listKategoryButton, AdapterKategoriFoodListener adapterKategoriFoodListener,Context ctx) {
        this.listKategoryButton = listKategoryButton;
        this.adapterKategoriFoodListener = adapterKategoriFoodListener;
        this.context = ctx;
    }


    public interface AdapterKategoriFoodListener{
        void clickKategoriItemListener(int positionKategory);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new AdapterKategoriViewHolder(layoutInflater.inflate(R.layout.item_kategory_layout_adapter, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AdapterKategoriViewHolder adapterKategoriViewHolder = (AdapterKategoriViewHolder)  holder;
        adapterKategoriViewHolder.materialButton.setText(listKategoryButton.get(position).getNamaKategory());
        if(listKategoryButton.get(
                position
        ).isStatusClickKategory()){
            adapterKategoriViewHolder.materialButton.setBackgroundColor(Color.parseColor("#d06871"));
        } else{
            adapterKategoriViewHolder.materialButton.setBackgroundColor(Color.parseColor("#ebf1ed"));
        }
    }

    @Override
    public int getItemCount() {
        return listKategoryButton.size();
    }
    public class AdapterKategoriViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        MaterialButton materialButton;
        public AdapterKategoriViewHolder(@NonNull View itemView) {
            super(itemView);
            materialButton = itemView.findViewById(R.id.id_button_kategori);
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            adapterKategoriFoodListener.clickKategoriItemListener(
                    getAdapterPosition()
            );
        }
    }
}
