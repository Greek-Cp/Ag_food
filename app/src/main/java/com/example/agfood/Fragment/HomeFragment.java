package com.example.agfood.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agfood.API.APIRequestData;
import com.example.agfood.API.BaseServerApp;
import com.example.agfood.Activity.FragmentSearchMenuMakanan;
import com.example.agfood.Adapter.AdapterButton;
import com.example.agfood.Adapter.AdapterFoodPopular;
import com.example.agfood.DataModel.ModelResponseAccount;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelBarang;
import com.example.agfood.Model.ModelButton;
import com.example.agfood.Model.ModelDetailAccount;
import com.example.agfood.Model.ModelFav;
import com.example.agfood.Model.ModelFavorit;
import com.example.agfood.Model.ModelResponseBarang;
import com.example.agfood.Model.ModelResponseFav;
import com.example.agfood.Model.ModelRetrieveAccount;
import com.example.agfood.Model.UtilFood;
import com.example.agfood.R;
import com.example.agfood.Util.SharedPrefDetail;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentHomeBinding;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ModelAccount username;



    public HomeFragment(){

    }
    UtilPref utilPref = new UtilPref();
    public HomeFragment(ModelAccount modelAccount){
        this.username = modelAccount;
    }
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */

    // TODO: Rename and change types and number of parameters
     public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    List<ModelBarang> mListModelFood = UtilFood.getListFoodAPI();
    List<ModelButton> listButtonName = UtilFood.getListKategoriMenuMakanan();
    List<ModelFav> listModelFavoritSelectedByUser;
    AdapterButton adapterButton;
    AdapterFoodPopular adapterFoodPopular;
    SharedPreferences sharedPreferences;
    void initializeDataKategory(){
        if(listButtonName == null){
            listButtonName = new ArrayList<>();
        }
        sharedPreferences = getActivity().getSharedPreferences("PREF_BUTTON", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        AdapterButton.AdapterButtonClickListener adapterButtonClickListener = new AdapterButton.AdapterButtonClickListener() {
            @Override
            public void clickButtonListener(int positionOfButton) {
                switch (listButtonName.get(positionOfButton).getNameButton()){
                    case "Populer":
                        initalizeAdapter(listBarang);
                        adapterFoodPopular.notifyDataSetChanged();
                        break;
                    case "Makanan":
                        Util.switchFragment(new FragmentViewMenuSelected("Makanan"),getActivity());
                        break;
                    case "Minuman":
                        Util.switchFragment(new FragmentViewMenuSelected("Minuman"),getActivity());
                        break;
                }
                if(listButtonName.get(positionOfButton).isClicked()){
                    listButtonName.get(positionOfButton).setClicked(true);
                    editor.putInt("CURRENT_POST",  positionOfButton);
                    editor.commit();
                } else if(!listButtonName.get(positionOfButton).isClicked()){
                    int getCurrentVal = sharedPreferences.getInt("CURRENT_POST",0);
                    listButtonName.get(getCurrentVal).setClicked(false);
                    listButtonName.get(positionOfButton).setClicked(true);
                    editor.putInt("CURRENT_POST", positionOfButton);
                    editor.commit();
                }
                adapterButton.notifyDataSetChanged();
            }
        };
        adapterButton = new AdapterButton(listButtonName,adapterButtonClickListener);
        mFragmentHomeBinding.idRecKategoryFood.setAdapter(adapterButton);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    void initalizeAdapter(List<ModelBarang> listBarang){

        AdapterFoodPopular.AdapterFoodInterface mAdapterFoodInterface = new AdapterFoodPopular.AdapterFoodInterface() {
            @Override
            public void clickItemSelectedListener(int positionOfItemFoodSelected) {
                ModelBarang foodSelected = listBarang.get(positionOfItemFoodSelected);
                 getActivity().getSupportFragmentManager().beginTransaction()
                         .setCustomAnimations(R.anim.slide_in,R.anim.fade_in,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentDetailMakanan(username,foodSelected,"HOME")).commit();
            }

            @Override
            public void clickLoveListener(int positionOfItemLikeByUser) {

            }
        };
        adapterFoodPopular = new AdapterFoodPopular(listBarang,listModelFavoritSelectedByUser,mAdapterFoodInterface);
       // mFragmentHomeBinding.idRecPopularFood.setLayoutManager(new CenterZoomLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL,false));
        mFragmentHomeBinding.idRecPopularFood.setAdapter(adapterFoodPopular);
    }
     List<ModelBarang> listBarang = new ArrayList<>();
    public  List<ModelBarang> getListFoodAPI(){
        APIRequestData apiRequestData = BaseServerApp.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponseBarang> modelResponseBarangCall  = apiRequestData.getResponseDataBarang();
        modelResponseBarangCall.enqueue(new Callback<ModelResponseBarang>() {
            @Override
            public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                int kode = response.body().getKode().intValue();
                    // here you can what response you are getting.
                Log.d("JSON From Server", response.body().getDataBarang().toString());//convert reponse to string
                for(ModelBarang modelBarang:  response.body().getDataBarang()){
                    System.out.println("Id Barang = " + modelBarang.getId_barang());
                    System.out.println("Nama Barang = " + modelBarang.getNama_barang());
                    System.out.println("Gambar Image = " + modelBarang.getGambar_barang());
                    System.out.println("Deskripsi = " + modelBarang.getDeskripsi_barang());
                    listBarang.add(modelBarang);
                }

                initalizeAdapter(listBarang);
                Gson gson = new Gson();
                SharedPrefDetail sharedPrefDetailBarang = utilPref.barangPrefences;
                sharedPrefDetailBarang.setSerializeDataList(gson.toJson(listBarang));
                Util.saveDataListPrefences(sharedPrefDetailBarang,getActivity().getApplicationContext());
            }
            @Override
            public void onFailure(Call<ModelResponseBarang> call, Throwable t) {
                System.out.println("DATA"   +t.getMessage() + t.getLocalizedMessage() + "test " + t.getCause() + "| ");

            }
        });
        return listBarang;
    }
    FragmentHomeBinding mFragmentHomeBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragme
        mFragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false);
        Gson gson = new Gson();
        System.out.println("gson response = " + gson.toJson(username));
        Util.resetButtonAdapter(getActivity());
        if(username != null){
            SharedPrefDetail prefDetailUser = utilPref.accountPrefences;
             prefDetailUser.setSerializeDataList(gson.toJson(username));
            Util.saveDataListPrefences(prefDetailUser,getActivity().getApplicationContext());
            mFragmentHomeBinding.idTvNamaUser.setText("Hi " + username.getNamaLengkap());
        } else {
            SharedPrefDetail sharedPrefDetailAccount = utilPref.accountPrefences;
            username = Util.getCurrentAccount(sharedPrefDetailAccount, getActivity());
            mFragmentHomeBinding.idTvNamaUser.setText("Hi " + username.getNamaLengkap());
        }
        Util.getApiRequetData().getNamaLengkap(username.getIdAkun()).enqueue(new Callback<ModelResponseAccount>() {
            @Override
            public void onResponse(Call<ModelResponseAccount> call, Response<ModelResponseAccount> response) {
                    mFragmentHomeBinding.idTvNamaUser.setText("Hi " + response.body().nama_lengkap);
                    username.setNamaLengkap(response.body().nama_lengkap);
                SharedPrefDetail prefDetailUser = utilPref.accountPrefences;
                prefDetailUser.setSerializeDataList(gson.toJson(username));
                Util.saveDataListPrefences(prefDetailUser,getActivity().getApplicationContext());


            }

            @Override
            public void onFailure(Call<ModelResponseAccount> call, Throwable t) {

            }
        });
        Util.getApiRequetData().getImageProfile(
                username.getEmail()
        ).enqueue(new Callback<ModelResponseAccount>() {
            @Override
            public void onResponse(Call<ModelResponseAccount> call, Response<ModelResponseAccount> response) {
                if(response.body().gambar_profile == null){
                    mFragmentHomeBinding.idImgProfile.setImageResource(R.drawable.ic_profile_saya);
                    System.out.println("Profile Belum Di Set !");
                } else{
                    Picasso.get()
                            .load(response.body().getGambar_profile()).resize(512,512).centerCrop()
                            .into(mFragmentHomeBinding.idImgProfile);
                }
            }
            @Override
            public void onFailure(Call<ModelResponseAccount> call, Throwable t) {

            }
        });
        mFragmentHomeBinding.idEditTextSearchMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.switchFragment(getActivity().getSupportFragmentManager(), new FragmentSearchMenuMakanan(),"FRAGMENT_SEARCH_MAKANAN");
            }
        });

        //        Util.setCustomColorText(mFragmentHomeBinding.idTvGrettingHome, "Sedang", " ","Lapar" , "ffffff", "FFA724");
        Util.setCustomColorText(mFragmentHomeBinding.idTvGrettingHome, "Sedang ", "Lapar ?", "ff4552");
        getActivity().findViewById(R.id.id_nav_bar).setVisibility(View.VISIBLE);
        initializeDataKategory();
        System.out.println(getListFoodAPI() + " SIZE");
        Util.showNavBottom(getActivity());
        return mFragmentHomeBinding.getRoot();
    }

}