package com.example.agfood.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.agfood.Adapter.AdapterButton;
import com.example.agfood.Adapter.AdapterFoodPopular;
import com.example.agfood.Model.ModelButton;
import com.example.agfood.Model.ModelFood;
import com.example.agfood.Model.UtilFood;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentHomeBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

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

    public HomeFragment() {
        // Required empty public constructor
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
    List<ModelFood> mListModelFood = UtilFood.getListFood();

    List<ModelButton> listButtonName = UtilFood.getListKategoriMenuMakanan();
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
                    case "Semua":
                        mListModelFood = UtilFood.getListFood();
                        initalizeAdapter();
                        adapterFoodPopular.notifyDataSetChanged();
                        break;
                    case "Menu Reguler":
                        break;
                    case "Menu Medium":
                        break;
                    case "Menu Large":
                        break;
                    case "Menu Cemilan":
                        mListModelFood = UtilFood.getListCemilan();
                        initalizeAdapter();

                        adapterFoodPopular.notifyDataSetChanged();
                        break;
                    case "Menu Minuman":
                        mListModelFood = UtilFood.getListMinuman();
                        initalizeAdapter();

                        adapterFoodPopular.notifyDataSetChanged();
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
    void initalizeAdapter(){
        AdapterFoodPopular.AdapterFoodInterface mAdapterFoodInterface = new AdapterFoodPopular.AdapterFoodInterface() {
            @Override
            public void clickItemSelectedListener(int positionOfItemFoodSelected) {
                ModelFood foodSelected = mListModelFood.get(positionOfItemFoodSelected);
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_out,R.anim.fade_in,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentDetailMakanan(foodSelected)).commit();
            }
        };
        adapterFoodPopular = new AdapterFoodPopular(mListModelFood,getActivity().getApplicationContext(),mAdapterFoodInterface);
       // mFragmentHomeBinding.idRecPopularFood.setLayoutManager(new CenterZoomLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL,false));
        mFragmentHomeBinding.idRecPopularFood.setAdapter(adapterFoodPopular);
    }
    FragmentHomeBinding mFragmentHomeBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragme
        mFragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false);
        Util.setCustomColorText(mFragmentHomeBinding.idTvGrettingHome, "Good", "Morning","Diana" , "ffffff", "E41277");
        initalizeAdapter();
        initializeDataKategory();
        mFragmentHomeBinding.idNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.id_nav_keranjang:
                        Util.switchFragment(new FragmentKeranjang(), getActivity());
                        break;
                    case R.id.id_nav_home:
                        Util.switchFragment(new HomeFragment(), getActivity());
                        break;
                }
                return true;

            }
        });
        return mFragmentHomeBinding.getRoot();
    }
}