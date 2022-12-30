package com.example.agfood.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agfood.Adapter.AdapterMetodePembayaran;
import com.example.agfood.Model.ModelKeranjang;
import com.example.agfood.ModelAdapter.ModelAdapterPembayaran;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentMetodePembayaranBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMetodePembayaran#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMetodePembayaran extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentMetodePembayaran() {
        // Required empty public constructor
    }

    List<ModelKeranjang> modelKeranjangList;
    String idKeranjang;
    public FragmentMetodePembayaran(List<ModelKeranjang> barangYangAkanDiOrderList,String idKeranjang) {
        this.modelKeranjangList = barangYangAkanDiOrderList;
        this.idKeranjang = idKeranjang;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMetodePembayaran.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMetodePembayaran newInstance(String param1, String param2) {
        FragmentMetodePembayaran fragment = new FragmentMetodePembayaran();
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
    String opsiPembayaran = "";
    String noRekPembayaran = "";
    RecyclerView recyclerViewPesanan;
    SharedPreferences sharedPreferencesPembayaran;
    AdapterMetodePembayaran adapterMetodePembayaran;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentMetodePembayaranBinding fragmentMetodePembayaranBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_metode_pembayaran,container,false);
        List<ModelAdapterPembayaran> modelAdapterPembayaranList = new ArrayList<>();
        modelAdapterPembayaranList.add(new ModelAdapterPembayaran("COD Ditempat",R.drawable.cod_ditempat,"Harap Membayarkan Secara Lansung",false));
        modelAdapterPembayaranList.add(new ModelAdapterPembayaran("Dana",R.drawable.ic_dana,"085608150983",false));
        modelAdapterPembayaranList.add(new ModelAdapterPembayaran("Shoppe Pay",R.drawable.ic_shoope,"085608150983",false));
        modelAdapterPembayaranList.add(new ModelAdapterPembayaran("BNI",R.drawable.ic_bni,"1153459155",false));
        sharedPreferencesPembayaran = getActivity().getSharedPreferences("PREF_BUTTON_PEMBAYARAN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesPembayaran.edit();

        AdapterMetodePembayaran.AdapterMetodePembayaranListener adapterMetodePembayaranListener = new AdapterMetodePembayaran.AdapterMetodePembayaranListener() {
            @Override
            public void selectionItemPayment(int positionOfPayment){
                /*
                Toast.makeText(getActivity().getApplicationContext(),
                        "Pembayaran = "
                                + modelAdapterPembayaranList.get(positionOfPayment).getNamaServicePayment()
                        ,Toast.LENGTH_LONG).show();
                 */
                if(modelAdapterPembayaranList.get(positionOfPayment).isMetodePesananSelected()){
                    modelAdapterPembayaranList.get(positionOfPayment).setMetodePesananSelected(true);
                    editor.putInt("CURRENT_POST_METODE_PESANAN",positionOfPayment);
                    editor.commit();
                } else if(!modelAdapterPembayaranList.get(positionOfPayment).isMetodePesananSelected()){
                    int currentVal = sharedPreferencesPembayaran.getInt("CURRENT_POST_METODE_PESANAN",0);
                    modelAdapterPembayaranList.get(currentVal).setMetodePesananSelected(false);
                    modelAdapterPembayaranList.get(positionOfPayment).setMetodePesananSelected(true);
                    editor.putInt("CURRENT_POST_METODE_PESANAN",positionOfPayment);
                    editor.commit();
                }
                opsiPembayaran = modelAdapterPembayaranList.get(positionOfPayment).getNamaServicePayment();
                noRekPembayaran = modelAdapterPembayaranList.get(positionOfPayment).getAkunToko();
                fragmentMetodePembayaranBinding.idBtnDetailMakananBackButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Util.switchFragment(getActivity().getSupportFragmentManager(),new FragmentCheckoutBarang(modelKeranjangList,idKeranjang),"FRAGMNT_CHECKOUTBARANG");
                    }
                });
                adapterMetodePembayaran.notifyDataSetChanged();

            }
        };
        fragmentMetodePembayaranBinding.idBtnPilihMetodePesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(opsiPembayaran.isEmpty()){
                    Toast.makeText(getActivity().getApplicationContext(),"Harap Memilih Metode Pembayaran !",Toast.LENGTH_LONG).show();
                } else{
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PREF_METODE_PEMBAYARAN", Context.MODE_PRIVATE);
                    SharedPreferences.Editor sharedPreferencesEditors = sharedPreferences.edit();
                    sharedPreferencesEditors.putString("PREF_OPSI_PEMBAYARAN",opsiPembayaran);
                    sharedPreferencesEditors.putString("PREF_NO_REK_PEMBAYARAN",noRekPembayaran);
                    sharedPreferencesEditors.commit();
                    Util.switchFragment(getActivity().getSupportFragmentManager(),new FragmentCheckoutBarang(modelKeranjangList,idKeranjang),"FRAGMENT_CHECKOUT ");
                }
            }
        });
       adapterMetodePembayaran = new AdapterMetodePembayaran(modelAdapterPembayaranList,adapterMetodePembayaranListener);

        fragmentMetodePembayaranBinding.idRecMetodePembayaran.setAdapter(adapterMetodePembayaran);

        return fragmentMetodePembayaranBinding.getRoot();
    }
}