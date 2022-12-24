package com.example.agfood.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agfood.Adapter.AdapterMetodePemesanan;
import com.example.agfood.Model.ModelKeranjang;
import com.example.agfood.ModelAdapter.ModelAdapterMetodePesanan;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentMetodePemesananBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMetodePemesanan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMetodePemesanan extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentMetodePemesanan() {
        // Required empty public constructor
    }

    List<ModelKeranjang> barangList;
    String idKeranjang;
    public FragmentMetodePemesanan(List<ModelKeranjang> barangYangAkanDiOrderList
    , String idKeranjang) {
    this.barangList = barangYangAkanDiOrderList;
    this.idKeranjang = idKeranjang;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMetodePemesanan.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMetodePemesanan newInstance(String param1, String param2) {
        FragmentMetodePemesanan fragment = new FragmentMetodePemesanan();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    String opsi = "";
    String contentMetodePesanan = "";
    FragmentMetodePemesananBinding bindingMetodeFragment;
    SharedPreferences sharedPreferences;
    AdapterMetodePemesanan adapterMetodePemesanan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bindingMetodeFragment = DataBindingUtil.inflate(inflater,R.layout.fragment_metode_pemesanan,container,false);
        bindingMetodeFragment.idBtnDetailMakananBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.switchFragment(getActivity().getSupportFragmentManager(),new FragmentCheckoutBarang(barangList,idKeranjang),"FRAGMNT_CHECKOUTBARANG");
            }
        });
        List<ModelAdapterMetodePesanan> listMetodePesanan = new ArrayList<>();
        listMetodePesanan.add(new ModelAdapterMetodePesanan("Cod",false,"Pesananmu akan dihantarkan sesuai lokasi tujuan kamu "));
        listMetodePesanan.add(new ModelAdapterMetodePesanan("Jasa Kirim",false,"Pesananmu akan dihantarkan sesuai lokasi tujuan kamu "));
        listMetodePesanan.add(new ModelAdapterMetodePesanan("Ambil Di Toko",false,"Pesanamu dapat di ambil di toko kami"));
        sharedPreferences = getActivity().getSharedPreferences("PREF_BUTTON_PESANAN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        AdapterMetodePemesanan.listenerAdapterMetodePesanan adapterMetodePesananListener = new AdapterMetodePemesanan.listenerAdapterMetodePesanan() {
            @Override
            public void selectMetodePesanan(int positionOfPesanan) {
                if(listMetodePesanan.get(positionOfPesanan).isMetodePesananSelected()){
                    listMetodePesanan.get(positionOfPesanan).setMetodePesananSelected(true);
                    editor.putInt("CURRENT_POST_METODE_PESANAN",positionOfPesanan);
                    editor.commit();
                } else if(!listMetodePesanan.get(positionOfPesanan).isMetodePesananSelected()){
                    int currentVal = sharedPreferences.getInt("CURRENT_POST_METODE_PESANAN",0);
                    listMetodePesanan.get(currentVal).setMetodePesananSelected(false);
                    listMetodePesanan.get(positionOfPesanan).setMetodePesananSelected(true);
                    editor.putInt("CURRENT_POST_METODE_PESANAN",positionOfPesanan);
                    editor.commit();
                }
                opsi = listMetodePesanan.get(positionOfPesanan).getMetodePesananan();
                contentMetodePesanan = listMetodePesanan.get(positionOfPesanan).getDeskripiPesanan();
                adapterMetodePemesanan.notifyDataSetChanged();
            }
        };

        bindingMetodeFragment.idCardPilihMetodePesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(opsi.isEmpty()){
                        Toast.makeText(getActivity().getApplicationContext(),"Harap Memilih Metode Pemesanan !",Toast.LENGTH_LONG).show();
                    } else{
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PREF_METODE_PESANAN", Context.MODE_PRIVATE);
                        SharedPreferences.Editor sharedPreferencesEditors = sharedPreferences.edit();
                        sharedPreferencesEditors.putString("PREF_JUDUL_PESANAN",opsi);
                        sharedPreferencesEditors.putString("PREF_CONTENT_PESANAN",contentMetodePesanan);
                        sharedPreferencesEditors.commit();
                        Util.switchFragment(getActivity().getSupportFragmentManager(),new FragmentCheckoutBarang(barangList,idKeranjang),"FRAMGENT_CHECKOUT");
                    }
            }
        });

        adapterMetodePemesanan = new AdapterMetodePemesanan(adapterMetodePesananListener,listMetodePesanan);
        bindingMetodeFragment.idRecMetodePesanan.setAdapter(adapterMetodePemesanan);


        return bindingMetodeFragment.getRoot();
    }
}