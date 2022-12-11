package com.example.agfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agfood.Model.ModelBarang;
import com.example.agfood.Model.ModelOrderan;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCartOrder extends RecyclerView.Adapter<AdapterCartOrder.ViewHolder> {
    List<ModelOrderan> listDataBarang;
    AdapterCardOrderListener mAdapterListener;

    public AdapterCartOrder(List<ModelOrderan> listDataBarang, AdapterCardOrderListener mAdapterListener) {
        this.listDataBarang = listDataBarang;
        this.mAdapterListener = mAdapterListener;
    }


    public interface AdapterCardOrderListener{
        void clickOrderListener(int posisiItemYangDiKlik);

    }
    @Override
    public AdapterCartOrder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_status_layout_adapter,parent,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterCartOrder.ViewHolder holder, int position) {
        Picasso.get()
                .load(listDataBarang.get(position).getListBarangYgDiOrder().get(0).getGambar_barang()).resize(512,512).centerCrop()
                .into(holder.imageCart);
        holder.hargaPesanan.setText(
                String.valueOf(
                        Util.convertToRupiah(
                                listDataBarang.get(position).getTotalHargaOrderan()))
        );
        holder.namaPesanan.setText("KRJ" + String.valueOf(listDataBarang.get(position).getIdkeranjang()));
    }

    @Override
    public int getItemCount() {
        return listDataBarang == null ? 0 : listDataBarang.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCart;
        TextView hargaPesanan , jumlahPesanan, namaPesanan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCart = itemView.findViewById(R.id.id_card_wraped_layout_keranjang_adapter);
            hargaPesanan = itemView.findViewById(R .id.id_tv_jumlah_item_checkout_adapter);
            jumlahPesanan = itemView.findViewById(R.id.id_tv_jumlah_item_keranjang);
            namaPesanan = itemView.findViewById(R.id.id_tv_tittle_item_checkout_adapter);
        }
    }
}
