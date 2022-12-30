package com.example.agfood.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agfood.Adapter.AdapterAlamatSaya;
import com.example.agfood.Adapter.AdapterMetodePemesanan;
import com.example.agfood.DataModel.ModelResponseAccount;
import com.example.agfood.DataModel.ModelResponseAlamat;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelAlamat;
import com.example.agfood.Model.ModelKeranjang;
import com.example.agfood.ModelAdapter.ModelAdapterMetodePesanan;
import com.example.agfood.R;
import com.example.agfood.Util.SharedPrefDetail;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentAturAlamatSayaBinding;
import com.example.agfood.databinding.FragmentMetodePemesananBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAturAlamatSaya#newInstance} factory method to
 * create an instance of this fragment.
 */

public class FragmentAturAlamatSaya extends Fragment  implements OnMapReadyCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GoogleMap mMap;
    private boolean oke = false;
    String id_user,alamatGet;
    List<Address> addressList = null;
    String opsi = "";
    String contentMetodePesanan = "";
    List<ModelKeranjang> modelKeranjangList;
    String idKeranjang;
    static String fromView;

    public FragmentAturAlamatSaya(String fromView) {
        // Required empty public constructor
        this.fromView = fromView;
        System.out.println("From View = " + fromView);
    }
    public FragmentAturAlamatSaya(List<ModelKeranjang> listKeranjangDariUser, String idKeranjang,
                                  String fromView){
        this.modelKeranjangList = listKeranjangDariUser;
        this.idKeranjang = idKeranjang;
        this.fromView = fromView;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAturAlamatSaya.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAturAlamatSaya newInstance(String param1, String param2) {
        FragmentAturAlamatSaya fragment = new FragmentAturAlamatSaya(fromView);
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
    FragmentAturAlamatSayaBinding fragmentAturAlamatSayaBinding;
    SharedPreferences sharedPreferences;
    AdapterMetodePemesanan adapterMetodePemesanan;
    ModelAccount mdl;
    AdapterAlamatSaya adapterAlamatSaya;
    String alamatYangDipilih;
    LocationManager locationManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Util.hiddenNavBottom(getActivity());
        fragmentAturAlamatSayaBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_atur_alamat_saya,container,false);
        UtilPref utilPref = new UtilPref();
        SharedPrefDetail sharedPrefDetailAccount = utilPref.accountPrefences;
        mdl = Util.getCurrentAccount(sharedPrefDetailAccount, getActivity());
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        Geocoder geocoder = new Geocoder(getActivity().getApplicationContext(), Locale.getDefault());
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        switch (fromView){
            case "PROFILE_USER":
                fragmentAturAlamatSayaBinding.idCardPilihAlamat.setVisibility(View.INVISIBLE);
                break;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 0, new LocationListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onLocationChanged(@NonNull Location location) {
//                try {
//                    addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
//                    if (addressList != null){
//                        Address returnAdd = addressList.get(0);
//                        StringBuilder stringBuilder = new StringBuilder("");
//                        for (int i = 0; i<returnAdd.getMaxAddressLineIndex(); i++){
//                            stringBuilder.append(returnAdd.getAddressLine(i)).append("\n");
//                        }
//                        Log.w("my Location",stringBuilder.toString());
//                    } else {
//                        Log.w("my Location","No Address");
//                    }
//                } catch (Exception e){
//                    System.out.println(e.getMessage());
//                }
                if (oke) {
                    mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(@NonNull LatLng latLng) {
                            try {
                                addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(latLng);
                                String addressLine = addressList.get(0).getAddressLine(0);
                                LatLng Posisi = new LatLng(latLng.latitude, latLng.longitude);
                                System.out.println("CLick Map" + Posisi.longitude  + " : " + Posisi.latitude);
                                mMap.addMarker(new MarkerOptions().position(Posisi).title("Posisi"));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(Posisi));
                                fragmentAturAlamatSayaBinding.AlamatSaya.setText(addressLine);
                                alamatGet = addressLine;
                                System.out.println(alamatGet + " test ");
                                float zoomLevel = 16.0f;
                                mMap.clear();
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
                                mMap.addMarker(markerOptions);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            }

        });
        sharedPreferences = getActivity().getSharedPreferences("PREF_BUTTON_ALAMAT", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Util.getApiRequetData().getAlamatList(mdl.getIdAkun()).enqueue(new Callback<ModelResponseAlamat>() {
            @Override
            public void onResponse(Call<ModelResponseAlamat> call, Response<ModelResponseAlamat> response) {
                List<ModelAlamat> listAlamat = response.body().getModelAlamatList();
                AdapterAlamatSaya.listenerAdapterAlamatSaya listenerAdapterAlamatSaya = new AdapterAlamatSaya.listenerAdapterAlamatSaya() {
                    @Override
                    public void selectMetodePesanan(int positionOfPesanan) {
                        if(response.body().getModelAlamatList().get(positionOfPesanan).isAlamatSelected()){
                            listAlamat.get(positionOfPesanan).setAlamatSelected(true);
                            editor.putInt("CURRENT_POST_METODE_ALAMAT",positionOfPesanan);
                            editor.commit();
                        } else if(!listAlamat.get(positionOfPesanan).isAlamatSelected()){
                            int currentVal = sharedPreferences.getInt("CURRENT_POST_METODE_ALAMAT",0);
                            listAlamat.get(currentVal).setAlamatSelected(false);
                            listAlamat.get(positionOfPesanan).setAlamatSelected(true);
                            editor.putInt("CURRENT_POST_METODE_ALAMAT",positionOfPesanan);
                            editor.commit();
                        }
                        alamatYangDipilih = listAlamat.get(positionOfPesanan).getAlamat();
                        adapterAlamatSaya.notifyDataSetChanged();
                    }

                    @Override
                    public void hapusAlamat(int posisiAlamat) {
                        Util.getApiRequetData().hapusAlamat(mdl.getIdAkun(),listAlamat.get(posisiAlamat).getAlamat()).enqueue(new Callback<ModelResponseAlamat>() {
                            @Override
                            public void onResponse(Call<ModelResponseAlamat> call, Response<ModelResponseAlamat> response) {
                                if(response.body().getKode() == 1){
                                    Toast.makeText(getActivity().getApplicationContext(),"Hapus Alamat Berhasil",Toast.LENGTH_LONG).show();
                                    editor.clear();

                                } else{
                                    Toast.makeText(getActivity().getApplicationContext(),"Hapus Alamat Gagal",Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<ModelResponseAlamat> call, Throwable t) {
                                System.out.println("error = " + t.getMessage());
                            }
                        });
                        listAlamat.remove(posisiAlamat);
                        adapterAlamatSaya.notifyDataSetChanged();
                    }
                };
                adapterAlamatSaya = new AdapterAlamatSaya(listenerAdapterAlamatSaya,response.body().getModelAlamatList());
                fragmentAturAlamatSayaBinding.idRecMetodePesanan.setAdapter(adapterAlamatSaya);
                adapterAlamatSaya.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ModelResponseAlamat> call, Throwable t) {

            }
        });
        fragmentAturAlamatSayaBinding.buttonTambahAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!fragmentAturAlamatSayaBinding.AlamatSaya.getText().toString().isEmpty()){
                    Call<ModelResponseAlamat> call = Util.getApiRequetData().tambahAlamat(mdl.getIdAkun(),fragmentAturAlamatSayaBinding.AlamatSaya.getText().toString());
                    call.enqueue(new Callback<ModelResponseAlamat>() {
                        @Override
                        public void onResponse(Call<ModelResponseAlamat> call, Response<ModelResponseAlamat> response) {
                            if(response.body().getKode() == 1){
                                Toast.makeText(getActivity().getApplicationContext(),"Berhasil Menambahkan Alamat",Toast.LENGTH_SHORT).show();
                                adapterAlamatSaya.notifyDataSetChanged();
                                if(fromView.equals("PROFILE_USER")){
                                    Util.switchFragment(getActivity().getSupportFragmentManager(),new FragmentAturAlamatSaya(modelKeranjangList,idKeranjang,"PROFILE_USER") , "FRAGMENT_ATUR");
                                } else{
                                    Util.switchFragment(getActivity().getSupportFragmentManager(),new FragmentAturAlamatSaya(modelKeranjangList,idKeranjang,"CHECKOUT") , "FRAGMENT_ATUR");
                                }
                                //Util.switchFragment(getActivity().getSupportFragmentManager(),new FragmentAturAlamatSaya(),"ATUR_ALAMAT");
                            } else{
                                Toast.makeText(getActivity().getApplicationContext(),"Gagal Menambahkan Alamat",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ModelResponseAlamat> call, Throwable t) {
                            System.out.println(t.getMessage() + " ERROR");
                        }
                    });
                } else{
                    Toast.makeText(getActivity().getApplicationContext(),"Mohon Maaf Alamat Tidak Boleh Kosong !",Toast.LENGTH_LONG).show();
                }
            }
        });
        fragmentAturAlamatSayaBinding.idBtnDetailMakananBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.switchFragment(getActivity().getSupportFragmentManager(),new HomeFragment(),"FRAGMENT_HOME");
            }
        });
        fragmentAturAlamatSayaBinding.idCardPilihAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PREF_ALAMAT_SAYA", Context.MODE_PRIVATE);
                SharedPreferences.Editor sharedPreferencesEditors = sharedPreferences.edit();
                sharedPreferencesEditors.putString("PREF_ALAMAT",alamatYangDipilih);
                sharedPreferencesEditors.commit();
                System.out.println("FROM VIEW = " + fromView);
                if(fromView.equals("PROFILE_USER")){
                    Util.switchFragment(getActivity().getSupportFragmentManager(),new FragmentAturAlamatSaya("PROFILE_USER"),"FRAGMENT_ATUR_ALAMAT");
                } else{
                    Util.switchFragment(getActivity().getSupportFragmentManager(),new FragmentCheckoutBarang(modelKeranjangList,idKeranjang),"FRAGMENT_CHECKOUT ");

                }
                locationManager = null;
            }
        });
        return fragmentAturAlamatSayaBinding.getRoot();

    }
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        oke = true;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMyLocationEnabled(true);
    }
}