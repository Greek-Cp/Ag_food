package com.example.agfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agfood.Model.ModelTopping;
import com.example.agfood.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterTableDetailTopping extends RecyclerView.Adapter<AdapterTableDetailTopping.ViewHolder> {

    List<ModelTopping> listToppingSelected = new ArrayList<>();

    public AdapterTableDetailTopping(List<ModelTopping> listToppingSelected) {
        this.listToppingSelected = listToppingSelected;
    }

    @Override
    public AdapterTableDetailTopping.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  layoutInflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_table_detail_topping,parent,false);
        return new ViewHolder(layoutInflater);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTableDetailTopping.ViewHolder holder, int position) {
        if(position == 0){
            holder.lineBottom.setVisibility(View.VISIBLE);
            holder.lineTop.setVisibility(View.VISIBLE);
        } else{
            holder.lineBottom.setVisibility(View.VISIBLE);
            holder.lineTop.setVisibility(View.VISIBLE);
            holder.namaTopping.setText(listToppingSelected.get(position).getNamaTopping());
            holder.hargaTopping.setText(String.valueOf(listToppingSelected.get(position).getHargaTopping()));

        }
    }

    @Override
    public int getItemCount() {

    return listToppingSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView namaTopping , hargaTopping , totalTopping;
        View lineTop , lineBottom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaTopping = itemView.findViewById(R.id.id_adapter_tv_nama_topping);
            hargaTopping = itemView.findViewById(R.id.id_tv_harga_topping);
            totalTopping = itemView.findViewById(R.id.id_tv_total_harga_topping);
            lineTop = itemView.findViewById(R.id.id_adapter_table_line_top);
            lineBottom = itemView.findViewById(R.id.id_adapter_table_line_bottom);
        }
    }
}
