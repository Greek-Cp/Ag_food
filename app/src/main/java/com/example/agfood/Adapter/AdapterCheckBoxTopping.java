package com.example.agfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agfood.Model.ModelTopping;
import com.example.agfood.R;
import com.example.agfood.Util.Util;

import java.util.List;

public class AdapterCheckBoxTopping  extends RecyclerView.Adapter<AdapterCheckBoxTopping.ViewHolder> {
    List<ModelTopping> listTopping;
    AdapterCheckBoxTopping.AdapterToppingInteface adapterToppingInteface;
    public AdapterCheckBoxTopping(List<ModelTopping> listTopping, AdapterToppingInteface adapterToppingInteface) {
        this.listTopping = listTopping;
        this.adapterToppingInteface = adapterToppingInteface;
    }

    public interface AdapterToppingInteface {
        void clickCheckBox(int  position,boolean statusChecked);
        void clickAddTopping(int position,int jumlahToping);
        void clickMinTopping(int position,int jumlahToping);
    }
    @Override
    public AdapterCheckBoxTopping.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_checkbox_topping_detail_makanan,parent,false);
        return new ViewHolder(v);
    }

    static int hitungTotalHargaTopping(int jumlah , int harga){
        return jumlah * harga;
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterCheckBoxTopping.ViewHolder holder, int position) {
        holder.imageViewTopping.setImageResource(listTopping.get(position).getImageTopping());
        holder.namaToping.setText(listTopping.get(position).getNamaTopping());
        holder.hargaToping.setText(String.valueOf(Util.convertToRupiah(hitungTotalHargaTopping(listTopping.get(position).getHargaTopping(), listTopping.get(position).getSatuanTopping()))));
        holder.kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = listTopping.get(holder.getAdapterPosition()).getSatuanTopping();
                System.out.println("Satuan toping = " + value);
                if(value >=  1){
                    System.out.println("Kurang Berhasil");
                    value -= 1;
                    System.out.println("Value After Delete = " + value);
                    adapterToppingInteface.clickMinTopping(holder.getAdapterPosition(),value);
                }
            }
        });
        holder.tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = listTopping.get(holder.getAdapterPosition()).getSatuanTopping();
                if(value >= 0) {
                    System.out.println("Tambah Berhasil");
                    value += 1;
                    System.out.println("Value After Tambah = " + value);
                    adapterToppingInteface.clickAddTopping(holder.getAdapterPosition(), value);
                }
            }
        });
        holder.checkBoxTopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CompoundButton) view).isChecked()){
                    System.out.println("Checked");
                    holder.cardViewAddToping.setVisibility(View.INVISIBLE);
                    holder.tambah.setVisibility(View.INVISIBLE);
                    holder.kurang.setVisibility(View.INVISIBLE);

                    adapterToppingInteface.clickCheckBox(holder.getAdapterPosition(),true);
                } else {
                    System.out.println("Un-Checked");
                    adapterToppingInteface.clickCheckBox(holder.getAdapterPosition(),false);
                    holder.cardViewAddToping.setVisibility(View.VISIBLE);
                    holder.tambah.setVisibility(View.VISIBLE);
                    holder.kurang.setVisibility(View.VISIBLE);
                }
            }
        });
        holder.value.setText(String.valueOf(listTopping.get(position).getSatuanTopping()));
    }

    @Override
    public int getItemCount() {
        return listTopping.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CheckBox checkBoxTopping;
        CardView    cardViewAddToping ,cardViewRemoveTopping;
        TextView namaToping, hargaToping , tambah , kurang ,value;
        LinearLayout layoutContainerTambahToppingDanKurangToping;
        ImageView imageViewTopping;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewTopping = itemView.findViewById(R.id.id_image_topping);
            checkBoxTopping = itemView.findViewById(R.id.id_check_box_topping);
            namaToping = itemView.findViewById(R.id.id_tv_nama_topping);
            hargaToping = itemView.findViewById(R.id.id_tv_harga_topping);
            tambah = itemView.findViewById(R.id.id_tv_tambah_item_keranjang);
            kurang = itemView.findViewById(R.id.id_tv_kurang_item_keranjang);
            value = itemView.findViewById(R.id.id_tv_jumlah_item_keranjang);
            cardViewAddToping = itemView.findViewById(R.id.id_card_container_add_min_topping);
            layoutContainerTambahToppingDanKurangToping = itemView.findViewById(R.id.id_container_button_add_topping);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {

        }
    }
}
