package com.example.agfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agfood.Model.ModelKeranjang;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterCheckoutItem extends RecyclerView.Adapter<AdapterCheckoutItem.ViewHolder> {
    List<ModelKeranjang> listKeranjang;

    public AdapterCheckoutItem(List<ModelKeranjang> listKeranjang){
        this.listKeranjang = listKeranjang;
    }
    @Override
    public AdapterCheckoutItem.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkout_adapter,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCheckoutItem.ViewHolder holder, int position) {
        holder.headerProduct.setText(listKeranjang.get(position).getSelectedFood().getNama_barang());
        holder.textViewHargaCheckout.setText(Util.convertToRupiah(listKeranjang.get(position).getSelectedFood().getHarga()));
        Picasso.get()
                .load(listKeranjang.get(position).getSelectedFood().getGambar_barang()).resize(512,512).centerCrop()
                .into(holder.imageViewCheckout);
        holder.textViewTotalJumlah.setText(String.valueOf(listKeranjang.get(position).getSelectedFood().getTotalItemKeranjang()));
    }

    @Override
    public int getItemCount() {
        return listKeranjang == null ? 0 : listKeranjang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewCheckout;
        TextView textViewHargaCheckout, headerProduct , textViewTotalJumlah;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCheckout = itemView.findViewById(R.id.id_img_item_keranjang_adapter);
            textViewHargaCheckout = itemView.findViewById(R.id.id_tv_harga_item_checkout_adapter);
            headerProduct = itemView.findViewById(R.id.id_tv_tittle_item_checkout_adapter);
            textViewTotalJumlah = itemView.findViewById(R.id.id_tv_jumlah_item_checkout_adapter);
        }
    }
}
