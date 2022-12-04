package com.example.agfood.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agfood.Adapter.AdapterButton;
import com.example.agfood.Adapter.AdapterFoodPopular;
import com.example.agfood.Model.ModelButton;
import com.example.agfood.Model.ModelFood;
import com.example.agfood.Model.UtilFood;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentViewMenuSelectedBinding;

import java.util.ArrayList;
import java.util.List;

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
    public void initializeKategoryAdapter(){
        switch (this.categoryName){
            case "Makanan":
              List<ModelButton> listKategoryButtonMakanan = new ArrayList<>();
                listKategoryButtonMakanan.add(new ModelButton("Seblak", false));
                listKategoryButtonMakanan.add(new ModelButton("Reguler", false));
                listKategoryButtonMakanan.add(new ModelButton("Cemilan", false));
                AdapterButton.AdapterButtonClickListener listenerClickItemMakanan = new AdapterButton.AdapterButtonClickListener() {
                    @Override
                    public void clickButtonListener(int positionOfButton) {

                        switch (listKategoryButtonMakanan.get(positionOfButton).getNameButton()){
                            case "Seblak":
                                break;
                            case "Reguler":
                                break;
                            case "Cemilan":
                                break;
                        }
                    }
                };
                AdapterButton adapterButtonMakanan = new AdapterButton(listKategoryButtonMakanan,listenerClickItemMakanan);
                fragmentViewMenuSelectedBinding.idRecKategoryFood.setAdapter(adapterButtonMakanan);
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