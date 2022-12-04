package com.example.agfood.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agfood.ModelAdapter.ModelAdapterMetodePesanan;
import com.example.agfood.R;

import java.util.List;

public class AdapterMetodePemesanan extends RecyclerView.Adapter<AdapterMetodePemesanan.ViewHolder> {
    public interface listenerAdapterMetodePesanan{
        void selectMetodePesanan(int positionOfPesanan);
    }
    AdapterMetodePemesanan.listenerAdapterMetodePesanan adapterMetodePesananListener;
    List<ModelAdapterMetodePesanan> listMetodePesanan;

    public AdapterMetodePemesanan(listenerAdapterMetodePesanan adapterMetodePesananListener, List<ModelAdapterMetodePesanan> listMetodePesanan) {
        this.adapterMetodePesananListener = adapterMetodePesananListener;
        this.listMetodePesanan = listMetodePesanan;
    }

    @Override
    public AdapterMetodePemesanan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_metode_pemesanan_adapter,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMetodePemesanan.ViewHolder holder, int position) {
        if(listMetodePesanan.get(position).isMetodePesananSelected() == true){
            holder.cardMark.setBackgroundColor(Color.parseColor("#FF0A36"));
        } else{
            holder.cardMark.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        holder.textViewNamaMetodePesanan.setText(listMetodePesanan.get(position).getMetodePesananan());
    }
    @Override
    public int getItemCount() {
        return listMetodePesanan.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View cardMark;
        TextView textViewNamaMetodePesanan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardMark = itemView.findViewById(R.id.list_item_card_view_left_card);
            textViewNamaMetodePesanan = itemView.findViewById(R.id.list_item_metode_pesanan_text_view);
            itemView.setOnClickListener(this::onClick);
        }
        @Override
        public void onClick(View view) {
            adapterMetodePesananListener.selectMetodePesanan(getAdapterPosition());
        }
    }
}
