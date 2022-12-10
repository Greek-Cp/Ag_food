package com.example.agfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agfood.Model.ModelKeranjang;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolder>{
    List<ModelKeranjang> listModelKeranjang;
    AdapterCart.AdapterCartInterface adapterCartInterface;

    public AdapterCart(List<ModelKeranjang> listModelKeranjang, AdapterCartInterface adapterCartInterface) {
        this.listModelKeranjang = listModelKeranjang;
        this.adapterCartInterface = adapterCartInterface;
        System.out.println(new Gson().toJson(listModelKeranjang) + " ttt");
    }
    public interface AdapterCartInterface{
        void tambahPesanan(int positionPesana);
        void kurangPesanan(int positionPesanan);
        void checkBoxItemSelected(int position , boolean statusCheckbox);
    }
    @Override
    public AdapterCart.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keranjang_layout_adapter,parent,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterCart.ViewHolder holder, int position) {
        holder.kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = listModelKeranjang.get(holder.getAdapterPosition()).getSelectedFood().getTotalItemKeranjang();
                System.out.println("Satuan toping = " + value);
                if(value >=  1){
                    System.out.println("Kurang Berhasil");
                    System.out.println("Value After Delete = " + value);
                    adapterCartInterface.kurangPesanan(holder.getAdapterPosition());
                }
            }
        });
        if(listModelKeranjang.get(position).isStatusCheckBoxChecked() == true){
            holder.selectedItemCheckBox.setChecked(true);
        } else{
            holder.selectedItemCheckBox.setChecked(false    );
        }
        holder.tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = listModelKeranjang.get(holder.getAdapterPosition()).getSelectedFood().getTotalItemKeranjang();
                if(value >= 0) {
                    System.out.println("Tambah Berhasil");
                    System.out.println("Value After Tambah = " + value);
                    adapterCartInterface.tambahPesanan(holder.getAdapterPosition());
                }
            }
        });
        if(listModelKeranjang.get(position).isStatusCheckBoxChecked() == true){
            holder.linearLayoutContainerAddOrderItem.setVisibility(View.INVISIBLE);
        } else{
            holder.linearLayoutContainerAddOrderItem.setVisibility(View.VISIBLE);
        }
        holder.selectedItemCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CompoundButton) view).isChecked()){
                    adapterCartInterface.checkBoxItemSelected(holder.getAdapterPosition(), true);
                    System.out.println("Checked");
                    holder.linearLayoutContainerAddOrderItem.setVisibility(View.INVISIBLE);
                } else {
                    System.out.println("UnChecked");
                    adapterCartInterface.checkBoxItemSelected(holder.getAdapterPosition(),false);
                    holder.linearLayoutContainerAddOrderItem.setVisibility(View.VISIBLE);
                }
            }
        });
        Picasso.get()
                .load(listModelKeranjang.get(position).getSelectedFood().getGambar_barang()).resize(512,512).centerCrop()
                .into(holder.imageCart);
        holder.jumlahPesanan.setText(String.valueOf(listModelKeranjang.get(position).getSelectedFood().getTotalItemKeranjang()));
        holder.hargaPesanan.setText(
                String.valueOf(
                Util.convertToRupiah(
                listModelKeranjang.get(position).getSelectedFood().getHarga()))
        );
        holder.namaPesanan.setText(String.valueOf(listModelKeranjang.get(position).getSelectedFood().getNama_barang()));
    }

    @Override
    public int getItemCount() {
        return listModelKeranjang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageCart;
        TextView hargaPesanan , jumlahPesanan, namaPesanan , tambah , kurang;
        CheckBox selectedItemCheckBox;
        LinearLayout linearLayoutContainerAddOrderItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            selectedItemCheckBox = itemView.findViewById(R.id.id_check_box_keranjang_layout_adapter);
            imageCart = itemView.findViewById(R.id.id_card_wraped_layout_keranjang_adapter);
            hargaPesanan = itemView.findViewById(R .id.id_tv_jumlah_item_checkout_adapter);
            jumlahPesanan = itemView.findViewById(R.id.id_tv_jumlah_item_keranjang);
            namaPesanan = itemView.findViewById(R.id.id_tv_tittle_item_checkout_adapter);
            kurang = itemView.findViewById(R.id.id_tv_kurang_item_keranjang);
            tambah = itemView.findViewById(R.id.id_tv_tambah_item_keranjang);
            linearLayoutContainerAddOrderItem = itemView.findViewById(R.id.id_container_button_add_topping);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
