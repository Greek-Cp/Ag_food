package com.example.agfood.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agfood.Model.ModelAlamat;
import com.example.agfood.R;

import java.util.List;

public class AdapterAlamatSaya extends RecyclerView.Adapter<AdapterAlamatSaya.ViewHolder> {
    public interface listenerAdapterAlamatSaya{
        void selectMetodePesanan(int positionOfPesanan);
        void hapusAlamat(int posisiAlamat);
    }
    AdapterAlamatSaya.listenerAdapterAlamatSaya listenerAdapterAlamatSaya;
    List<ModelAlamat> listAlamatUser;

    public AdapterAlamatSaya(AdapterAlamatSaya.listenerAdapterAlamatSaya listenerAdapterAlamatSaya, List<ModelAlamat> listMetodePesanan) {
        this.listenerAdapterAlamatSaya = listenerAdapterAlamatSaya;
        this.listAlamatUser = listMetodePesanan;
    }

    @Override
    public AdapterAlamatSaya.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alamat_saya_adapter,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAlamatSaya.ViewHolder holder, int position) {
        if(listAlamatUser.get(position).isAlamatSelected() == true){
            holder.cardMark.setBackgroundColor(Color.parseColor("#FF0A36"));
        } else{
            holder.cardMark.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        System.out.println(listAlamatUser.get(position).getAlamat());
        holder.textViewAlamatUser.setText(listAlamatUser.get(position).getAlamat());
        holder.hapusAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listenerAdapterAlamatSaya.hapusAlamat(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listAlamatUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View cardMark;
        TextView textViewAlamatUser;
        CardView hapusAlamat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardMark = itemView.findViewById(R.id.list_item_card_view_left_card);
            textViewAlamatUser = itemView.findViewById(R.id.list_item_metode_pesanan_text_view);
            itemView.setOnClickListener(this::onClick);
            hapusAlamat = itemView.findViewById(R.id.id_btn_hapus_keranjang);
        }

        @Override
        public void onClick(View view) {
            listenerAdapterAlamatSaya.selectMetodePesanan(getAdapterPosition());
        }
    }
}
