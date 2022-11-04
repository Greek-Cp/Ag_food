package com.example.agfood.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agfood.Adapter.AdapterCart;
import com.example.agfood.Model.ModelFood;
import com.example.agfood.Model.ModelKeranjang;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentKeranjangBinding;

import java.util.ArrayList;
import java.util.List;

public class FragmentKeranjang extends Fragment {
    FragmentKeranjangBinding fragmentKeranjangBinding;
    List<ModelKeranjang> listDataKeranjang;
    AdapterCart adapterCart;
    void testDataKeranjang(){
        listDataKeranjang = new ArrayList<>();
        ModelFood martabakAsin = new ModelFood("Martaba Asin", 30, 3000, R.drawable.food_img_1);
        martabakAsin.setTotalStockFood(4);
        martabakAsin.setHargaFood(4000);
        ModelFood nasiGorengAsin = new ModelFood("Nasi Goreng Asin", 10, 3000, R.drawable.food_img_2);
        nasiGorengAsin.setTotalStockFood(4);
        nasiGorengAsin.setHargaFood(4000);
        listDataKeranjang.add(new ModelKeranjang(martabakAsin));
        listDataKeranjang.add(new ModelKeranjang(nasiGorengAsin));
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentKeranjangBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_keranjang, container, false);
        testDataKeranjang();
        AdapterCart.AdapterCartInterface adapterCartInterface = new AdapterCart.AdapterCartInterface() {
            @Override
            public void tambahPesanan(int positionPesanan, int jumlah) {
                jumlah += 1;
                listDataKeranjang.get(positionPesanan).getSelectedFood().setTotalStockFood(jumlah);
                adapterCart.notifyDataSetChanged();
            }

            @Override
            public void kurangPesanan(int positionPesanan, int jumlah) {
                jumlah -= 1;
                listDataKeranjang.get(positionPesanan).getSelectedFood().setTotalStockFood(jumlah);
                adapterCart.notifyDataSetChanged();
            }

            @Override
            public void checkBoxItemSelected(int position, boolean statusCheckbox) {
                listDataKeranjang.get(position).setStatusCheckBoxChecked(statusCheckbox);
                if(listDataKeranjang.get(position).isStatusCheckBoxChecked()){
                    System.out.print("Ter Klik");
                } else{
                    System.out.print("Tidak Di Pilih");
                }
                adapterCart.notifyDataSetChanged();
            }
        };
        fragmentKeranjangBinding.idBtnDetailMakananBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.switchFragment(new HomeFragment(), getActivity());
            }
        });
         adapterCart = new AdapterCart(listDataKeranjang,adapterCartInterface);
        fragmentKeranjangBinding.idRecKeranjang.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.VERTICAL,false));
        fragmentKeranjangBinding.idRecKeranjang.setAdapter(adapterCart);
        return  fragmentKeranjangBinding.getRoot();
    }
}
