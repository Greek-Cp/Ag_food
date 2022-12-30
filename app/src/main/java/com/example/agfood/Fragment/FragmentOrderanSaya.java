package com.example.agfood.Fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agfood.Adapter.AdapterCartOrder;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelBarang;
import com.example.agfood.Model.ModelIdKeranjang;
import com.example.agfood.Model.ModelKeranjang;
import com.example.agfood.Model.ModelOrderan;
import com.example.agfood.Model.ModelResponseBarang;
import com.example.agfood.Model.ModelResponseIdKeranjang;
import com.example.agfood.R;
import com.example.agfood.Util.SharedPrefDetail;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentOrderanSayaBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentOrderanSaya#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentOrderanSaya extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentOrderanSaya() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentOrderanSaya.
     */
    ModelAccount mdl;
    UtilPref utilPref = new UtilPref();

    // TODO: Rename and change types and number of parameters
    public static FragmentOrderanSaya newInstance(String param1, String param2) {
        FragmentOrderanSaya fragment = new FragmentOrderanSaya();
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentOrderanSayaBinding fragmentOrderanSayaBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_orderan_saya,container,false);
        SharedPrefDetail sharedPrefDetailAccount = utilPref.accountPrefences;
        Util.hiddenNavBottom(getActivity());
        mdl = Util.getCurrentAccount(sharedPrefDetailAccount, getActivity());
        Util.getApiRequetData().getListIdKeranjang(mdl.getIdAkun()).enqueue(new Callback<ModelResponseIdKeranjang>() {
            @Override
            public void onResponse(Call<ModelResponseIdKeranjang> call,
                                   Response<ModelResponseIdKeranjang> response) {
                   response.body().getDataKeranjang();
                List<ModelIdKeranjang> listModelOrderan = new ArrayList<>();
                if(response.body().getDataKeranjang() != null){
                    for(ModelIdKeranjang mdlIdKeranjang : response.body().getDataKeranjang()){
                        listModelOrderan.add(new ModelIdKeranjang(mdlIdKeranjang.getId_keranjang()));
                    }
                    Util.getApiRequetData().getListOrderByAccount(mdl.getIdAkun()).enqueue(new Callback<ModelResponseBarang>() {
                        @Override
                        public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                            System.out.println(response.body().getPesan() + " PESAN");
                            List<ModelOrderan> listModelOrderanDiPilih = new ArrayList<>();
                            List<ModelKeranjang> listModelKeranjang = new ArrayList<>();
                            for(ModelIdKeranjang modelOrderan : listModelOrderan){
                                List<ModelBarang> listBarangSesuaiKeranjang = new ArrayList<>();
                                int totHarga = 0;
                                for(ModelBarang mdlBarang : response.body().getDataBarang()){
                                    if(modelOrderan.getId_keranjang().equals(mdlBarang.getId_keranjang())){
                                        totHarga += mdlBarang.getHarga();
                                        listBarangSesuaiKeranjang.add(mdlBarang);
                                        listModelKeranjang.add(new ModelKeranjang(mdlBarang));
                                    }
                                }
                                ModelOrderan modelOrderan1 = new ModelOrderan(modelOrderan.getId_keranjang());
                                modelOrderan1.setTotalHargaOrderan(totHarga);
                                modelOrderan1.setListBarangYgDiOrder(listBarangSesuaiKeranjang);
                                listModelOrderanDiPilih.add(modelOrderan1);
                                totHarga = 0;
                            }
                            AdapterCartOrder.AdapterCardOrderListener adapterCardOrderListener = new AdapterCartOrder.AdapterCardOrderListener() {
                                @Override
                                public void clickOrderListener(int posisiItemYangDiKlik) {
                                    List<ModelKeranjang> listKeranjang = new ArrayList<>();
                                    for(ModelBarang mdlBarangFromList : listModelOrderanDiPilih.get(posisiItemYangDiKlik).getListBarangYgDiOrder()){
                                        listKeranjang.add(new ModelKeranjang(mdlBarangFromList));
                                    }

                                    System.out.println("PENGIRIMAN = " + listKeranjang.get(0).getSelectedFood().getAlamatPengiriman());
                                    System.out.println("data = " + listKeranjang.get(0).getSelectedFood().getStatus_bayar() + " STATUS");
                                            System.out.println(listModelOrderanDiPilih.get(posisiItemYangDiKlik).getListBarangYgDiOrder());
                                            Util.switchFragment(getActivity().getSupportFragmentManager(),
                                                    new FragmentCekTransaksi(
                                                            listKeranjang
                                                            ,listKeranjang.get(0).getSelectedFood().getMetodePembayaran(),
                                                            listKeranjang.get(0).getSelectedFood().getAkunPembayaran()
                                                            ,listKeranjang.get(0).getSelectedFood().getAlamatPengiriman(),
                                                            "FROM_ORODERAN",
                                                            listModelOrderan.get(posisiItemYangDiKlik).getId_keranjang()
                                                            ,listKeranjang.get(0).getSelectedFood().getStatus_bayar()),"FRAGMENT_CEK_TRANSAKSI");
                                }
                            };
                            if(listModelOrderanDiPilih != null){
                                AdapterCartOrder adapterCartOrder = new AdapterCartOrder(listModelOrderanDiPilih,adapterCardOrderListener,getActivity().getApplicationContext());
                                fragmentOrderanSayaBinding.idRecOrderanSaya.setAdapter(adapterCartOrder);
                                System.out.println("Data Final = "  + new Gson().toJson(listModelOrderan));
                            }
                        }

                        @Override
                        public void onFailure(Call<ModelResponseBarang> call, Throwable t) {

                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<ModelResponseIdKeranjang> call, Throwable t) {

            }
        });
        return fragmentOrderanSayaBinding.getRoot();




















    }
}