package com.example.agfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
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
    Context context;
    public AdapterCartOrder(List<ModelOrderan> listDataBarang, AdapterCardOrderListener mAdapterListener,
                            Context context) {
        this.listDataBarang = listDataBarang;
        this.mAdapterListener = mAdapterListener;
        this.context = context;
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
        holder.namaPesanan.setText(String.valueOf(listDataBarang.get(position).getIdkeranjang()));

        switch (listDataBarang.get(position).getListBarangYgDiOrder().get(0).getStatus_bayar()){
            case "belum_bayar":
                holder.textViewStatusOrder.setText("Transaksi Belum Terkonfirmasi");
                holder.cardViewStatusOrder.setCardBackgroundColor(ContextCompat.getColor(context,R.color.bg_card_tidak_tersedia));
                holder.textViewStatusOrder.setTextColor(ContextCompat.getColor(context,R.color.text_color_tidak_tersedia));
                break;
            case "sudah_bayar":
                holder.textViewStatusOrder.setText("Transaksi Terkonfirmasi");
                holder.cardViewStatusOrder.setCardBackgroundColor(ContextCompat.getColor(context,R.color.bg_card_tersedia));
                holder.textViewStatusOrder.setTextColor(ContextCompat.getColor(context,R.color.text_color_tersedia));
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listDataBarang == null ? 0 : listDataBarang.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageCart;
        TextView hargaPesanan , jumlahPesanan, namaPesanan;
        CardView cardViewStatusOrder;
        TextView textViewStatusOrder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCart = itemView.findViewById(R.id.id_card_wraped_layout_keranjang_adapter);
            hargaPesanan = itemView.findViewById(R .id.id_tv_jumlah_item_checkout_adapter);
            jumlahPesanan = itemView.findViewById(R.id.id_tv_jumlah_item_keranjang);
            namaPesanan = itemView.findViewById(R.id.id_tv_tittle_item_checkout_adapter);
            cardViewStatusOrder = itemView.findViewById(R.id.id_card_container_status_orderan);
            textViewStatusOrder = itemView.findViewById(R.id.id_status_orderan);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mAdapterListener.clickOrderListener(getAdapterPosition());
        }
    }
}
