package com.example.agfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agfood.Model.ModelKeranjang;
import com.example.agfood.R;
import com.example.agfood.Util.Util;

import java.util.List;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolder>{
    List<ModelKeranjang> listModelKeranjang;
    AdapterCart.AdapterCartInterface adapterCartInterface;

    public AdapterCart(List<ModelKeranjang> listModelKeranjang, AdapterCartInterface adapterCartInterface) {
        this.listModelKeranjang = listModelKeranjang;
        this.adapterCartInterface = adapterCartInterface;
    }
    public interface AdapterCartInterface{
        void tambahPesanan(int positionPesanan ,int jumlah);
        void kurangPesanan(int positionPesanan ,int jumlah);
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
                int value = listModelKeranjang.get(holder.getAdapterPosition()).getSelectedFood().getTotalStockFood();
                System.out.println("Satuan toping = " + value);
                if(value >=  1){
                    System.out.println("Kurang Berhasil");

                    System.out.println("Value After Delete = " + value);
                    adapterCartInterface.kurangPesanan(holder.getAdapterPosition(),value);
                }
            }
        });
        holder.tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = listModelKeranjang.get(holder.getAdapterPosition()).getSelectedFood().getTotalStockFood();
                if(value >= 0) {
                    System.out.println("Tambah Berhasil");

                    System.out.println("Value After Tambah = " + value);
                    adapterCartInterface.tambahPesanan(holder.getAdapterPosition(), value);
                }
            }
        });
        holder.selectedItemCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CompoundButton) view).isChecked()){
                    adapterCartInterface.checkBoxItemSelected(holder.getAdapterPosition(), true);
                    System.out.println("Checked");
                } else {
                    System.out.println("UnChecked");
                    adapterCartInterface.checkBoxItemSelected(holder.getAdapterPosition(),false);
                }
            }
        });
        holder.imageCart.setImageResource(listModelKeranjang.get(position).getSelectedFood().getImageFood());
        holder.jumlahPesanan.setText(String.valueOf(listModelKeranjang.get(position).getSelectedFood().getTotalStockFood()));
        holder.hargaPesanan.setText(String.valueOf(Util.convertToRupiah(listModelKeranjang.get(position).getSelectedFood().getHargaFood())));
        holder.namaPesanan.setText(String.valueOf(listModelKeranjang.get(position).getSelectedFood().getNameFood()));
    }

    @Override
    public int getItemCount() {
        return listModelKeranjang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageCart;
        TextView hargaPesanan , jumlahPesanan, namaPesanan , tambah , kurang;
        CheckBox selectedItemCheckBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            selectedItemCheckBox = itemView.findViewById(R.id.id_check_box_keranjang_layout_adapter);
            imageCart = itemView.findViewById(R.id.id_img_item_keranjang_adapter);
            hargaPesanan = itemView.findViewById(R .id.id_tv_harga_item_keranjang_adapter);
            jumlahPesanan = itemView.findViewById(R.id.id_tv_jumlah_item_keranjang);
            namaPesanan = itemView.findViewById(R.id.id_tv_nama_item_keranjang_adapter);
            kurang = itemView.findViewById(R.id.id_tv_kurang_item_keranjang);
            tambah = itemView.findViewById(R.id.id_tv_tambah_item_keranjang);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
