package com.example.agfood.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agfood.Adapter.AdapterCheckoutItem;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelKeranjang;
import com.example.agfood.Model.ModelResponseBarang;
import com.example.agfood.Model.ModelResponseGetCurrentIdBarang;
import com.example.agfood.R;
import com.example.agfood.Util.SharedPrefDetail;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentCheckoutBarangBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCheckoutBarang#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCheckoutBarang extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<ModelKeranjang> barangYangAkanDiOrderList;
    String idKeranjang;
    public FragmentCheckoutBarang(List<ModelKeranjang> listKeranjangDariUser, String idKeranjang) {
        // Required empty public constructor
        this.barangYangAkanDiOrderList = listKeranjangDariUser;
        this.idKeranjang = idKeranjang;
    }
    public FragmentCheckoutBarang(){

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCheckoutBarang.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCheckoutBarang newInstance(String param1, String param2) {
        FragmentCheckoutBarang fragment = new FragmentCheckoutBarang();
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

    public Map<String,String> getMetodePesanan(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PREF_METODE_PESANAN", Context.MODE_PRIVATE);
        SharedPreferences sharedPrefPembayaran = getActivity().getSharedPreferences("PREF_METODE_PEMBAYARAN",Context.MODE_PRIVATE);
        SharedPreferences sharedPrefAlamat = getActivity().getSharedPreferences("PREF_ALAMAT_SAYA",Context.MODE_PRIVATE);
        String alamat = sharedPrefAlamat.getString("PREF_ALAMAT","Silahkan Pilih Alamat Anda");
        String tittleMetodePesanan = sharedPreferences.getString("PREF_JUDUL_PESANAN","COD");
        String contentMetodePesanan = sharedPreferences.getString("PREF_CONTENT_PESANAN","Pesananmu akan dihantarkan sesuai lokasi tujuan kamu");
        String keyPembayaranMetodePesanan = sharedPrefPembayaran.getString("PREF_OPSI_PEMBAYARAN","COD");
        String noRekPembayaran = sharedPrefPembayaran.getString("PREF_NO_REK_PEMBAYARAN","");
        HashMap<String,String> mapPesananDetail = new HashMap<>();
        mapPesananDetail.put("KEY_JUDUL_PESANAN", tittleMetodePesanan);
        mapPesananDetail.put("KEY_CONTENT_PESANAN",contentMetodePesanan);
        mapPesananDetail.put("KEY_PEMBAYARAN",keyPembayaranMetodePesanan);
        mapPesananDetail.put("KEY_NOREK",noRekPembayaran);
        mapPesananDetail.put("KEY_ALAMAT",alamat);
        return mapPesananDetail;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ModelAccount mdl;
        UtilPref utilPref = new UtilPref();
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Util.switchFragment(getActivity().getSupportFragmentManager(),new HomeFragment(),"AAA");
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        SharedPrefDetail sharedPrefDetailAccount = utilPref.accountPrefences;
        mdl = Util.getCurrentAccount(sharedPrefDetailAccount, getActivity());
        Util.hiddenNavBottom(getActivity());
        FragmentCheckoutBarangBinding fragmentCheckoutBarangBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_checkout_barang,container,false);
        if(barangYangAkanDiOrderList != null)
        {
             fragmentCheckoutBarangBinding.idCardContainerMetodePesananCheckoutBarang.setOnClickListener(this::onClick);
            fragmentCheckoutBarangBinding.idCardContainerMetodePembayaranContaner.setOnClickListener(this::onClick);
            Map<String,String> metodePesanan = getMetodePesanan();
            fragmentCheckoutBarangBinding.idTextViewCheckoutLeftTotalPesanan.setText("Total Pesanan (" + barangYangAkanDiOrderList.size() + ")");
            int hargaTotal = 0;
            for(ModelKeranjang mdLKeranjang : barangYangAkanDiOrderList){
                hargaTotal += mdLKeranjang.getSelectedFood().getHarga();
            }
            fragmentCheckoutBarangBinding.idBtnDetailMakananBackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Util.switchFragment(getActivity().getSupportFragmentManager(),new HomeFragment(),"FRAGMENT_HOME");
                }
            });
            fragmentCheckoutBarangBinding.idTextViewCheckoutTotalPembayaran.setText(Util.convertToRupiah(hargaTotal));
            fragmentCheckoutBarangBinding.idTextViewCheckoutRightHargaTotalPesanan.setText(Util.convertToRupiah(hargaTotal));
            fragmentCheckoutBarangBinding.idTextViewCheckoutSubtotal.setText(Util.convertToRupiah(hargaTotal));

            fragmentCheckoutBarangBinding.idTextViewCheckoutMetodePembayaran.setText(metodePesanan.get("KEY_PEMBAYARAN"));
            fragmentCheckoutBarangBinding.idTextViewCheckoutHeaderMetodePesanan.setText(metodePesanan.get("KEY_JUDUL_PESANAN"));

            fragmentCheckoutBarangBinding.idTextViewCheckoutBodyMetodPesanan.setText(metodePesanan.get("KEY_CONTENT_PESANAN"));
            fragmentCheckoutBarangBinding.idTextViewCheckoutBarangAlamat.setText(metodePesanan.get("KEY_ALAMAT"));
            AdapterCheckoutItem adapterCheckoutItem = new AdapterCheckoutItem(barangYangAkanDiOrderList);
            fragmentCheckoutBarangBinding.idRecCheckoutListBarang.setAdapter(adapterCheckoutItem);
            fragmentCheckoutBarangBinding.idBtnBuatPesanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Util.getApiRequetData().getListIdKeranjang().enqueue(new Callback<ModelResponseGetCurrentIdBarang>() {
                        @Override
                        public void onResponse(Call<ModelResponseGetCurrentIdBarang> call, Response<ModelResponseGetCurrentIdBarang> response) {
                            System.out.println("Response Id Keranjang = " + response.body().getIdKeranjang() + " data");
                            if(response.body().getKode() == 0){
                                idKeranjang = "KRJ" + response.body().getIdKeranjang();
                            } else if(response.body().getKode() == 1){
                                idKeranjang = "KRJ" + response.body().getIdKeranjang();
                            }

                            for(ModelKeranjang mSelectedFoodModel : barangYangAkanDiOrderList){
                                Util.getApiRequetData().pindahPesananKeOrderPending(
                                        mdl.idAkun,
                                        mSelectedFoodModel.getSelectedFood().getId_barang(),
                                        String.valueOf(mSelectedFoodModel.getSelectedFood().getHarga()),
                                        String.valueOf(mSelectedFoodModel.getSelectedFood().getTotalItemKeranjang()),idKeranjang,
                                        metodePesanan.get("KEY_PEMBAYARAN"),
                                        metodePesanan.get("KEY_NOREK"),
                                        String.valueOf(mSelectedFoodModel.getSelectedFood().getPesanDariUser()),
                                        metodePesanan.get("KEY_ALAMAT")
                                ).enqueue(
                                        new Callback<ModelResponseBarang>() {
                                            @Override
                                            public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                                                int kode = response.body().getKode();
                                                System.out.println(response.body().getPesan() + " PESAN ");
                                                if(kode == 1){
                                                } else{
                                                    Toast.makeText(getActivity().getApplicationContext(),"Masukan Keranjang Gagal",Toast.LENGTH_LONG).show();
                                                }
                                            }
                                            @Override
                                            public void onFailure(Call<ModelResponseBarang> call, Throwable t) {
                                                System.out.println(t.getCause() + ":" + t.getMessage() + " ERROR");
                                            }
                                        });
                            }
                            Util.switchFragment(getActivity().getSupportFragmentManager(),new FragmentCekTransaksi(barangYangAkanDiOrderList,
                                    metodePesanan.get("KEY_PEMBAYARAN"),metodePesanan.get("KEY_NOREK"),metodePesanan.get("KEY_ALAMAT"),"REQUEST_CHECKOUT_ORDER",idKeranjang,"belum_bayar"),"FRAGMENT_TEST");
                        }
                        @Override
                        public void onFailure(Call<ModelResponseGetCurrentIdBarang> call, Throwable t) {
                        }
                    });

                }
            });
            fragmentCheckoutBarangBinding.idLayoutAlamatPengiriman.setOnClickListener(this);
        }

        return fragmentCheckoutBarangBinding.getRoot();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_card_container_metode_pesanan_checkout_barang:
                Util.switchFragment(getActivity().getSupportFragmentManager(),new FragmentMetodePemesanan(barangYangAkanDiOrderList,idKeranjang),"METODE_PEMESANAN");
                break;
            case R.id.id_card_container_metode_pembayaran_contaner:
                Util.switchFragment(getActivity().getSupportFragmentManager(), new FragmentMetodePembayaran(barangYangAkanDiOrderList,idKeranjang),"FRAGMENT_CHECKOUT");
                break;
            case R.id.id_layout_alamat_pengiriman:
                Util.switchFragment(getActivity().getSupportFragmentManager(),new FragmentAturAlamatSaya(barangYangAkanDiOrderList,idKeranjang,"CHECKOUT"),"ATUR_ALAMAT");
            default:
                break;

        }
    }
}