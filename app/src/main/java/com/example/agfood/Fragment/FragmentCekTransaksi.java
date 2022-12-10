package com.example.agfood.Fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agfood.Adapter.AdapterRinciaTransaksi;
import com.example.agfood.Model.ModelKeranjang;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentCekTransaksiBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCekTransaksi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCekTransaksi extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<ModelKeranjang> modelKeranjangList;
    private String metodePembayaran;
    private String noRek;

    public FragmentCekTransaksi(List<ModelKeranjang> listKeranjang , String metodePembayaran , String noRek) {
        this.modelKeranjangList = listKeranjang;
        this.metodePembayaran = metodePembayaran;
        this.noRek = noRek;
        // Required empty public constructor
    }
    public FragmentCekTransaksi() {
        // Required empty public constructor
    }

    /**
         * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCekTransaksi.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCekTransaksi newInstance(String param1, String param2) {
        FragmentCekTransaksi fragment = new FragmentCekTransaksi();
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
    int totl = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentCekTransaksiBinding fragmentCekTransaksiBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_cek_transaksi,container,false);
        switch (metodePembayaran){
            case "Dana":
                fragmentCekTransaksiBinding.idCekTransaksiMetodePembayaran.setText("Melalui Transfer E-Wallet Dana Ke Nomor");
                break;
            case "Shoppe Pay":
                fragmentCekTransaksiBinding.idCekTransaksiMetodePembayaran.setText("Melalui Transfer E-Wallet Shoppe Pay Ke Nomor");
                break;
            case "BNI":
                fragmentCekTransaksiBinding.idCekTransaksiMetodePembayaran.setText("Melalui Transfer Bank BNI Ke No Rek");
                break;
        }
        fragmentCekTransaksiBinding.idCekTransaksiNomrRek.setText(noRek);

        AdapterRinciaTransaksi adapterRinciaTransaksi = new AdapterRinciaTransaksi(modelKeranjangList);
        fragmentCekTransaksiBinding.idCekTransaksiRecRincianTransaksi.setAdapter(adapterRinciaTransaksi);
        for(ModelKeranjang mdl :modelKeranjangList){
            totl += mdl.getSelectedFood().getHarga();
        }
        fragmentCekTransaksiBinding.idCekTransaksiTotalPembayaranTextView.setText(Util.convertToRupiah(totl));
        fragmentCekTransaksiBinding.idCekTransaksiTotalPembayaranFinalTextView.setText(Util.convertToRupiah(totl));


        return fragmentCekTransaksiBinding.getRoot();


    }
}