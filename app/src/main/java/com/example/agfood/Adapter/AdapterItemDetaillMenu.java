package com.example.agfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agfood.Model.ModelFood;
import com.example.agfood.R;

import java.util.List;

public class AdapterItemDetaillMenu extends RecyclerView.Adapter<AdapterItemDetaillMenu.ViewHolder> {
    List<ModelFood> listFood;
    AdapterItemDetaillMenu.AdapterItemDetailMenuListener adapterItemDetailMenuListener;


    public interface AdapterItemDetailMenuListener{
        void clickItemMenuListener();
    }

    public AdapterItemDetaillMenu(List<ModelFood> listFood, AdapterItemDetailMenuListener adapterItemDetailMenuListener) {
        this.listFood = listFood;
        this.adapterItemDetailMenuListener = adapterItemDetailMenuListener;
    }

    @Override
    public AdapterItemDetaillMenu.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_menu_detail, parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemDetaillMenu.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewMenuMakanan;
        TextView textViewNamaMenuMakanan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewMenuMakanan = itemView.findViewById(R.id.id_image_menu_detail);
            textViewNamaMenuMakanan = itemView.findViewById(R.id.id_tv_detail_makanan_nama_makanan);
        }
    }
}
