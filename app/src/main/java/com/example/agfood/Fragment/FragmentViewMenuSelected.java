package com.example.agfood.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agfood.API.APIRequestData;
import com.example.agfood.API.BaseServerApp;
import com.example.agfood.Adapter.AdapterButton;
import com.example.agfood.Adapter.AdapterFoodPopular;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelBarang;
import com.example.agfood.Model.ModelButton;
import com.example.agfood.Model.ModelFav;
import com.example.agfood.Model.ModelFavorit;
import com.example.agfood.Model.ModelFood;
import com.example.agfood.Model.ModelResponseBarang;
import com.example.agfood.Model.ModelResponseIdKeranjang;
import com.example.agfood.Model.UtilFood;
import com.example.agfood.R;
import com.example.agfood.Util.CenterZoomLayoutManager;
import com.example.agfood.Util.SharedPrefDetail;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentViewMenuSelectedBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentViewMenuSelected#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentViewMenuSelected extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<ModelFood> mListModelFood = UtilFood.getListFood();
    ModelAccount mdl;
    public FragmentViewMenuSelected(){

    }
    private String categoryName;
    public FragmentViewMenuSelected(String categoryName) {
        // Required empty public constructor
        this.categoryName = categoryName;
        switch (categoryName){
            case "Makanan":
                mListModelFood = UtilFood.getListCemilan();
                break;
            case "Minuman":
                mListModelFood = UtilFood.getListMinuman();
                break;
        }
        System.out.println("Category Name = " + categoryName);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentViewMenuSelected.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentViewMenuSelected newInstance(String param1, String param2) {
        FragmentViewMenuSelected fragment = new FragmentViewMenuSelected();
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
    SharedPreferences sharedPreferences;
    List<ModelFav> listModelFavoritSelectedByUser;
    AdapterButton adapterButtonMakanan;
    public void initializeKategoryAdapter(){
        sharedPreferences = getActivity().getSharedPreferences("PREF_BUTTON", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        listModelFavoritSelectedByUser = UtilFood.getListFoodLikeByUser(Integer.parseInt(mdl.getIdAkun()));
        switch (this.categoryName){
            case "Makanan":
              List<ModelButton> listKategoryButtonMakanan = new ArrayList<>();
                listKategoryButtonMakanan.add(new ModelButton("Menu Reguler", true,R.drawable.ic_menu_reguler));
                listKategoryButtonMakanan.add(new ModelButton("Menu Medium", false,R.drawable.ic_medium));
                listKategoryButtonMakanan.add(new ModelButton("Menu Large", false,R.drawable.ic_large));
                listKategoryButtonMakanan.add(new ModelButton("Menu Cemilan", false,R.drawable.ic_cemilan));
                listKategoryButtonMakanan.add(new ModelButton("Mie Ngocor", false,R.drawable.ic_mie_ngocor));
                listKategoryButtonMakanan.add(new ModelButton("Seblak Ngocor", false,R.drawable.tteok));
                AdapterButton.AdapterButtonClickListener listenerClickItemMakanan = new AdapterButton.AdapterButtonClickListener() {
                    @Override
                    public void clickButtonListener(int positionOfButton) {
                        if(listKategoryButtonMakanan.get(positionOfButton).isClicked()){
                            listKategoryButtonMakanan.get(positionOfButton).setClicked(true);
                            editor.putInt("CURRENT_POST_MENU",  positionOfButton);
                            editor.commit();
                        } else if(!listKategoryButtonMakanan.get(positionOfButton).isClicked()){
                            int getCurrentVal = sharedPreferences.getInt("CURRENT_POST_MENU",0);
                            listKategoryButtonMakanan.get(getCurrentVal).setClicked(false);
                            listKategoryButtonMakanan.get(positionOfButton).setClicked(true);
                            editor.putInt("CURRENT_POST_MENU", positionOfButton);
                            editor.commit();
                        }

                        adapterButtonMakanan.notifyDataSetChanged();
                        switch (listKategoryButtonMakanan.get(positionOfButton).getNameButton()){
                            case "Menu Reguler":
                                Util.getApiRequetData().getMenuSpesifik("Menu Reguler").enqueue(
                                        new Callback<ModelResponseBarang>() {
                                            @Override
                                            public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                                                List<ModelBarang> listBarang = response.body().getDataBarang();
                                                AdapterFoodPopular.AdapterFoodInterface mAdapterFoodInterface = new AdapterFoodPopular.AdapterFoodInterface() {
                                                    @Override
                                                    public void clickItemSelectedListener(int positionOfItemFoodSelected) {
                                                        ModelBarang foodSelected = listBarang.get(positionOfItemFoodSelected);
                                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                                .setCustomAnimations(R.anim.slide_in,R.anim.fade_in,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentDetailMakanan(mdl,foodSelected,"HOME")).commit();
                                                    }

                                                    @Override
                                                    public void clickLoveListener(int positionOfItemLikeByUser) {
                                                        ModelFavorit modelFavorit = new ModelFavorit(Integer.parseInt(mdl.getIdAkun()),listBarang.get(positionOfItemLikeByUser).getId_barang(),"T");
                                                        APIRequestData apiRequestData = BaseServerApp.konekRetrofit().create(APIRequestData.class);
                                                        Call<ModelFavorit> callModelFavorit = apiRequestData.saveLikeData(modelFavorit.getId_akun(),modelFavorit.getId_barang(),modelFavorit.getStatus_fav());
                                                        callModelFavorit.enqueue(new Callback<ModelFavorit>() {
                                                            @Override
                                                            public void onResponse(Call<ModelFavorit> call, Response<ModelFavorit> response) {

                                                            }

                                                            @Override
                                                            public void onFailure(Call<ModelFavorit> call, Throwable t) {
                                                                System.out.println("response error = " + t.getLocalizedMessage());
                                                            }
                                                        });
                                                    }
                                                };
                                                adapterFoodPopular = new AdapterFoodPopular(listBarang,listModelFavoritSelectedByUser,getActivity().getApplicationContext(),mAdapterFoodInterface);
                                                fragmentViewMenuSelectedBinding.idRecMenuSelected.setAdapter(adapterFoodPopular);
                                                // fragmentViewMenuSelectedBinding.idRecMenuSelected.setLayoutManager(new CenterZoomLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL,false));
                                            }

                                            @Override
                                            public void onFailure(Call<ModelResponseBarang> call, Throwable t) {

                                            }
                                        }
                                );
                                break;
                            case "Menu Large":
                                Util.getApiRequetData().getMenuSpesifik("Menu Large").enqueue(
                                        new Callback<ModelResponseBarang>() {
                                            @Override
                                            public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                                                List<ModelBarang> listBarang = response.body().getDataBarang();
                                                AdapterFoodPopular.AdapterFoodInterface mAdapterFoodInterface = new AdapterFoodPopular.AdapterFoodInterface() {
                                                    @Override
                                                    public void clickItemSelectedListener(int positionOfItemFoodSelected) {
                                                        ModelBarang foodSelected = listBarang.get(positionOfItemFoodSelected);
                                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                                .setCustomAnimations(R.anim.slide_in,R.anim.fade_in,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentDetailMakanan(mdl,foodSelected,"HOME")).commit();
                                                    }

                                                    @Override
                                                    public void clickLoveListener(int positionOfItemLikeByUser) {
                                                        ModelFavorit modelFavorit = new ModelFavorit(Integer.parseInt(mdl.getIdAkun()),listBarang.get(positionOfItemLikeByUser).getId_barang(),"T");
                                                        APIRequestData apiRequestData = BaseServerApp.konekRetrofit().create(APIRequestData.class);
                                                        Call<ModelFavorit> callModelFavorit = apiRequestData.saveLikeData(modelFavorit.getId_akun(),modelFavorit.getId_barang(),modelFavorit.getStatus_fav());
                                                        callModelFavorit.enqueue(new Callback<ModelFavorit>() {
                                                            @Override
                                                            public void onResponse(Call<ModelFavorit> call, Response<ModelFavorit> response) {

                                                            }

                                                            @Override
                                                            public void onFailure(Call<ModelFavorit> call, Throwable t) {
                                                                System.out.println("response error = " + t.getLocalizedMessage());
                                                            }
                                                        });
                                                    }
                                                };
                                                adapterFoodPopular = new AdapterFoodPopular(listBarang,listModelFavoritSelectedByUser,getActivity().getApplicationContext(),mAdapterFoodInterface);
                                                fragmentViewMenuSelectedBinding.idRecMenuSelected.setAdapter(adapterFoodPopular);
                                            }

                                            @Override
                                            public void onFailure(Call<ModelResponseBarang> call, Throwable t) {

                                            }
                                        }
                                );
                                break;
                            case "Menu Cemilan":
                                Util.getApiRequetData().getMenuSpesifik("Menu Cemilan").enqueue(
                                        new Callback<ModelResponseBarang>() {
                                            @Override
                                            public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                                                List<ModelBarang> listBarang = response.body().getDataBarang();
                                                AdapterFoodPopular.AdapterFoodInterface mAdapterFoodInterface = new AdapterFoodPopular.AdapterFoodInterface() {
                                                    @Override
                                                    public void clickItemSelectedListener(int positionOfItemFoodSelected) {
                                                        ModelBarang foodSelected = listBarang.get(positionOfItemFoodSelected);
                                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                                .setCustomAnimations(R.anim.slide_in,R.anim.fade_in,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentDetailMakanan(mdl,foodSelected,"HOME")).commit();
                                                    }

                                                    @Override
                                                    public void clickLoveListener(int positionOfItemLikeByUser) {
                                                        ModelFavorit modelFavorit = new ModelFavorit(Integer.parseInt(mdl.getIdAkun()),listBarang.get(positionOfItemLikeByUser).getId_barang(),"T");
                                                        APIRequestData apiRequestData = BaseServerApp.konekRetrofit().create(APIRequestData.class);
                                                        Call<ModelFavorit> callModelFavorit = apiRequestData.saveLikeData(modelFavorit.getId_akun(),modelFavorit.getId_barang(),modelFavorit.getStatus_fav());
                                                        callModelFavorit.enqueue(new Callback<ModelFavorit>() {
                                                            @Override
                                                            public void onResponse(Call<ModelFavorit> call, Response<ModelFavorit> response) {

                                                            }

                                                            @Override
                                                            public void onFailure(Call<ModelFavorit> call, Throwable t) {
                                                                System.out.println("response error = " + t.getLocalizedMessage());
                                                            }
                                                        });
                                                    }
                                                };
                                                adapterFoodPopular = new AdapterFoodPopular(listBarang,listModelFavoritSelectedByUser,getActivity().getApplicationContext(),mAdapterFoodInterface);
                                                fragmentViewMenuSelectedBinding.idRecMenuSelected.setAdapter(adapterFoodPopular);
                                            }

                                            @Override
                                            public void onFailure(Call<ModelResponseBarang> call, Throwable t) {

                                            }
                                        }
                                );
                                break;
                            case "Seblak Ngocor":
                                Util.getApiRequetData().getMenuSpesifik("Seblak Ngocor").enqueue(
                                        new Callback<ModelResponseBarang>() {
                                            @Override
                                            public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                                                List<ModelBarang> listBarang = response.body().getDataBarang();
                                                AdapterFoodPopular.AdapterFoodInterface mAdapterFoodInterface = new AdapterFoodPopular.AdapterFoodInterface() {
                                                    @Override
                                                    public void clickItemSelectedListener(int positionOfItemFoodSelected) {
                                                        ModelBarang foodSelected = listBarang.get(positionOfItemFoodSelected);
                                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                                .setCustomAnimations(R.anim.slide_in,R.anim.fade_in,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentDetailMakanan(mdl,foodSelected,"HOME")).commit();
                                                    }

                                                    @Override
                                                    public void clickLoveListener(int positionOfItemLikeByUser) {
                                                        ModelFavorit modelFavorit = new ModelFavorit(Integer.parseInt(mdl.getIdAkun()),listBarang.get(positionOfItemLikeByUser).getId_barang(),"T");
                                                        APIRequestData apiRequestData = BaseServerApp.konekRetrofit().create(APIRequestData.class);
                                                        Call<ModelFavorit> callModelFavorit = apiRequestData.saveLikeData(modelFavorit.getId_akun(),modelFavorit.getId_barang(),modelFavorit.getStatus_fav());
                                                        callModelFavorit.enqueue(new Callback<ModelFavorit>() {
                                                            @Override
                                                            public void onResponse(Call<ModelFavorit> call, Response<ModelFavorit> response) {

                                                            }

                                                            @Override
                                                            public void onFailure(Call<ModelFavorit> call, Throwable t) {
                                                                System.out.println("response error = " + t.getLocalizedMessage());
                                                            }
                                                        });
                                                    }
                                                };
                                                adapterFoodPopular = new AdapterFoodPopular(listBarang,listModelFavoritSelectedByUser,getActivity().getApplicationContext(),mAdapterFoodInterface);
                                                fragmentViewMenuSelectedBinding.idRecMenuSelected.setAdapter(adapterFoodPopular);
                                            }

                                            @Override
                                            public void onFailure(Call<ModelResponseBarang> call, Throwable t) {

                                            }
                                        }
                                );
                                break;
                            case "Menu Medium":
                                Util.getApiRequetData().getMenuSpesifik("Menu Makanan Medium").enqueue(
                                        new Callback<ModelResponseBarang>() {
                                            @Override
                                            public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                                                List<ModelBarang> listBarang = response.body().getDataBarang();
                                                AdapterFoodPopular.AdapterFoodInterface mAdapterFoodInterface = new AdapterFoodPopular.AdapterFoodInterface() {
                                                    @Override
                                                    public void clickItemSelectedListener(int positionOfItemFoodSelected) {
                                                        ModelBarang foodSelected = listBarang.get(positionOfItemFoodSelected);
                                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                                .setCustomAnimations(R.anim.slide_in,R.anim.fade_in,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentDetailMakanan(mdl,foodSelected,"HOME")).commit();
                                                    }

                                                    @Override
                                                    public void clickLoveListener(int positionOfItemLikeByUser) {
                                                        ModelFavorit modelFavorit = new ModelFavorit(Integer.parseInt(mdl.getIdAkun()),listBarang.get(positionOfItemLikeByUser).getId_barang(),"T");
                                                        APIRequestData apiRequestData = BaseServerApp.konekRetrofit().create(APIRequestData.class);
                                                        Call<ModelFavorit> callModelFavorit = apiRequestData.saveLikeData(modelFavorit.getId_akun(),modelFavorit.getId_barang(),modelFavorit.getStatus_fav());
                                                        callModelFavorit.enqueue(new Callback<ModelFavorit>() {
                                                            @Override
                                                            public void onResponse(Call<ModelFavorit> call, Response<ModelFavorit> response) {

                                                            }

                                                            @Override
                                                            public void onFailure(Call<ModelFavorit> call, Throwable t) {
                                                                System.out.println("response error = " + t.getLocalizedMessage());
                                                            }
                                                        });
                                                    }
                                                };
                                                adapterFoodPopular = new AdapterFoodPopular(listBarang,listModelFavoritSelectedByUser,getActivity().getApplicationContext(),mAdapterFoodInterface);
                                                fragmentViewMenuSelectedBinding.idRecMenuSelected.setAdapter(adapterFoodPopular);
                                            }

                                            @Override
                                            public void onFailure(Call<ModelResponseBarang> call, Throwable t) {

                                            }
                                        }
                                );
                                break;
                            case "Mie Ngocor":
                                Util.getApiRequetData().getMenuSpesifik("Mie Ngocor").enqueue(
                                        new Callback<ModelResponseBarang>() {
                                            @Override
                                            public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                                                List<ModelBarang> listBarang = response.body().getDataBarang();
                                                AdapterFoodPopular.AdapterFoodInterface mAdapterFoodInterface = new AdapterFoodPopular.AdapterFoodInterface() {
                                                    @Override
                                                    public void clickItemSelectedListener(int positionOfItemFoodSelected) {
                                                        ModelBarang foodSelected = listBarang.get(positionOfItemFoodSelected);
                                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                                .setCustomAnimations(R.anim.slide_in,R.anim.fade_in,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentDetailMakanan(mdl,foodSelected,"HOME")).commit();
                                                    }

                                                    @Override
                                                    public void clickLoveListener(int positionOfItemLikeByUser) {
                                                        ModelFavorit modelFavorit = new ModelFavorit(Integer.parseInt(mdl.getIdAkun()),listBarang.get(positionOfItemLikeByUser).getId_barang(),"T");
                                                        APIRequestData apiRequestData = BaseServerApp.konekRetrofit().create(APIRequestData.class);
                                                        Call<ModelFavorit> callModelFavorit = apiRequestData.saveLikeData(modelFavorit.getId_akun(),modelFavorit.getId_barang(),modelFavorit.getStatus_fav());
                                                        callModelFavorit.enqueue(new Callback<ModelFavorit>() {
                                                            @Override
                                                            public void onResponse(Call<ModelFavorit> call, Response<ModelFavorit> response) {

                                                            }

                                                            @Override
                                                            public void onFailure(Call<ModelFavorit> call, Throwable t) {
                                                                System.out.println("response error = " + t.getLocalizedMessage());
                                                            }
                                                        });
                                                    }
                                                };
                                                adapterFoodPopular = new AdapterFoodPopular(listBarang,listModelFavoritSelectedByUser,getActivity().getApplicationContext(),mAdapterFoodInterface);
                                                fragmentViewMenuSelectedBinding.idRecMenuSelected.setAdapter(adapterFoodPopular);
                                            }

                                            @Override
                                            public void onFailure(Call<ModelResponseBarang> call, Throwable t) {

                                            }
                                        }
                                );
                                break;

                        }
                    }
                };
                 adapterButtonMakanan = new AdapterButton(listKategoryButtonMakanan,listenerClickItemMakanan);
                fragmentViewMenuSelectedBinding.idRecKategoryFood.setAdapter(adapterButtonMakanan);
                fragmentViewMenuSelectedBinding.idRecKategoryFood.setLayoutManager(new CenterZoomLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL,false));

                break;
            case "Minuman":
                sharedPreferences = getActivity().getSharedPreferences("PREF_BUTTON_MINUMAN",Context.MODE_PRIVATE);
                List<ModelButton> listKategoryButtonMinuman = new ArrayList<>();
                listKategoryButtonMinuman.add(new ModelButton("Dingin", false));
                listKategoryButtonMinuman.add(new ModelButton("Panas", false));
                AdapterButton.AdapterButtonClickListener listenerClickItemMinuman = new AdapterButton.AdapterButtonClickListener() {
                    @Override
                    public void clickButtonListener(int positionOfButton) {
                        switch (listKategoryButtonMinuman.get(positionOfButton).getNameButton()){
                            case "Dingin":
                                break;
                            case "Panas":
                                break;
                        }
                    }
                };
                AdapterButton adapterButtonMinuman = new AdapterButton(listKategoryButtonMinuman,listenerClickItemMinuman);
                fragmentViewMenuSelectedBinding.idRecKategoryFood.setAdapter(adapterButtonMinuman);
                break;
        }
    }
    AdapterFoodPopular adapterFoodPopular;
    FragmentViewMenuSelectedBinding fragmentViewMenuSelectedBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentViewMenuSelectedBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_view_menu_selected,container  , false);
        UtilPref utilPref = new UtilPref();
        Util.hiddenNavBottom(getActivity());
        fragmentViewMenuSelectedBinding.idKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.switchFragment(getActivity().getSupportFragmentManager(),new FragmentBaseKeranjang(),"FRAGMENT_KERANJANG");
            }
        });
        SharedPrefDetail sharedPrefDetailAccount = utilPref.accountPrefences;
        mdl = Util.getCurrentAccount(sharedPrefDetailAccount, getActivity());
        AdapterFoodPopular.AdapterFoodInterface mAdapterFoodInterface = new AdapterFoodPopular.AdapterFoodInterface() {
            @Override
            public void clickItemSelectedListener(int positionOfItemFoodSelected) {
                ModelFood foodSelected = mListModelFood.get(positionOfItemFoodSelected);
             //   getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_out,R.anim.fade_in,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentDetailMakanan(foodSelected,categoryName)).commit();
            }

            @Override
            public void clickLoveListener(int positionOfItemLikeByUser) {

            }
        };
        Util.getApiRequetData().getMenuSpesifik("Menu Reguler").enqueue(
                new Callback<ModelResponseBarang>() {
                    @Override
                    public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                        List<ModelBarang> listBarang = response.body().getDataBarang();
                        AdapterFoodPopular.AdapterFoodInterface mAdapterFoodInterface = new AdapterFoodPopular.AdapterFoodInterface() {
                            @Override
                            public void clickItemSelectedListener(int positionOfItemFoodSelected) {
                                ModelBarang foodSelected = listBarang.get(positionOfItemFoodSelected);
                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .setCustomAnimations(R.anim.slide_in,R.anim.fade_in,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentDetailMakanan(mdl,foodSelected,"HOME")).commit();
                            }

                            @Override
                            public void clickLoveListener(int positionOfItemLikeByUser) {
                                ModelFavorit modelFavorit = new ModelFavorit(Integer.parseInt(mdl.getIdAkun()),listBarang.get(positionOfItemLikeByUser).getId_barang(),"T");
                                APIRequestData apiRequestData = BaseServerApp.konekRetrofit().create(APIRequestData.class);
                                Call<ModelFavorit> callModelFavorit = apiRequestData.saveLikeData(modelFavorit.getId_akun(),modelFavorit.getId_barang(),modelFavorit.getStatus_fav());
                                callModelFavorit.enqueue(new Callback<ModelFavorit>() {
                                    @Override
                                    public void onResponse(Call<ModelFavorit> call, Response<ModelFavorit> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<ModelFavorit> call, Throwable t) {
                                        System.out.println("response error = " + t.getLocalizedMessage());
                                    }
                                });
                            }
                        };
                        adapterFoodPopular = new AdapterFoodPopular(listBarang,listModelFavoritSelectedByUser,getActivity().getApplicationContext(),mAdapterFoodInterface);
                        fragmentViewMenuSelectedBinding.idRecMenuSelected.setAdapter(adapterFoodPopular);
                                          }

                    @Override
                    public void onFailure(Call<ModelResponseBarang> call, Throwable t) {

                    }
                }
        );
        initializeKategoryAdapter();
        //adapterFoodPopular = new AdapterFoodPopular(mListModelFood,getActivity().getApplicationContext(),mAdapterFoodInterface);
        // mFragmentHomeBinding.idRecPopularFood.setLayoutManager(new CenterZoomLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL,false));
        fragmentViewMenuSelectedBinding.idRecMenuSelected.setAdapter(adapterFoodPopular);
        fragmentViewMenuSelectedBinding.idTvDetailMakananTopName.setText(categoryName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            fragmentViewMenuSelectedBinding.idBtnDetailMakananBackButton.setOutlineSpotShadowColor(getActivity().getResources().getColor(R.color.red_color));
        }
        fragmentViewMenuSelectedBinding.idBtnDetailMakananBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.switchFragment(new HomeFragment(), getActivity());
            }
        });

        return fragmentViewMenuSelectedBinding.getRoot();

    }
}