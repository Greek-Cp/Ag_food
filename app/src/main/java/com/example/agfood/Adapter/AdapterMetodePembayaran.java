package com.example.agfood.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agfood.ModelAdapter.ModelAdapterPembayaran;
import com.example.agfood.R;

import java.util.List;

public class AdapterMetodePembayaran extends RecyclerView.Adapter<AdapterMetodePembayaran.ViewHolder> {

    List<ModelAdapterPembayaran> listPembayaran;
    AdapterMetodePembayaran.AdapterMetodePembayaranListener adapterMetodePembayaranListener;
    public interface AdapterMetodePembayaranListener{
        void selectionItemPayment(int positionOfPayment);
    }
    public AdapterMetodePembayaran(List<ModelAdapterPembayaran> listPembayaran,
    AdapterMetodePembayaran.AdapterMetodePembayaranListener adapterMetodePembayaranListen) {
        this.listPembayaran = listPembayaran;
        this.adapterMetodePembayaranListener = adapterMetodePembayaranListen;
    }
    @Override
    public AdapterMetodePembayaran.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_metode_pembayaran_adapter,parent,false);
        return new AdapterMetodePembayaran.ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterMetodePembayaran.ViewHolder holder, int position){
        if(listPembayaran.get(position).isMetodePesananSelected() == true){
            holder.viewMark.setBackgroundColor(Color.parseColor("#FF0A36"));
        } else{
            holder.viewMark.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        holder.tvNamePayment.setText(listPembayaran.get(position).getNamaServicePayment());
        holder.imgPayment.setImageResource(listPembayaran.get(position).getImgServicePayment());
    }
    @Override
    public int getItemCount() {
        return listPembayaran.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvNamePayment;
        ImageView imgPayment;
        View viewMark;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamePayment = itemView.findViewById(R.id.list_item_payment_method_name);
            imgPayment = itemView.findViewById(R.id.list_item_ic_payment_method_imageview);
            viewMark = itemView.findViewById(R.id.list_item_card_view_left_card);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            adapterMetodePembayaranListener.selectionItemPayment(getAdapterPosition());
        }
    }
}
