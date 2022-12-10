package com.example.agfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agfood.Model.ModelKeranjang;
import com.example.agfood.R;
import com.example.agfood.Util.Util;

import java.util.List;

public class AdapterRinciaTransaksi extends RecyclerView.Adapter<AdapterRinciaTransaksi.ViewHolder> {
    List<ModelKeranjang> listModelKeranjang;

    public AdapterRinciaTransaksi(List<ModelKeranjang> listModelKeranjang) {
        this.listModelKeranjang = listModelKeranjang;
    }

    @Override
    public AdapterRinciaTransaksi.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rincian_transaksi,parent,false);
    return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRinciaTransaksi.ViewHolder holder, int position) {
        holder.id_tv_jmlh_item.setText(String.valueOf(listModelKeranjang.get(position).getSelectedFood().getTotalItemKeranjang() + "x"));
        holder.id_tv_hrga_item.setText(Util.convertToRupiah(listModelKeranjang.get(position).getSelectedFood().getHarga()));
        holder.id_tv_nama_item.setText(listModelKeranjang.get(position).getSelectedFood().getNama_barang());

    }


    @Override
    public int getItemCount() {
        return listModelKeranjang == null ? 0 : listModelKeranjang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id_tv_jmlh_item,id_tv_nama_item , id_tv_hrga_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_tv_jmlh_item = itemView.findViewById(R.id.id_adapter_rincian_pesanan_jumlah_item);
            id_tv_nama_item = itemView.findViewById(R.id.id_adapter_rincian_nama_item);
            id_tv_hrga_item = itemView.findViewById(R.id.id_adapter_rincian_harga_item);
        }
    }
}
